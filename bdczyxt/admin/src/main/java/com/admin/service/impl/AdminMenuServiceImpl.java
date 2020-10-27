package com.admin.service.impl;

import com.admin.dao.primary.AdminMenuDao;
import com.admin.dao.primary.AdminRoleMenuDao;
import com.admin.entity.primary.AdminMenu;
import com.admin.security.SecurityUtil;
import com.admin.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = "menulist")
public class AdminMenuServiceImpl implements AdminMenuService {





    @Autowired
    private AdminMenuDao adminMenuDao;

    @Autowired
    private AdminRoleMenuDao adminRoleMenuDao;

    /**
     *
     * @param menu
     * @return
     */
    @Caching(
            put = @CachePut(key = "#menu.id"),
            evict = {@CacheEvict(key = "'list'"),@CacheEvict(value = "user-nav-menu", allEntries = true)}
    )
    @Override
    public AdminMenu create(AdminMenu menu) {
        adminMenuDao.save(menu);
        return menu;
    }

    @Caching(
            put = @CachePut(key = "#menu.id"),
            evict = {@CacheEvict(key = "'list'"),@CacheEvict(value = "user-nav-menu", allEntries = true)}
    )
    @Override
    public AdminMenu modify(AdminMenu menu) {
        adminMenuDao.save(menu);
        return menu;
    }

    @Cacheable(value = "user-nav-menu")
    @Override
    public List<AdminMenu> getNavMenus(Integer userId) {
        List<AdminMenu> list = null;
        //超级管理员
        if (SecurityUtil.isRoot()) {
            list = adminMenuDao.findAll();
        } else {
            //子管理员
            list = adminMenuDao.findMenuListById(userId);
        }
        return (List<AdminMenu>) AdminMenu.buildTree(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Caching(
            evict = {@CacheEvict(key = "'list'"),@CacheEvict(key = "#id"),@CacheEvict(value = "user-nav-menu", allEntries = true)}
    )
    public void delete(Integer id) {
        //删除菜单
        adminMenuDao.delete(id);
        //删除角色菜单
        adminRoleMenuDao.deleteByMenuId(id);
    }

    /**
     * 更新菜单状态
     * @param id
     * @param disable
     */
    @Override
    @Caching(
            put = @CachePut(key = "#id"),
            evict = {@CacheEvict(key = "'list'"),@CacheEvict(value = "user-nav-menu", allEntries = true)}
    )
    public void updateStateById(Integer id, String disable) {
        adminMenuDao.updateStateById(id, disable);
    }

    /**
     * 菜单列表
     * @return
     */
    @Override
    @Cacheable(key = "'list'")
    public List<AdminMenu> list() {
        return adminMenuDao.findAll();
    }
}
