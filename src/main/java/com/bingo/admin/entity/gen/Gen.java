package com.bingo.admin.entity.gen;

import com.bingo.admin.commons.gen.annotations.GeneratedClassInfo;
import com.bingo.admin.commons.gen.annotations.GeneratedInfo;
import com.bingo.admin.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "acc_gen")
@GeneratedClassInfo(label="自动生成")
public class Gen extends BaseEntity {
    /** 菜单名称 */
    @GeneratedInfo(label = "菜单名称")
    private String menuName;
    /** 父菜单名称 */
    @GeneratedInfo(label = "父菜单名称")
    private String parentName;
    /** 父菜单ID */
    @GeneratedInfo(label = "父菜单ID")
    private Long parentId;
    /** 显示顺序 */
    @GeneratedInfo(label = "显示顺序")
    private String orderNum;
    /** 菜单URL */
    @GeneratedInfo(label = "菜单URL")
    private String url;
    /** 类型:0目录,1菜单,2按钮 */
    @GeneratedInfo(label = "类型")
    private String menuType;
    /** 菜单状态:0显示,1隐藏 */
    private int visible=0;
    /** 权限字符串 */
    @GeneratedInfo(label = "权限字符串")
    private String perms;
    /** 菜单图标 */
    private String icon;
    /** 备注 */
    private String remark;


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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gen menu = (Gen) o;

        if (id!=menu.id) return false;
        if (menuName != null ? !menuName.equals(menu.menuName) : menu.menuName != null) return false;
        if (parentName != null ? !parentName.equals(menu.parentName) : menu.parentName != null) return false;
        if (parentId != null ? !parentId.equals(menu.parentId) : menu.parentId != null) return false;
        if (orderNum != null ? !orderNum.equals(menu.orderNum) : menu.orderNum != null) return false;
        if (url != null ? !url.equals(menu.url) : menu.url != null) return false;
        if (menuType != null ? !menuType.equals(menu.menuType) : menu.menuType != null) return false;
        return perms != null ? perms.equals(menu.perms) : menu.perms == null;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (menuName != null ? menuName.hashCode() : 0);
        result = 31 * result + (parentName != null ? parentName.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (orderNum != null ? orderNum.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (menuType != null ? menuType.hashCode() : 0);
        result = 31 * result + visible;
        result = 31 * result + (perms != null ? perms.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
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
