package com.admin.service;

import com.admin.entity.primary.AdminMenu;

import java.util.List;

/**
 * @ClassName 类名：AdminMenuService
 * @Description 功能说明：管理后台菜单
 */
public interface AdminMenuService {
    /**
     *  创建菜单
     * @param menu
     * @return
     */
    AdminMenu create(AdminMenu menu);

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    AdminMenu modify(AdminMenu menu);

    /**
     * 获取用户菜单
     * @param userId
     * @return
     */
    List<AdminMenu> getNavMenus(Integer userId);

    /**
     * 删除菜单，同时删除角色关联该菜单
     * @param id
     */
    void delete(Integer id);

    /**
     * 更新菜单状态
     * @param id
     * @param disable
     */
    void updateStateById(Integer id, String disable);

    /**
     * 查询菜单列表
     * @return
     */
    List<AdminMenu> list();
}
