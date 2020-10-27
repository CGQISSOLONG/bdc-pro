package com.admin.controller.admin.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AdminUserDto {

    /** 主键ID */
    private Integer id;

    /** 登录名称 */
    private String account;


    /** 邮箱 */
    private String email;

    /** 是否禁用 */
    private Integer status;

    /** 创建时间 */
    private Date createTime;

    /** 最后登录时间 */
    private Date lastTime;

}
