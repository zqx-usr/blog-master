package top.yehonghan.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import top.yehonghan.dto.ChatRecordDTO;
import top.yehonghan.dto.RecallMessageDTO;
import top.yehonghan.dto.WebsocketMessageDTO;
import top.yehonghan.entity.ChatRecord;
import top.yehonghan.enums.FilePathEnum;
import top.yehonghan.mapper.ChatRecordMapper;
import top.yehonghan.strategy.context.UploadStrategyContext;
import top.yehonghan.util.BeanCopyUtils;
import top.yehonghan.util.HtmlUtils;
import top.yehonghan.util.IpUtils;
import top.yehonghan.vo.VoiceVO;

import javax.websocket.*;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;

import static top.yehonghan.enums.ChatTypeEnum.*;

/**
 * ClassName: WebSocketServiceImpl
 * Description: websocket服务
 *
 * @Author: yehonghan
 * @Create: 2023/1/6 15:14
 * @Version: V1.0
 */
@Data
@Service
@ServerEndpoint(value = "/websocket", configurator = WebSocketServiceImpl.ChatConfigurator.class)
public class WebSocketServiceImpl {
    /**
     * 用户session
     */
    private Session session;

    /**
     * 用户session集合
     */
    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet = new CopyOnWriteArraySet<>();

    @Autowired
    public void setChatRecordDao(ChatRecordMapper chatRecordMapper) {
        WebSocketServiceImpl.chatRecordMapper = chatRecordMapper;
    }

    @Autowired
    public void setUploadStrategyContext(UploadStrategyContext uploadStrategyContext) {
        WebSocketServiceImpl.uploadStrategyContext = uploadStrategyContext;
    }

    private static ChatRecordMapper chatRecordMapper;

    private static UploadStrategyContext uploadStrategyContext;

    /**
     * 获取客户端真实ip
     */
    public static class ChatConfigurator extends ServerEndpointConfig.Configurator {

        public static String HEADER_NAME = "X-Real-IP";

        @Override
        public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
            try {
                String firstFoundHeader = request.getHeaders().get(HEADER_NAME.toLowerCase()).get(0);
                sec.getUserProperties().put(HEADER_NAME, firstFoundHeader);
            } catch (Exception e) {
                sec.getUserProperties().put(HEADER_NAME, "未知ip");
            }
        }
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) throws IOException {
        // 加入连接
        this.session = session;
        webSocketSet.add(this);
        // 更新在线人数
        updateOnlineCount();
        // 加载历史聊天记录
        ChatRecordDTO chatRecordDTO = listChartRecords(endpointConfig);
        // 发送消息
        WebsocketMessageDTO messageDTO = WebsocketMessageDTO.builder()
                .type(HISTORY_RECORD.getType())
                .data(chatRecordDTO)
                .build();
        synchronized (session) {
            session.getBasicRemote().sendText(JSON.toJSONString(messageDTO));
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        WebsocketMessageDTO messageDTO = JSON.parseObject(message, WebsocketMessageDTO.class);
        switch (Objects.requireNonNull(getChatType(messageDTO.getType()))) {
            case SEND_MESSAGE:
                // 发送消息
                ChatRecord chatRecord = JSON.parseObject(JSON.toJSONString(messageDTO.getData()), ChatRecord.class);
                // 过滤html标签
                chatRecord.setContent(HtmlUtils.filter(chatRecord.getContent()));
                chatRecordMapper.insert(chatRecord);
                messageDTO.setData(chatRecord);
                // 广播消息
                broadcastMessage(messageDTO);
                break;
            case RECALL_MESSAGE:
                // 撤回消息
                RecallMessageDTO recallMessage = JSON.parseObject(JSON.toJSONString(messageDTO.getData()), RecallMessageDTO.class);
                // 删除记录
                chatRecordMapper.deleteById(recallMessage.getId());
                // 广播消息
                broadcastMessage(messageDTO);
                break;
            case HEART_BEAT:
                // 心跳消息
                messageDTO.setData("pong");
                session.getBasicRemote().sendText(JSON.toJSONString(JSON.toJSONString(messageDTO)));
            default:
                break;
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() throws IOException {
        // 更新在线人数
        webSocketSet.remove(this);
        updateOnlineCount();
    }

    /**
     * 加载历史聊天记录
     *
     * @param endpointConfig 配置
     * @return 加载历史聊天记录
     */
    private ChatRecordDTO listChartRecords(EndpointConfig endpointConfig) {
        // 获取聊天历史记录
        List<ChatRecord> chatRecordList = chatRecordMapper.selectList(new LambdaQueryWrapper<ChatRecord>()
                .ge(ChatRecord::getCreateTime, DateUtil.offsetHour(new Date(), -12)));
        // 获取当前用户ip
        String ipAddress = endpointConfig.getUserProperties().get(ChatConfigurator.HEADER_NAME).toString();
        return ChatRecordDTO.builder()
                .chatRecordList(chatRecordList)
                .ipAddress(ipAddress)
                .ipSource(IpUtils.getIpSource(ipAddress))
                .build();
    }

    /**
     * 更新在线人数
     *
     * @throws IOException io异常
     */
    @Async
    public void updateOnlineCount() throws IOException {
        // 获取当前在线人数
        WebsocketMessageDTO messageDTO = WebsocketMessageDTO.builder()
                .type(ONLINE_COUNT.getType())
                .data(webSocketSet.size())
                .build();
        // 广播消息
        broadcastMessage(messageDTO);
    }

    /**
     * 发送语音
     *
     * @param voiceVO 语音路径
     */
    public void sendVoice(VoiceVO voiceVO) {
        // 上传语音文件
        String content = uploadStrategyContext.executeUploadStrategy(voiceVO.getFile(), FilePathEnum.VOICE.getPath());
        voiceVO.setContent(content);
        // 保存记录
        ChatRecord chatRecord = BeanCopyUtils.copyObject(voiceVO, ChatRecord.class);
        chatRecordMapper.insert(chatRecord);
        // 发送消息
        WebsocketMessageDTO messageDTO = WebsocketMessageDTO.builder()
                .type(VOICE_MESSAGE.getType())
                .data(chatRecord)
                .build();
        // 广播消息
        try {
            broadcastMessage(messageDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 广播消息
     *
     * @param messageDTO 消息dto
     * @throws IOException io异常
     */
    private void broadcastMessage(WebsocketMessageDTO messageDTO) throws IOException {
        for (WebSocketServiceImpl webSocketService : webSocketSet) {
            synchronized (webSocketService.session) {
                webSocketService.session.getBasicRemote().sendText(JSON.toJSONString(messageDTO));
            }
        }
    }
}
