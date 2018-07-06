package com.bingo.admin.commons.security;

import com.bingo.admin.dao.UserDao;
import com.bingo.admin.entity.Menu;
import com.bingo.admin.entity.Role;
import com.bingo.admin.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函数.
 * 
 * @author bingo
 */
@Component
@Transactional
public class CustomUserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(CustomUserService.class);
    @Autowired
    private UserDao userDao;

    /**
     * 获取用户Details信息的回调函数.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException, DataAccessException {
		User user = userDao.getUserByLoginName(loginName);

        if (user == null) {
            throw new UsernameNotFoundException("用户" + loginName + " 不存在");
        } else {
			Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();
			Set<Menu> menuSet=new LinkedHashSet<>();
			for (Role role : user.getUseRoles()) {
				Set<Menu> menus = role.getMenus();
                menuSet.addAll(menus);
				for (Iterator iterator = menus.iterator(); iterator.hasNext();) {
					Menu menu = (Menu) iterator.next();

					GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
                            menu.getPerms());
					authorities.add(grantedAuthority);
				}
			}
//			Set<Menu> all_authorities = new LinkedHashSet<Menu>();
//			for (Role role : user.getUseRoles()) {
//				List<LabelValue> userRoles = new ArrayList<LabelValue>();
//				if (this.authorities != null) {
//					for (Authority authority : authorities) {
//						// convert the user's roles to LabelValue Objects
//						userRoles.add(new LabelValue(authority.getLabel(), authority.getName()));
//					}
//				}
//				authorities.addAll(role.getAuthorities());
//			}

            // 在使用记住密码登录时 为非OpenSessionInViewFilter模式
        	AuthUser sUser = new AuthUser(user.getId() ,user.getLoginName(), user.getUserName(),
        			user.getPassword(),user.isEnabled(),user.isAccountExpired(),user.isCredentialsExpired(),
        			user.isAccountLocked(),authorities,menuSet);
        	
            logger.info("登录成功,用户{}", sUser);
            return sUser;
        }

    }


}
