package com.bdc.service;

import com.bdc.dao.vo.SelectRoleVo;
import com.bdc.entity.Users;


import java.util.List;

public interface UserService {
    Users findByName(String account);

    List<Users> findAll();

    Users findById(String id);

    void save(Users user);

    // List<Role> findotherRoles(String id);

    Users getPubUserByToken(String token);

    /**
     * 获取用户所具有的角色和为分配的角色
     *
     * @param userId
     * @return
     */
    List<SelectRoleVo> selectRoles(Integer userId);

    /**
     * 分配角色
     *
     */
    void grantRole(Integer userId, List<Integer> roleIds);

    // void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
