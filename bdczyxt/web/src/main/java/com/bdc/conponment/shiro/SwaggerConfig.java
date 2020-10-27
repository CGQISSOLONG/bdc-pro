package com.bdc.conponment.shiro;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
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
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName 类名：SwaggerConfig
 * @Description 功能说明：Swagger配置文件类。
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {
    /**
     * 是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
     */
    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;

    @Bean
    public Docket createTestRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("测试模块")
                .enable(swaggerEnabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bdc.controller.test"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createPubUserRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("api模块")
                .enable(swaggerEnabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bdc.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    //todo 新模块接口按照上面方法模式添加即可。
    /**
     * API信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("不动产系统").description("不动产系统")
                .contact(new Contact("cgq", "http://localhost:8010/bdczyxt", "1285063203@qq.com"))
                .version("1.0.0").build();
    }
}
