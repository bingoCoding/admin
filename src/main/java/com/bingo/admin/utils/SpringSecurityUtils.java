package com.bingo.admin.utils;

import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.bingo.admin.commons.security.AuthUser;
import com.bingo.admin.entity.Menu;
import com.bingo.admin.entity.User;
import com.bingo.admin.service.impl.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;


/**
 * SpringSecurity的工具类
 * 
 */
public class SpringSecurityUtils {

	protected final static Log logger = LogFactory.getLog(SpringSecurityUtils.class);
    /**
     * 取得当前用户, 返回值为SpringSecurity的User类或其子类, 如果当前用户未登录则返回null.
     */
    @SuppressWarnings("unchecked")
    public static <T extends AuthUser> T getCurrentUser() {
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof AuthUser) {
            	
                return (T) principal;
            }
        }
        return null;
    }
    /**
     * 获取权限菜单
     */
    @SuppressWarnings("unchecked")
    public static Set<Menu> getCurrentUserMenus() {
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof AuthUser) {

                return ((AuthUser) principal).getMenus();
            }
        }
        return null;
    }

    /**
     * 取得当前用户, 返回值为SpringSecurity的User类或其子类, 如果当前用户未登录则返回null.
     * 
     */
    @SuppressWarnings("unchecked")
    public static <T extends User> T getCurrentCoreUser() {
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof AuthUser) {
            	return (T)loadUserFromDb(((AuthUser) principal).getId());
            }
        }
        return null;
    }

    /**
     * 从数据库中装载用户
     * @param userId
     * @return
     */
    private static User loadUserFromDb(Long userId){
        UserService userService = SpringUtils.getBean(UserService.class);
    	return userService.getUserById(userId);
    }
    
    /**
     * 取得当前用户的登录名, 如果当前用户未登录则返回空字符
     */
    public static String getCurrentUserName() {
        Authentication authentication = getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return authentication.getName();
        }
        return "";
    }

    /**
     * 取得当前用户登录IP, 如果当前用户未登录则返回空字符串.
     */
    public static String getCurrentUserIp() {
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            Object details = authentication.getDetails();
            if (details instanceof WebAuthenticationDetails) {
                WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
                return webDetails.getRemoteAddress();
            }
        }

        return "";
    }

    /**
     * 判断用户是否拥有角色, 如果用户拥有参数中的任意一个角色则返回true.
     */
    public static boolean hasAnyRole(String[] roles) {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return false;
        }

        Collection<GrantedAuthority> granteds = (Collection<GrantedAuthority>) authentication.getAuthorities();
        for (String role : roles) {
            for (GrantedAuthority authority : granteds) {
                if (role.equals(authority.getAuthority())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 将UserDetails保存到Security Context.
     */
    public static void saveUserDetailsToContext(UserDetails userDetails, HttpServletRequest request) {
        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(userDetails,
                userDetails.getPassword(), userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * 取得Authentication, 如当前SecurityContext为空时返回null.
     */
    private static Authentication getAuthentication() {
    	SecurityContext context = SecurityContextHolder.getContext();
        Authentication ret = null;
        if (context != null) {
        	ret = context.getAuthentication();
        }
        if(logger.isDebugEnabled()){
        	if(ret == null){
        		logger.debug("return Authentication is : null");
        	}else{
        	    logger.debug("return Authentication type is : " + ret.getClass().getName() +" , name is : "+ ret.getName());
        	}
         }
        return ret;
    }
}
