package com.bdc.dao;

import com.bdc.entity.Menu;
import com.bdc.entity.bdcTree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName 类名：MenuDao
 * @Description 功能说明：菜单Dao接口类
 */
@Mapper
public interface MenuDao{
//public interface MenuDao extends BaseMapper<Menu> {

    /**
     * 根据菜单ID查询菜单列表
     *
     * @param menuIds
     * @return
     */
    List<Menu> findListByMenuIds(@Param("menuIds") List<Integer> menuIds);

    /**
     * 获取角色拥有的菜单
     *
     * @param id
     * @return
     */
    List<Integer> findMenuIdsByRoleId(Integer id);

    /**
     * 获取所有的菜单
     *
     * @return
     */
    List<bdcTree> findAllMenuList();

    /**
     * @param id
     * @return
     */
    List<Menu> findMenubyUserId(Integer id);

    void deleteById(Integer id);

    List<Menu> findMenubyUserId2(@Param("id") Integer id, @Param("type") String type);

    List<Menu> findList();

    Menu create(Menu menu);

    void modify(Menu menu);


    @Transactional(rollbackFor = Exception.class)
    void updateStateById(@Param("id")Integer id, @Param("disabled")String disable);

    List<Menu> findAlls();

    Menu findOne(Integer id);

    void save(Menu menu);


    Menu getOne(Integer id);
}
