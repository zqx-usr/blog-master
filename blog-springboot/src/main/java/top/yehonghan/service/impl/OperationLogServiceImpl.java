package top.yehonghan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.yehonghan.dto.OperationLogDTO;
import top.yehonghan.entity.OperationLog;
import top.yehonghan.service.OperationLogService;
import top.yehonghan.mapper.OperationLogMapper;
import org.springframework.stereotype.Service;
import top.yehonghan.util.BeanCopyUtils;
import top.yehonghan.util.PageUtils;
import top.yehonghan.vo.ConditionVO;
import top.yehonghan.vo.PageResult;

import java.util.List;

/**
* @author 86152
* @description 针对表【tb_operation_log】的数据库操作Service实现
* @createDate 2023-01-05 11:03:22
*/
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService{
    @Override
    public PageResult<OperationLogDTO> listOperationLogs(ConditionVO conditionVO) {
        Page<OperationLog> page = new Page<>(PageUtils.getCurrent(), PageUtils.getSize());
        // 查询日志列表
        Page<OperationLog> operationLogPage = this.page(page, new LambdaQueryWrapper<OperationLog>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), OperationLog::getOptModule, conditionVO.getKeywords())
                .or()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), OperationLog::getOptDesc, conditionVO.getKeywords())
                .orderByDesc(OperationLog::getId));
        List<OperationLogDTO> operationLogDTOList = BeanCopyUtils.copyList(operationLogPage.getRecords(), OperationLogDTO.class);
        return new PageResult<>(operationLogDTOList, (int) operationLogPage.getTotal());
    }

}




