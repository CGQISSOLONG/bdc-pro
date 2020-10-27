package com.bdc.service;

import com.bdc.entity.UserMenu;

import java.util.List;

public interface UserMenuService {

        UserMenu findByExample(UserMenu userMenu);

        List<UserMenu> findListByUserId(Integer userId);

        void save(UserMenu userMenu);

        List<UserMenu> findAll();

        List<UserMenu> findList(UserMenu userMenu);

        void delete(UserMenu userMenu);

        void delete(List<UserMenu> userMenus);

}
