package com.admin.dao.primary;


import com.admin.controller.admin.vo.SelectRoleVo;
import com.admin.entity.primary.AdminUser;
import com.bdc.common.base.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
 * @ClassName 类名：AdminUserDao
 * @Description 功能说明：后台用户dao
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年06月12日
 */
@Mapper
@Repository
public interface AdminUserDao extends CrudDao<AdminUser> {
    /**
     * 新增用户
     * @param user
     * @return
     */
    AdminUser create(AdminUser user);

    /**
     *  保存用户角色信息
     * @param userId
     * @param roleIds
     */
    void grantRole(Long userId, List<Integer> roleIds);

    /**
     * 获取用户所具有的角色和为分配的角色
     * @param userId
     * @return
     */
    List<SelectRoleVo> selectRoles(Integer userId);

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
    /**
     * 判断用户名称是否重复
     * @param username
     * @return
     */
    List<AdminUser> findUserByAccount(String username);


    List<AdminUser> findUserByEmail(String email);


    List<AdminUser> findUserByUserTypeNotAndIdNot(@Param("userType") Integer userType, @Param("id")Integer id);

    int save(AdminUser user);

    AdminUser findOne( Integer adminUserId);


    void update(@Param("password") String password, @Param("email") String email,
                @Param("date") Date date, @Param("id") Integer id);
}
