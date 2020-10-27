package com.bdc.service;

import com.bdc.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {
//    List<Role> findListByRoleIds(@Param("roleIds") List<Integer> roleIds);
//
//    Role deleteById(Integer id);
//
//    List<Role> findRolesById(Integer userId);
    Role findRoleById(Integer id);

    Role findRoleByExample(Role role);

    List<Role> findListByRoleIds(List<Integer> roleIds);

    void save(Role role);

    List<Role> findAll();

    List<Role> findList(Role role);

    void delete(Role role);

    void delete(List<Role> roles);

    /* *//**
     * 为角色分配菜单
     *
     * @param id
     * @param asList
     *//*
    void grantMenu(Integer id, List<Integer> asList);

    *//**
     * 为角色分配资源
     * @param id
     * @param asList
     *//*
    void grantResource(String id, List<String> asList);

    *//**
     * 查询资源
     * @param id
     * @return
     *//*
    Object selectResources(String id);
    */

    /**
     * 删除角色，同时删除用户角色和角色关联的菜单
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 角色分配菜单
     *
     * @param id
     * @param integers
     */
    void grantMenu(Integer id, List<Integer> integers);
}
