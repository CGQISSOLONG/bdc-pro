package com.admin.dao.primary;

import com.admin.entity.primary.AdminRoleMenu;

import com.bdc.common.base.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName 类名：AdminRoleMenuDao
 * @Description 功能说明：后台角色菜单dao
 */
@Mapper
@Repository
public interface AdminRoleMenuDao extends CrudDao<AdminRoleMenu> {

    /**
     * 批量插入
     * @param roleId
     * @param menuIds
     */
    void insertBatch(@Param("roleId") Integer roleId, @Param("list") List<Integer> menuIds);

    /**
     * 删除角色同时删除角色菜单
     * @param id
     */
    void deleteByRoleId(Integer id);

    /**
     * 删除角色菜单
     * @param id
     */
    void deleteByMenuId(Integer id);
}
