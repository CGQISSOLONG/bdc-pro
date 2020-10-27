package com.bdc.service;

import com.bdc.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MenuService {
    Menu findMenuById(Integer id);

    List<Menu> findListByMenuIds(List<Integer> menuIds);

    Menu findMenuByExample(Menu menu);

    void save(Menu menu);

    List<Menu> findAll();

    List<Menu> findList(Menu menu);

    void delete(Menu menu);

    void delete(List<Menu> menus);

    /**
     *  创建菜单
     * @param menu
     * @return
     */
    Menu create(Menu menu);

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    Menu modify(Menu menu);

    /**
     * 获取用户菜单
     * @param userId
     * @return
     */
    List<Menu> getNavMenus(Integer userId);

    /**
     * 删除菜单，同时删除角色关联该菜单
     * @param id
     */
    void delete(Integer id);

    /**
     * 更新菜单状态
     * @param id
     * @param disable
     */
    void updateStateById(@Param("id")Integer id, @Param("disabled")String disable);

    /**
     * 查询菜单列表
     * @return
     */
    List<Menu> list();
}
