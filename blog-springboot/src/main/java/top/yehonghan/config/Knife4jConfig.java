package top.yehonghan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Collections;

/**
 * ClassName: Knife4jConfig
 * Description: Swagger2配置类
 *
 * @Author: yehonghan
 * @Create: 2022/12/10 14:49
 * @Version: V1.0
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {

    /**
     * 是否开启swagger false 禁用  true开启
     */
    @Value("${swagger.isOpen}")
    private Boolean isOpen;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(Collections.singleton("http"))
                .enable(isOpen)
                .host("http://www.yehonghan.top")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.yehonghan.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("博客API文档")
                .description("springboot+vue开发的博客项目")
                .contact(new Contact("YHH_cs", "https://gitee.com/ye-honghan/blog", "yehonghan@foxmail.com"))
                .termsOfServiceUrl("http://www.yehonghan.top/api")
                .version("1.0")
                .build();
    }

}
