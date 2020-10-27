package com.bdc.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Users {

    private Integer id;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String username;
    /**
     * 登录标识
     */
    private String token;
    /**
     * 客户端访问
     */
    private String ip;
    /**
     * 状态 1. 在用。2.禁用。0.删除
     */
    private Integer status;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 登陆时间
     */
    private String lastLoginDate;
    /**
     * 区域代码
     */
    private String areaCode;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 更新时间
     */
    private Date updateTime;

}
