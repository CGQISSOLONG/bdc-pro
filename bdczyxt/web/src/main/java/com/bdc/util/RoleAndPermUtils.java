package com.bdc.util;

import com.bdc.entity.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleAndPermUtils {
    public static List<Integer> parseUserRolesGetRoleIds(List<UserRole> userRoles){
        List<Integer> roleIds = new ArrayList<Integer>();
        for(UserRole userRole:userRoles){
            roleIds.add(userRole.getRoleId());
        }
        return roleIds;
    }

    public static Set<String> parseRolesGetRoleNames(List<Role> roles){
        Set<String> roleNameSet = new HashSet<String>();
        for(Role role:roles){
            roleNameSet.add(role.getRoleName());
        }
        return roleNameSet;
    }

    public static List<Integer> parseRoleMenusGetMenuIds(List<RoleMenu> roleMenus){
        List<Integer> menuIds = new ArrayList<Integer>();
        for(RoleMenu roleMenu:roleMenus){
            menuIds.add(roleMenu.getMenuId());
        }
        return menuIds;
    }

    public static List<Integer> parseUserMenuGetMenuIds(List<UserMenu> userMenus){
        List<Integer> menuIds = new ArrayList<Integer>();
        for(UserMenu userMenu:userMenus){
            menuIds.add(userMenu.getMenuId());
        }
        return menuIds;
    }

    public static List<String> parseMenuGetPermNames(List<Menu> menus){
        List<String> perms = new ArrayList<String>();
        for(Menu menu:menus){
            //perms.add(menu.getPermissionName());
        }
        return perms;
    }

}
