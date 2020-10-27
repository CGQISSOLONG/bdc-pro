package com.admin.dao.primary;

import com.admin.entity.primary.AdminUserRole;
import com.bdc.common.base.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 创建人：laill
 * @version 版本号：V1.0
 * <p>
 * **************************          修订记录***************************************
 * <p>
 * 2019年06月12日 laill 创建该类功能。
 * <p>
 * ***********************************************************************
 * </p>
 * @ClassName 类名：AdminUserRoleDao
 * @Description 功能说明：后台用户角色dao
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年06月12日
 */
@Mapper
@Repository
public interface AdminUserRoleDao extends CrudDao<AdminUserRole> {

    /**
     * 删除用户角色
     * @param userId
     */
    void deleteByUserId(Integer userId);

    /**
     * 批量插入角色
     * @param userId
     * @param roleIds
     */
    void insertBatch(@Param("userId") Integer userId, @Param("list") List<Integer> roleIds);

    /**
     * 删除用户角色
     * @param id
     */
    void deleteByRoleId(Integer id);
}
