package top.yehonghan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.yehonghan.entity.SysLog;

/**
 * ClassName: SysLogMapper
 * Package: top.yehonghan.dao
 * Description: 系统日志Mapper
 *
 * @Author: yehonghan
 * @Create: 2022/12/7 14:11
 * @Version: V1.0
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {
}
