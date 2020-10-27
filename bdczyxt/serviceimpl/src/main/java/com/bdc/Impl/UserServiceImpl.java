package com.bdc.Impl;

import com.bdc.dao.RoleDao;
import com.bdc.dao.UserDao;
import com.bdc.dao.UserRoleDao;
import com.bdc.dao.vo.SelectRoleVo;

import com.bdc.entity.Users;
import com.bdc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RoleDao roleDao;


    @Override
    public Users findByName(String account) {
        return userDao.findByName(account);
    }

    @Override
    public List<Users> findAll() {
        return userDao.findAll();
    }

    @Override
    public Users findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public void save(Users user) {
        userDao.save(user);
    }

    @Override
    public Users getPubUserByToken(String token) {
        return userDao.getPubUserByToken(token);
    }

    @Override
    public List<SelectRoleVo> selectRoles(Integer userId) {
        return RoleSelectService.mergeRole(roleDao.findAll(), roleDao.findRolesById(userId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantRole(Integer userId, List<Integer> roleIds) {
        //删除用户角色
        userRoleDao.deleteByUserId(userId);
        //插入新角色
        if (roleIds.size() > 0) {
            userRoleDao.insertBatch(userId, roleIds);
        }
    }
}
