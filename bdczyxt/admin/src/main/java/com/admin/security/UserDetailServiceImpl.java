package com.admin.security;


import com.admin.dao.primary.AdminRoleDao;
import com.admin.dao.primary.AdminUserDao;
import com.admin.entity.primary.AdminRole;
import com.admin.entity.primary.AdminUser;
import com.bdc.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AdminUserDao adminUserDao;

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        List<AdminUser> user = adminUserDao.findUserByAccount(account);
        if (user.size()!=1) {
            throw new UsernameNotFoundException("用户不存在！！！");
        }
        final AdminUser adminUser = user.get(0);
        SecurityUser userDetails = new SecurityUser(adminUser.getId(), account,
                adminUser.getPassword(), adminUser.getStatus() == 1 ? true : false,
                true, true, true,
                grantedAuthorities(adminUser.getId()), adminUser.getEmail());
        return userDetails;
    }

    protected Collection<GrantedAuthority> grantedAuthorities(Integer userId) {
        List<AdminRole> resources=adminRoleDao.findRolesById(userId);
        if (CollectionUtils.isEmpty(resources)) {
            return new ArrayList<>();
        }
        Collection<GrantedAuthority> authorities = new HashSet<>();
        //忽略已经禁用的角色
        resources.stream().filter(role -> !Constants.ROLE_STATUS_DISABLE.equals(role.getStatus())).forEach((resource -> {
            authorities.add(new SimpleGrantedAuthority(resource.getRoleName()));
        }));
        return authorities;
    }

}
