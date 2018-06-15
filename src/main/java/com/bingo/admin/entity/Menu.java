package com.bingo.admin.entity;

import com.bingo.admin.utils.ReflectionUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "acc_menu")
public class Menu extends BaseEntity{
    /** 菜单名称 */
    private String menuName;
    /** 父菜单名称 */
    private String parentName;
    /** 父菜单ID */
    private Long parentId;
    /** 显示顺序 */
    private String orderNum;
    /** 菜单URL */
    private String url;
    /** 类型:0目录,1菜单,2按钮 */
    private String menuType;
    /** 菜单状态:0显示,1隐藏 */
    private int visible=0;
    /** 权限字符串 */
    private String perms;
    /** 菜单图标 */
    private String icon;
    /** 备注 */
    private String remark;

    /** 权限所需要的角色 */
    private Set<Role> authorities = new LinkedHashSet<Role>();

    private List<Menu> childList;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Transient
    public List<Menu> getChildList() {
        return childList;
    }

    public void setChildList(List<Menu> childList) {
        this.childList = childList;
    }

    @ManyToMany(mappedBy = "menus")
    @Fetch(FetchMode.JOIN)
    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @Transient
    public Collection<ConfigAttribute> getAuthNameCollection() {
        Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
        for (Role a : authorities) {
            ConfigAttribute ca = new SecurityConfig(a.getRoleName());
            atts.add(ca);
        }
        return atts;
    }

    @Transient
    public String getRoleNames() {
        return ReflectionUtils.convertElementPropertyToString(authorities,"roleName", ",");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (visible != menu.visible) return false;
        if (!menuName.equals(menu.menuName)) return false;
        if (!parentName.equals(menu.parentName)) return false;
        if (!parentId.equals(menu.parentId)) return false;
        if (!orderNum.equals(menu.orderNum)) return false;
        if (!url.equals(menu.url)) return false;
        if (!menuType.equals(menu.menuType)) return false;
        if (!perms.equals(menu.perms)) return false;
        if (!icon.equals(menu.icon)) return false;
        return remark.equals(menu.remark);
    }

    @Override
    public int hashCode() {
        int result = menuName.hashCode();
        result = 31 * result + parentId.hashCode();
        result = 31 * result + orderNum.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + menuType.hashCode();
        result = 31 * result + visible;
        result = 31 * result + perms.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuName='" + menuName + '\'' +
                ", parentName='" + parentName + '\'' +
                ", parentId=" + parentId +
                ", orderNum='" + orderNum + '\'' +
                ", url='" + url + '\'' +
                ", menuType='" + menuType + '\'' +
                ", visible=" + visible +
                ", perms='" + perms + '\'' +
                ", icon='" + icon + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", lastModifyTime=" + lastModifyTime +
                ", lastModifyBy='" + lastModifyBy + '\'' +
                ", id=" + id +
                ", sessCode=" + sessCode +
                '}';
    }
}
