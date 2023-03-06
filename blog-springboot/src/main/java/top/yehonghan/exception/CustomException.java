package top.yehonghan.exception;

/**
 * ClassName: CustomException
 * Description: 自定义业务异常
 *
 * @Author: yehonghan
 * @Create: 2022/12/10 13:49
 * @Version: V1.0
 */
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
