package top.yehonghan.service;

import top.yehonghan.dto.MessageBackDTO;
import top.yehonghan.dto.MessageDTO;
import top.yehonghan.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yehonghan.vo.ConditionVO;
import top.yehonghan.vo.MessageVO;
import top.yehonghan.vo.PageResult;
import top.yehonghan.vo.ReviewVO;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_message】的数据库操作Service
* @createDate 2023-01-05 11:03:18
*/
public interface MessageService extends IService<Message> {
    /**
     * 添加留言弹幕
     *
     * @param messageVO 留言对象
     */
    void saveMessage(MessageVO messageVO);

    /**
     * 查看留言弹幕
     *
     * @return 留言列表
     */
    List<MessageDTO> listMessages();

    /**
     * 审核留言
     *
     * @param reviewVO 审查签证官
     */
    void updateMessagesReview(ReviewVO reviewVO);

    /**
     * 查看后台留言
     *
     * @param condition 条件
     * @return 留言列表
     */
    PageResult<MessageBackDTO> listMessageBackDTO(ConditionVO condition);

}
