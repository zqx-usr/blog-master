package top.yehonghan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.yehonghan.enums.StatusCodeEnum;

import static top.yehonghan.enums.StatusCodeEnum.FAIL;

/**
 * ClassName: BizException
 * Description: 自定义业务异常
 *
 * @Author: yehonghan
 * @Create: 2023/1/4 11:10
 * @Version: V1.0
 */
@Getter
@AllArgsConstructor
public class BizException extends RuntimeException{
    /**
     * 错误码
     */
    private Integer code = FAIL.getCode();

    /**
     * 错误信息
     */
    private final String message;

    public BizException(String message) {
        this.message = message;
    }

    public BizException(StatusCodeEnum statusCodeEnum) {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getDesc();
    }
}
