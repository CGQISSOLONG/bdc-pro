package com.bdc.Impl;

import com.bdc.dao.RoleDao;
import com.bdc.dao.RoleMenuDao;
import com.bdc.entity.Role;
import com.bdc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public Role findRoleById(Integer id) {
        return null;
    }

    @Override
    public Role findRoleByExample(Role role) {
        return null;
    }

    @Override
    public List<Role> findListByRoleIds(List<Integer> roleIds) {
        return roleDao.findListByRoleIds(roleIds);
    }

    @Override
    public void save(Role role) {
        roleDao.update(role);
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public List<Role> findList(Role role) {
        return null;
    }

    @Override
    public void delete(Role role) {

    }

    @Override
    public void delete(List<Role> roles) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantMenu(Integer roleId, List<Integer> menuIds){
        //删除
        roleMenuDao.deleteByRoleId(roleId);
        //插入
        if (menuIds.size()>0) {
            roleMenuDao.insertBatch(roleId, menuIds);
        }
    }

//    @Override
//    public List<Role> findListByRoleIds(List<Integer> roleIds) {
//        return roleDao.findListByRoleIds(roleIds);
//    }
//
//    @Override
//    public Role deleteById(Integer id) {
//        return roleDao.deleteById(id);
//    }
//
//    @Override
//    public List<Role> findRolesById(Integer userId) {
//        return roleDao.findRolesById(userId);
//    }
}
