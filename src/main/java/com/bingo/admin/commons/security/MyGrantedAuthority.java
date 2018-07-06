package com.bingo.admin.commons.security;

import com.bingo.admin.entity.Menu;
import org.springframework.security.core.GrantedAuthority;

public class MyGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = 320L;

    private String authority;

    private Menu menu;

    public MyGrantedAuthority(Menu menu) {
        this.setAuthority(menu.getPerms());
        this.setMenu(menu);
    }

    public boolean equals(Object obj) {
        if(obj instanceof String) {
            return obj.equals(this.authority);
        } else if(obj instanceof GrantedAuthority) {
            GrantedAuthority attr = (GrantedAuthority)obj;
            return this.authority.equals(attr.getAuthority());
        } else {
            return false;
        }
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }


    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int hashCode() {
        return this.getAuthority().hashCode();
    }

    public String toString() {
        return this.getAuthority();
    }
}