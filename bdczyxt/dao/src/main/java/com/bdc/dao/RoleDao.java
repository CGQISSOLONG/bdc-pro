package com.bdc.dao;

import com.bdc.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleDao {
    List<Role> findListByRoleIds(@Param("roleIds") List<Integer> roleIds);

    Role deleteById(Integer id);

    List<Role> findRolesById(Integer userId);

    List<Role> findAll();

    Role findOne(Integer roleId);

    void updateStateById(Integer id,Integer state);

    void update(Role role);
}
