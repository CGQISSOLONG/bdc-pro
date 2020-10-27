package com.admin.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 配置文件
 * @author zfy
 * @version 1.0, 2018/6/21
 * @author  luoxuan
 * 增加短信模板  2019-3-13 15:49:34
 * encoding=gbk  中文编码
 */
@Component
@PropertySource(value = {"classpath:config.properties"},encoding = "utf-8")
@Getter
public class Config {
    @Value("${file.tempPath}")
    private String tempPath;
    @Value("${file.savePath}")
    private String savePath;
    @Value("${xc.img.path}")
    private String xcImgPath;
    @Value("${file.sz.path}")
    private String szPath;
    @Value("${file.sz.temp.path}")
    private String szTempPath;
    @Value("${file.cl.path}")
    private String clPath;
    @Value("${file.app.path}")
    private String appPath;
    @Value("${file.fyhy}")
    private String fyhy;

    @Value("${info.img.path}")
    private String infoImgPath;

    @Value("${avatar.img.path}")
    private String avatarImgPath;

    @Value("${menu.img.path}")
    private String menumgPath;


}
