package top.yehonghan.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.yehonghan.exception.BizException;
import top.yehonghan.exception.CustomException;
import top.yehonghan.util.Result;

import java.sql.SQLDataException;
import java.util.Objects;

import static top.yehonghan.enums.StatusCodeEnum.SYSTEM_ERROR;
import static top.yehonghan.enums.StatusCodeEnum.VALID_ERROR;

/**
 * ClassName: GlobalExceptionHandler
 * Description: 全局异常处理器
 *
 * @Author: yehonghan
 * @Create: 2022/12/10 13:46
 * @Version: V1.0
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 处理数据库录入异常
     * @return
     */
    @ExceptionHandler(SQLDataException.class)
    public Result<String> exceptionHandler(Exception e){
        e.printStackTrace();
        return Result.fail("系统繁忙");
    }

    /**
     * 处理自定义异常
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public Result<String> exceptionHandler(CustomException e){
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }

    /**
     * 处理服务异常
     *
     * @param e 异常
     * @return 接口异常信息
     */
    @ExceptionHandler(value = BizException.class)
    public Result<?> errorHandler(BizException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 处理参数校验异常
     *
     * @param e 异常
     * @return 接口异常信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> errorHandler(MethodArgumentNotValidException e) {
        return Result.fail(VALID_ERROR.getCode(), Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /**
     * 处理系统异常
     *
     * @param e 异常
     * @return 接口异常信息
     */
    @ExceptionHandler(value = Exception.class)
    public Result<?> errorHandler(Exception e) {
        e.printStackTrace();
        return Result.fail(SYSTEM_ERROR.getCode(), SYSTEM_ERROR.getDesc());
    }
}
