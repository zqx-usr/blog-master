package top.yehonghan.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.yehonghan.entity.ChatRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86152
* @description 针对表【tb_chat_record】的数据库操作Mapper
* @createDate 2023-01-05 11:02:07
* @Entity top.yehonghan.entity.ChatRecord
*/
@Mapper
public interface ChatRecordMapper extends BaseMapper<ChatRecord> {

}




