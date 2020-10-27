package com.bdc.entity.primary;

import lombok.Data;

import java.util.Date;

@Data
public class UserLoginLog {
    private Integer id;
    /**
     * 账号
     */
    private String account;

    private String ip;

    private Integer userId;

    private String loginTime;

    private String createDate;

    /**
     * 浏览器名
     */
    private String browserName;

    /**
     * 浏览器版本
     */
    private String browserVersion;
}
