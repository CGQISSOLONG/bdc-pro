package com.bdc.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Integer id;//角色标识码
    private String roleName;//角色名称
    private String description;//描述
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private Integer state;//状态0：删除，1：启用，2：禁用
}
