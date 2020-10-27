package com.admin;

import com.admin.controller.admin.intercept.NavMenuActiveInterceptor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication(scanBasePackages={"com.admin.*","com.bdc.*"})
@MapperScan(basePackages = {"com.admin.entity.*","com.bdc.entity.*"})
public class AdminApplication   extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new NavMenuActiveInterceptor()).addPathPatterns("/*");
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(this.getClass());
//    }

}
