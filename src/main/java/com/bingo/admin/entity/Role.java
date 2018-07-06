package com.bingo.admin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.*;

@Table(name = "acc_role")
@Entity
public class Role extends BaseEntity{
    /** 角色名 */
    private String roleName;
    /** 角色权限 */
    private String roleKey;
    /** 角色排序 */
    private String roleSort;
    /** 角色状态:0正常,1禁用 */
    private int status=0;
    /** 备注 */
    private String remark;
    /** 用户是否存在此角色标识 默认不存在 */
    private boolean flag = false;
    /** 菜单组 */
    private Set<Menu> menus =new LinkedHashSet<>();

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(String roleSort) {
        this.roleSort = roleSort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @ManyToMany
    @JoinTable(name = "ACC_ROLE_MENU", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "MENU_ID") })
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id")
    @JsonIgnore
    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    @Transient
    public void setMenusByArray(String[] menuIds){
        if(menuIds.length>0){
            for (String menuId:menuIds) {
                if (menuId!=null&&menuId.length()>0){
                    Menu m=new Menu();
                    m.setId(Long.valueOf(menuId));
                    this.menus.add(m);
                }
            }
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != role.id) return false;
        if (status != role.status) return false;
        if (flag != role.flag) return false;
        if (roleName != null ? !roleName.equals(role.roleName) : role.roleName != null) return false;
        if (roleKey != null ? !roleKey.equals(role.roleKey) : role.roleKey != null) return false;
        return roleSort != null ? roleSort.equals(role.roleSort) : role.roleSort == null;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (roleKey != null ? roleKey.hashCode() : 0);
        result = 31 * result + (roleSort != null ? roleSort.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (flag ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                ", roleKey='" + roleKey + '\'' +
                ", roleSort='" + roleSort + '\'' +
                ", status=" + status +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", flag=" + flag +
                ", menus=" + menus +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", lastModifyTime=" + lastModifyTime +
                ", lastModifyBy='" + lastModifyBy + '\'' +
                ", id=" + id +
                ", sessCode=" + sessCode +
                '}';
    }
}
