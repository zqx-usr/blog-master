package top.yehonghan.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: CosConfigProperties
 * Description: cos配置属性
 *
 * @Author: yehonghan
 * @Create: 2023/1/6 17:03
 * @Version: V1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upload.cos")
public class CosConfigProperties {

    private String url;

    private String secretId;

    private String secretKey;

    private String region;

    private String bucketName;


}
