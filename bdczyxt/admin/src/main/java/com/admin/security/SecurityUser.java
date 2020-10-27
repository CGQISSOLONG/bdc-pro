package com.admin.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class SecurityUser extends User {
    private Integer userId;
    private String email;
    private String account;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "SecurityUser{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", account='" + account + '\'' +
                '}';
    }

    public SecurityUser(Integer userId, String username, String password, boolean enabled,
                        boolean accountNonExpired, boolean credentialsNonExpired,
                        boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
                        String email) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId=userId;
        this.email = email;
    }
}
