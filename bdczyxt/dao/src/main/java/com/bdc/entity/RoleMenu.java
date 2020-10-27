package com.bdc.entity;

import lombok.Data;

@Data
public class RoleMenu {
    private Integer id;
    private Integer roleId;
    private Integer menuId;
    private String roleName;
    /**
     * 逗号隔开 如：“选址,上传,采集,巡查,个人”
     */
    private String menuName;

}
