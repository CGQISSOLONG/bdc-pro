package com.admin.service;


import com.admin.entity.primary.AdminRole;

import java.util.List;

/**
 * @ClassName 类名：AdminRoleService
 * @Description 功能说明：角色接口
 */
public interface AdminRoleService {
    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    AdminRole create(AdminRole role);

    /**
     * 为角色分配菜单
     *
     * @param id
     * @param asList
     */
    void grantMenu(Integer id, List<Integer> asList);

    /**
     * 为角色分配资源
     * @param id
     * @param asList
     */
    void grantResource(String id, List<String> asList);

    /**
     * 查询资源
     * @param id
     * @return
     */
    Object selectResources(String id);

    /**
     * 删除角色，同时删除用户角色和角色关联的菜单
     * @param id
     */
    void delete(Integer id);

    /**
     * 获取角色列表
     * @return
     */
    List<AdminRole> list();

    /**
     * 更新橘色
     * @param roleTemp
     */
    void modify(AdminRole roleTemp);

    /**
     * 更新状态
     * @param id
     * @param state
     */
    void updateStateById(Integer id, Integer state);
}
