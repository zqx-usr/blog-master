package top.yehonghan.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.yehonghan.entity.OperationLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86152
* @description 针对表【tb_operation_log】的数据库操作Mapper
* @createDate 2023-01-05 11:03:22
* @Entity top.yehonghan.entity.OperationLog
*/
@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {

}




