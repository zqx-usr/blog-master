package top.yehonghan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * ClassName: WebSocketConfig
 * Description: websocket配置类
 *
 * @Author: yehonghan
 * @Create: 2023/1/7 23:49
 * @Version: V1.0
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
