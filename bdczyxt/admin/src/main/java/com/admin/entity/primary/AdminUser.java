package com.admin.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**

 * @ClassName 类名：AdminUser
 * @Description 功能说明：后台用户
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Id
    private Integer id;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 账号
     */
    private String account;

    /**
     * 用户名
     */
    private String staffName;

    /**
     * 登录标识
     */
    private String token;

    /**
     * 客户端访问
     */
    private String ip;

    private String username;

    /**
     * 状态 1. 在用。2.禁用。0.删除
     */
    private Integer status;

    /**
     * 0 公众用户 1 管理员 2 超级管理员
     */
    private Integer userType;

    private Date lastLoginDate;
    private String areaCode;
    private String phone;
    private String updateTime;

}
