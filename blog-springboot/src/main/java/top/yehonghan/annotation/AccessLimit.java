package top.yehonghan.annotation;

import java.lang.annotation.*;

/**
 * ClassName: AccessLimit
 * Description: redis接口限流
 *
 * @Author: yehonghan
 * @Create: 2023/1/4 9:45
 * @Version: V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimit {

    /**
     * 单位时间（秒）
     *
     * @return int
     */
    int seconds();

    /**
     * 单位时间最大请求次数
     *
     * @return int
     */
    int maxCount();
}
