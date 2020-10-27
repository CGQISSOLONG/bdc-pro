package com.bdc.dao;

import com.bdc.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleDao {
    UserRole findByExample(UserRole userRole);

    List<UserRole> findbyusername(String username);

    List<UserRole> findById(Integer userId);

    void save(UserRole userRole);

    List<UserRole> findAlls();

    List<UserRole> findList(UserRole userRole);

    void deleteByRoleId(Integer id);

    void delete(UserRole userRole);

    void delete(List<UserRole> userRoles);
    void deleteByUserId(Integer userId);

    void insertBatch(@Param("userId") Integer userId, @Param("list") List<Integer> roleIds);

}
