package com.bdc.conponment.shiro;

import com.bdc.common.Constants;
import com.bdc.entity.*;
import com.bdc.service.*;
import com.bdc.util.JWTUtil;
import com.bdc.util.RoleAndPermUtils;
import com.bdc.util.SpringContextHolder;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PrimaryRealm extends AuthorizingRealm {

    private UserService userService;
    private RoleService roleService;
    private UserRoleService userRoleService;
    private RoleMenuService roleMenuService;
    private MenuService menuService;
    private UserMenuService userMenuService;


    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        this.serviceInit();
        System.out.println("————身份认证方法————");
       String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        if (Constants.IS_PASS) {
          return new SimpleAuthenticationInfo(token,token,this.getName());
        }

        /* 以下数据库查询可根据实际情况，可以不必再次查询，这里我两次查询会很耗资源
         * 我这里增加两次查询是因为考虑到数据库管理员可能自行更改数据库中的用户信息
         */
        //String username = JWTUtil.getUsername(token);
        String userId = JWTUtil.getUserIdByToken(token);
        if(userId == null){
      //  if (username == null) {
            throw new UnauthorizedException("无效令牌！");
        }
        Users user = null;
        if (user == null) {
            throw new UnauthorizedException("用户不存在！");
        }
        if(!JWTUtil.verify(token,userId,user.getPassword())){
       // if(!JWTUtil.verify(token,username,user.getPassword())){
            throw new UnauthorizedException("用户名或密码错误！");
        }
        return new SimpleAuthenticationInfo(token,token,this.getName());
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        this.serviceInit();
        System.out.println("————权限认证————");
        //String username = JWTUtil.getUsername(principals.toString());
        String userId = JWTUtil.getUserIdByToken(principals.toString());
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        Users user = null;
        //List<UserRole> userRole = userRoleService.findbyname(user.getUsername());
        List<UserRole> userRoles= userRoleService.findByUserId(user.getId());
        //List<Integer> roleId = RoleAndPermUtils.parseUserRolesGetRoleIds(userRole);
        List<Integer> roleIds = RoleAndPermUtils.parseUserRolesGetRoleIds(userRoles);
        List<Integer> menuIds = new ArrayList<Integer>();
        //每个用户可以设置新的权限
        Set<String> roleSet = new HashSet<>();
        if(roleIds != null && roleIds.size() != 0){
            List<Role> roles = roleService.findListByRoleIds(roleIds);
            roleSet = RoleAndPermUtils.parseRolesGetRoleNames(roles);

            List<RoleMenu> roleMenus = roleMenuService.findListByRoleIds(roleIds);
            List<Integer> roleOfMenus = RoleAndPermUtils.parseRoleMenusGetMenuIds(roleMenus);
            menuIds.addAll(roleOfMenus);
        }
        List<UserMenu> userMenus = userMenuService.findListByUserId(user.getId());
        List<Integer> userOfMenus = RoleAndPermUtils.parseUserMenuGetMenuIds(userMenus);
        menuIds.addAll(userOfMenus);

        List<Menu> menuList = new ArrayList<Menu>();
        if(menuIds != null && menuIds.size() != 0){
            menuList = menuService.findListByMenuIds(menuIds);
        }
        Set<String> permissionSet = new HashSet<String>(RoleAndPermUtils.parseMenuGetPermNames(menuList));

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roleSet);//用户角色设置
        simpleAuthorizationInfo.setStringPermissions(permissionSet);//用户菜单权限设置

        return simpleAuthorizationInfo;
    }
    private void serviceInit(){
        if(userService == null){
            this.userService = SpringContextHolder.getBean(UserService.class);
        }
        if(userMenuService == null){
            this.userMenuService = SpringContextHolder.getBean(UserMenuService.class);
        }
        if(roleService == null){
            this.roleService = SpringContextHolder.getBean(RoleService.class);
        }
        if(roleMenuService == null){
            this.roleMenuService = SpringContextHolder.getBean(RoleMenuService.class);
        }
        if(userRoleService == null){
            this.userRoleService = SpringContextHolder.getBean(UserRoleService.class);
        }
        if(menuService == null){
            this.menuService = SpringContextHolder.getBean(MenuService.class);
        }
    }
}
