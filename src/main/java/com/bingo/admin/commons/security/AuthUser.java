package com.bingo.admin.commons.security;

import com.bingo.admin.entity.Menu;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;

public class AuthUser extends User {


	private static final long serialVersionUID = -8197411355750597660L;
	
	private Long id;
	private String realName;
	private Set<Menu> menus;

	public AuthUser(Long id , String username, String realName , String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,Set<Menu> menus) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.id = id;
		this.realName = realName;
		this.menus=menus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
}
