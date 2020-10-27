package com.admin.security;

import com.admin.constants.AdminConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static Integer getUserId(){
        return getUser() == null ? null: getUser().getUserId();
    }

    public static SecurityUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        if(authentication.getPrincipal()!=null && authentication.getPrincipal() instanceof SecurityUser){
            return (SecurityUser) authentication.getPrincipal();
        }
        return null;
    }

    /**
     * 是否为超级管理员
     * @return
     */
    public static boolean isRoot() {
        return AdminConstants.ROOT_ACCOUNT.equals(getUser().getUsername());
    }
}
