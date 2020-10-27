package com.bdc.Impl;

import com.bdc.entity.UserMenu;
import com.bdc.service.UserMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMenuServiceImpl implements UserMenuService {
    @Override
    public UserMenu findByExample(UserMenu userMenu) {
        return null;
    }

    @Override
    public List<UserMenu> findListByUserId(Integer userId) {
        return null;
    }

    @Override
    public void save(UserMenu userMenu) {

    }

    @Override
    public List<UserMenu> findAll() {
        return null;
    }

    @Override
    public List<UserMenu> findList(UserMenu userMenu) {
        return null;
    }

    @Override
    public void delete(UserMenu userMenu) {

    }

    @Override
    public void delete(List<UserMenu> userMenus) {

    }

}
