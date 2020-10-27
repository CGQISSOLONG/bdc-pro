package com.bdc.service;

import com.bdc.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuService {
    /**
     * 根据角色ID查询角色菜单列表
     *
     * @param roleIds
     * @return
     */
    List<RoleMenu> findListByRoleIds(@Param("roleIds") List<Integer> roleIds);

    /**
     * 删除角色菜单
     *
     * @param id
     */
    void deleteByRoleId(Integer id);

    /**
     * @param id
     */
    void deleteByMenuId(Long id);

    /**
     * 批量插入
     *
     * @param roleId
     * @param menuIds
     */
    void insertBatch(@Param("roleId") Integer roleId, @Param("list") List<Integer> menuIds);
}
