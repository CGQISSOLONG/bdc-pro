package com.admin.config;

//import com.google.common.cache.CacheBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.guava.GuavaCacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

/**
 * @ClassName 类名：CacheConfig
 * @Description 功能说明：SpringBoot框架中集成GuavaCache实现本地缓存
 *  Guava Cache是一种本地缓存机制，之所以叫本地缓存，是因为它不会把缓存数据放到外部文件或者其他服务器上，
 *  而是存放到了应用内存中。
 */
//@Configuration
//@ConfigurationProperties(prefix = "spring.cache.caffeine")
//@EnableCaching(proxyTargetClass = true)
//public class CacheConfig {
//
//    private String spec;
//
//    @Bean
//    protected CacheManager cacheManager() {
//        GuavaCacheManager cacheManager = new GuavaCacheManager();
//        cacheManager.setCacheBuilder(CacheBuilder.from(spec));
//        return cacheManager;
//    }
//
//
//    public void setSpec(String spec) {
//        this.spec = spec;
//    }
//}
