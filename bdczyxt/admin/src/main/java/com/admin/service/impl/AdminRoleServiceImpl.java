package com.admin.service.impl;

import com.admin.controller.admin.vo.SelectResourceVo;
import com.admin.dao.primary.AdminRoleDao;
import com.admin.dao.primary.AdminRoleMenuDao;
import com.admin.dao.primary.AdminUserRoleDao;
import com.admin.entity.primary.AdminRole;
import com.admin.service.AdminRoleService;
import com.bdc.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author 创建人：laill
 * @version 版本号：V1.0
 * <p>
 * **************************修订记录***************************************
 * <p>
 * 2019年06月05日 laill 创建该类功能。
 * <p>
 * ***********************************************************************
 * </p>
 * @ClassName 类名：
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年06月03日
 */
@Service
@CacheConfig(cacheNames = "role")
public class AdminRoleServiceImpl implements AdminRoleService {


    @Autowired
    protected ResourceSelectService resourceSelectService;


    @Autowired
    private AdminRoleMenuDao adminRoleMenuDao;

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Autowired
    private AdminUserRoleDao adminUserRoleDao;


    @Caching(
            evict = @CacheEvict(key = "'list'"),
            put = @CachePut(key = "#role.id")
    )
    @Override
    public AdminRole create(AdminRole role) {
        Assert.hasText(role.getRoleName(),"AdminRole name is empty");
        final Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        role.setCreateTime(dateString);
        role.setUpdateTime(dateString);
        role.setStatus(Constants.ROLE_STATUS_DISABLE);
        adminRoleDao.save(role);
        return role;
    }

    @Caching(
            evict = @CacheEvict(key = "'list'"),
            put = @CachePut(key = "#newRole.id")
    )
    @Override
    public void modify(AdminRole newRole) {
        Assert.hasText(newRole.getId()+"","AdminRole id is empty");
        Assert.hasText(newRole.getRoleName(),"AdminRole name is empty");
        adminRoleDao.save(newRole);
    }

    /**
     * 更新状态
     * @param id
     * @param state
     */
    @Override
    @Caching(
            evict = @CacheEvict(key = "'list'"),
            put = @CachePut(key = "#id")
    )
    public void updateStateById(Integer id, Integer state) {
        adminRoleDao.updateStateById(id, state);
    }

    @Cacheable(key = "'list'")
    @Override
    public List<AdminRole> list(){
        return adminRoleDao.findAll();
    }


    @Caching(
            evict = {@CacheEvict(key = "'list'"), @CacheEvict(key = "#id")}
    )
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id){
        //删除角色
        adminRoleDao.delete(id);
        //删除角色菜单
        adminRoleMenuDao.deleteByRoleId(id);
        //删除用户角色
        adminUserRoleDao.deleteByRoleId(id);
    }

    @Override
    public void grantResource(String roleId, List<String> resources){
        //roleRepository.updateResources(roleId, resources);
    }


    @CacheEvict(value = "user-nav-menu", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void grantMenu(Integer roleId, List<Integer> menuIds){
        //删除
        adminRoleMenuDao.delete(roleId);
        //插入
        if (menuIds.size()>0) {
            adminRoleMenuDao.insertBatch(roleId, menuIds);
        }
    }

    @Override
    public List<SelectResourceVo> selectResources(String roleId) {
        return null;
        //return resourceSelectService.mergeResource(resourceRepository.findAll(), resourceRepository.listByRole(roleId));
    }

}
