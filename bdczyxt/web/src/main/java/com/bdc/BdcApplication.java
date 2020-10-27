package com.bdc;

import com.bdc.interceptors.WebInterceptorAdapter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@MapperScan("com.bdc.dao")
@SpringBootApplication(scanBasePackages = {"com.bdc"})
//public class BdcApplication implements WebMvcConfigurer{
public class BdcApplication  extends WebMvcConfigurerAdapter {

public static void main(String[] args) {
        SpringApplication.run(BdcApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WebInterceptorAdapter());
        super.addInterceptors(registry);
    }

}
