package top.yehonghan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yehonghan.entity.SysLog;
import top.yehonghan.service.SysLogService;
import top.yehonghan.mapper.SysLogMapper;

/**
 * ClassName: SysLogServiceImpl
 * Package: top.yehonghan.service.impl
 * Description: 系统日志Service接口实现类
 *
 * @Author: yehonghan
 * @Create: 2022/12/7 14:14
 * @Version: V1.0
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
}
