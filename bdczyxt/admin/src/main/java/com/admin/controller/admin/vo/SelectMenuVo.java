package com.admin.controller.admin.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName 类名：SelectMenuVo
 * @Description 功能说明：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectMenuVo {
    /**
     * 菜单id
     */
    private Integer mid;

    /**
     * 菜单名称
     */
    private String label;

    /**
     * 角色是否有此菜单权限
     */
    private boolean checked;
}
