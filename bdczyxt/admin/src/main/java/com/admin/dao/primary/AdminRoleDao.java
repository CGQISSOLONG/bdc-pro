package com.admin.dao.primary;


import com.admin.entity.primary.AdminRole;
import com.bdc.common.base.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName 类名：AdminRoleDao
 * @Description 功能说明：后台角色dao
 */
@Repository
@Mapper
public interface AdminRoleDao extends CrudDao<AdminRole> {

    /**
     * 根据用户id获取用户对应的角色
     * @param userId
     * @return
     */
    List<AdminRole> findRolesById(Integer userId);

    List<AdminRole> findAll();

    void updateStateById(@Param("id") Integer id, @Param("status") Integer status);

    void save(AdminRole newRole);

    AdminRole findOne(Integer roleId);
}
