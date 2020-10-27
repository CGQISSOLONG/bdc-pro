package com.admin.service.impl;

import com.admin.controller.admin.vo.SelectRoleVo;
import com.admin.dao.primary.AdminRoleDao;
import com.admin.dao.primary.AdminUserDao;
import com.admin.dao.primary.AdminUserRoleDao;
import com.admin.entity.primary.AdminUser;
import com.admin.security.SecurityUtil;
import com.admin.service.AdminUserService;

import com.bdc.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@CacheConfig(cacheNames = "adminUser")
public class AdminUserServiceImpl implements AdminUserService {


    @Autowired
    protected RoleSelectServices roleSelectServices;

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Autowired
    private AdminUserRoleDao adminUserRoleDao;

    @Autowired
    private AdminUserDao adminUserDao;


    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @Override
    @Caching(
            put = @CachePut(key = "#user.id"),
            evict = @CacheEvict(key = "'list'")
    )
    public int create(AdminUser user) {
        final Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dates = simpleDateFormat.format(date);
        user.setCreateTime(dates);
        user.setUserType(Constants.USER_TYPE_ADMIN);
        user.setStatus(Constants.USER_STATUS_DISABLE);
        //密码加盐处理
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(user.getPassword());
        user.setPassword(encode);
        return adminUserDao.save(user);
    }

    @Caching(
            put = @CachePut(key = "#user.id"),
            evict = @CacheEvict(key = "'list'")
    )
    @Override
    public void modify(AdminUser user) {
        adminUserDao.modify(user);
    }

    /**
     * 更新状态
     * @param id
     * @param status
     */
    @Override
    @Caching(
            put = @CachePut(key = "#id"),
            evict = @CacheEvict(key = "'list'")
    )
    public void updateStatusById(Integer id, Integer status) {
        adminUserDao.updateStatusById(id, status);
    }


    /**
     * 保存用户角色信息
     *
     * @param userId
     * @param roleIds
     */
    @Caching(
            evict = {@CacheEvict(value = "user-nav-menu", key = "#userId")}
    )
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantRole(Integer userId, List<Integer> roleIds) {
        //删除用户角色
        adminUserRoleDao.deleteByUserId(userId);
        //插入新角色
        if (roleIds.size() > 0) {
            adminUserRoleDao.insertBatch(userId, roleIds);
        }
    }

    /**
     * 获取用户角色和没有分配的角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<SelectRoleVo> selectRoles(Integer userId) {
        return roleSelectServices.mergeRole(adminRoleDao.findAll(), adminRoleDao.findRolesById(userId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Caching(
            evict = {@CacheEvict(key = "#id"),@CacheEvict(key = "'list'")}
    )
    public void delete(Integer id) {
        //删除用户
        adminUserDao.delete(id);
        //删除用户关联角色
        adminUserRoleDao.deleteByUserId(id);
    }

    /**
     * 用户列表
     * @return
     */
    @Override
    @Cacheable(key = "'list'")
    public List<AdminUser> list() {
//        return userRepository.findUserByUserTypeNotAndIdNot(
//////                Constants.USER_TYPE_SUPER_ADMIN, SecurityUtil.getUserId());
        return adminUserDao.findUserByUserTypeNotAndIdNot(Constants.USER_TYPE_SUPER_ADMIN, SecurityUtil.getUserId());
    }
}
