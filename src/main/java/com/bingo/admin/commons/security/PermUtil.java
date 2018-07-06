package com.bingo.admin.commons.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

public class PermUtil {

    public static String hasPerm(String permission){
        return hasAuthories(permission) ? "" : "hidden";
    }

    /**
     * 判断用户是否拥有角色, 如果用户拥有参数中的任意一个角色则返回true.
     */
    public static boolean hasAuthories(String... auths) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = null;
        if (context != null) {
            authentication = context.getAuthentication();
        }
        if (authentication == null) {
            return false;
        }

        Collection<GrantedAuthority> granteds = (Collection<GrantedAuthority>) authentication.getAuthorities();
        for (String auth : auths) {
            for (GrantedAuthority authority : granteds) {
                if (auth.equals(authority.getAuthority())) {
                    return true;
                }
            }
        }
        return false;
    }
}
