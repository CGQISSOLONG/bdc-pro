package com.admin.controller.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户分配角色功能中列出角色，用户已经具备的角色checked=true
 *
 * @author laill
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectRoleVo {

    /**
     * 角色id
     */
    private Integer rid;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 用户是否具有该角色
     */
    private boolean checked;

    /**
     * 角色描述
     */
    private String description;

}
