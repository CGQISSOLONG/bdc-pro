package com.bdc.Impl;

import com.bdc.dao.UserRoleDao;
import com.bdc.entity.UserRole;
import com.bdc.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;


    @Override
    public List<UserRole> findbyname(String username) {
        return userRoleDao.findbyusername(username);
    }

    @Override
    public UserRole findByExample(UserRole userRole) {
        return userRoleDao.findByExample(userRole);
    }

    @Override
    public List<UserRole> findByUserId(Integer userId) {
        return userRoleDao.findById(userId);
    }

    @Override
    public void save(UserRole userRole) {

    }

    @Override
    public List<UserRole> findAll() {
        return null;
    }

    @Override
    public List<UserRole> findList(UserRole userRole) {
        return null;
    }

    @Override
    public void delete(UserRole userRole) {

    }

    @Override
    public void delete(List<UserRole> userRoles) {

    }
}
