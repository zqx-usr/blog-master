package top.yehonghan.service;

import top.yehonghan.dto.OperationLogDTO;
import top.yehonghan.entity.OperationLog;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yehonghan.vo.ConditionVO;
import top.yehonghan.vo.PageResult;

/**
* @author 86152
* @description 针对表【tb_operation_log】的数据库操作Service
* @createDate 2023-01-05 11:03:22
*/
public interface OperationLogService extends IService<OperationLog> {
    /**
     * 查询日志列表
     *
     * @param conditionVO 条件
     * @return 日志列表
     */
    PageResult<OperationLogDTO> listOperationLogs(ConditionVO conditionVO);
}
