package top.yehonghan.annotation;

import java.lang.annotation.*;

/**
 * ClassName: OptLog
 * Description: 操作日志注解
 *
 * @Author: yehonghan
 * @Create: 2023/1/4 16:09
 * @Version: V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptLog {

    /**
     * @return 操作类型
     */
    String optType() default "";
}
