package com.bdc.Impl;

import com.bdc.dao.RoleMenuDao;
import com.bdc.entity.RoleMenu;
import com.bdc.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public List<RoleMenu> findListByRoleIds(List<Integer> roleIds) {
        return roleMenuDao.findListByRoleIds(roleIds);
    }

    @Override
    public void deleteByRoleId(Integer id) {

    }

    @Override
    public void deleteByMenuId(Long id) {

    }

    @Override
    public void insertBatch(Integer roleId, List<Integer> menuIds) {

    }
}
