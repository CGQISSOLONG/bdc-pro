package com.bdc.Impl;

import com.bdc.dao.MenuDao;
import com.bdc.dao.RoleMenuDao;
import com.bdc.entity.Menu;
import com.bdc.service.MenuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public Menu findMenuById(Integer id) {
        return null;
    }

    @Override
    public List<Menu> findListByMenuIds(List<Integer> menuIds) {
        return null;
    }

    @Override
    public Menu findMenuByExample(Menu menu) {
        return null;
    }

    @Override
    public void save(Menu menu) {

    }

    @Override
    public List<Menu> findAll() {
        return null;
    }

    @Override
    public List<Menu> findList(Menu menu) {
        return null;
    }

    @Override
    public void delete(Menu menu) {

    }

    @Override
    public void delete(List<Menu> menus) {

    }


    /**
     *
     * @param menu
     * @return
     */
    @Override
    public Menu create(Menu menu) {

       // menuRepository.save(menu);
        menuDao.create(menu);
        return menu;
    }


    @Override
    public Menu modify(Menu menu) {
        menuDao.modify(menu);
        return menu;
    }

    @Override
    public List<Menu> getNavMenus(Integer userId) {
        List<Menu> list = null;
        //超级管理员
       /* if (SecurityUtil.isRoot()) {
            list = menuRepository.findAll();
        } else {
            //子管理员
            list = MenuDao.findMenuListById(userId);
        }*/
        return (List<Menu>) Menu.buildTree(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        //删除菜单
        menuDao.deleteById(id);
        //删除角色菜单
        roleMenuDao.deleteByMenuId(id);
    }

    /**
     * 更新菜单状态
     * @param id
     * @param disable
     */
    @Override
    public void updateStateById(@Param("id")Integer id, @Param("disabled")String disable) {
        menuDao.updateStateById(id, disable);
    }

    /**
     * 菜单列表
     * @return
     */
    @Override
    public List<Menu> list() {
        return menuDao.findAlls();
    }
}
