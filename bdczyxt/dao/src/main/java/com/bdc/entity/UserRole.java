package com.bdc.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserRole {
    private Integer id;//用户角色标识码
    private Integer userId;//用户标识码
    private String username;
    private Integer roleId;//角色标识码
    private Date createTime;//创建时间
}
