package top.yehonghan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.yehonghan.entity.ChatRecord;
import top.yehonghan.service.ChatRecordService;
import top.yehonghan.mapper.ChatRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author 86152
* @description 针对表【tb_chat_record】的数据库操作Service实现
* @createDate 2023-01-05 11:02:07
*/
@Service
public class ChatRecordServiceImpl extends ServiceImpl<ChatRecordMapper, ChatRecord>
    implements ChatRecordService{

}




