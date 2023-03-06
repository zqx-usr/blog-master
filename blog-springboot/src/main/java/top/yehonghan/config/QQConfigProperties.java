package top.yehonghan.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: QQConfigProperties
 * Description: qq配置属性
 *
 * @Author: yehonghan
 * @Create: 2023/1/6 16:42
 * @Version: V1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "qq")
public class QQConfigProperties {

    /**
     * QQ appId
     */
    private String appId;

    /**
     * 校验token地址
     */
    private String checkTokenUrl;

    /**
     * QQ用户信息地址
     */
    private String userInfoUrl;

}
