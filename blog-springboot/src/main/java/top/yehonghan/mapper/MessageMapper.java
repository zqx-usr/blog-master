package top.yehonghan.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.yehonghan.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86152
* @description 针对表【tb_message】的数据库操作Mapper
* @createDate 2023-01-05 11:03:18
* @Entity top.yehonghan.entity.Message
*/
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}




