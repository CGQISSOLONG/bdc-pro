package com.admin.dao.primary;

import com.admin.controller.admin.dto.TreeDto;
import com.admin.entity.primary.AdminMenu;

import com.bdc.common.base.CrudDao;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
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
 * @ClassName 类名：AdminMenuDao
 * @Description 功能说明：后台菜单dao
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年06月12日
 */
@Repository
@Mapper
public interface AdminMenuDao extends CrudDao<AdminMenu> {

    /**
     * 根据用户id获取用户对应的菜单
     * @param userId
     * @return
     */
    List<AdminMenu> findMenuListById(Integer userId);

    /**
     * 根据角色id获取菜单ids
     * @param id
     * @return
     */
    List<Integer> findMenuIdsByRoleId(Integer id);

    /**
     * 查找所有菜单
     * @return
     */
    List<TreeDto> findAllMenuList();

    void updateStateById(@Param("id") Integer id, @Param("disable") String disable);


    void save(AdminMenu menu);

    List<AdminMenu> findAll();

    AdminMenu findOne(Integer id);
}
