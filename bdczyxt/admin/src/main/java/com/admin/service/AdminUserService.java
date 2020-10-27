package com.admin.service;


import com.admin.controller.admin.vo.SelectRoleVo;
import com.admin.entity.primary.AdminUser;

import java.util.List;

/**
 * @ClassName 类名：AdminUserService
 * @Description 功能说明：用户接口

 */
public interface AdminUserService {
    /**
     * 新增用户
     * @param user
     * @return
     */
    int create(AdminUser user);

    /**
     *  保存用户角色信息
     * @param userId
     * @param roleIds
     */
    void grantRole(Integer userId, List<Integer> roleIds);

    /**
     * 获取用户所具有的角色和为分配的角色
     * @param userId
     * @return
     */
    List<SelectRoleVo> selectRoles(Integer userId);

    /**
     * 删除用户
     * @param id
     */
    void delete(Integer id);

    /**
     * 用户列表
     *
     */
    List<AdminUser> list();

    /**
     * 修改用户
     * @param user
     */
    void modify(AdminUser user);

    /**
     * 更新状态
     * @param id
     * @param status
     */
    void updateStatusById(Integer id, Integer status);
}
