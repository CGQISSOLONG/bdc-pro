package com.bdc.service;

import com.bdc.entity.UserRole;

import java.util.List;

public interface UserRoleService {
    List<UserRole> findbyname(String username);

    UserRole findByExample(UserRole userRole);

    List<UserRole> findByUserId(Integer userId);

    void save(UserRole userRole);

    List<UserRole> findAll();

    List<UserRole> findList(UserRole userRole);

    void delete(UserRole userRole);

    void delete(List<UserRole> userRoles);

}
