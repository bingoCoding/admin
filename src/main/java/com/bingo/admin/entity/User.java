package com.bingo.admin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Table(name = "acc_user")
@Entity
public class User extends BaseEntity{

    /** 部门ID */
    private Long deptId;
    /** 部门父ID */
    private Long parentId;
    /** 登录名 */
    private String loginName;
    /** 用户名称 */
    private String userName;
    /** 用户邮箱 */
    private String email;
    /** 手机号码 */
    private String phone;
    /** 用户性别 */
    private String sex;
    /** 用户头像 */
    private String avatar;
    /** 密码 */
    private String password;
    /** 盐加密 */
    private String salt;
    /** 类型:Y默认用户,N非默认用户 */
    private String userType;
    /** 帐号状态:0正常,1禁用 */
    private int status=0;
    /** 拒绝登录描述 */
    private String refuseDes;
    /** 角色组 */
    private List<Role> useRoles = new LinkedList<Role>();

    private Domain domain;

    private boolean enabled=true;// 账户可用
    private boolean accountExpired=true;// 帐号过期
    private boolean accountLocked=true;// 帐号锁定
    private String lockRemark;
    private boolean credentialsExpired=true;// 证书过期

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRefuseDes() {
        return refuseDes;
    }

    public void setRefuseDes(String refuseDes) {
        this.refuseDes = refuseDes;
    }

    @ManyToMany
    @JoinTable(name = "ACC_USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id")
    @JsonIgnore
    public List<Role> getUseRoles() {
        return useRoles;
    }

    public void setUseRoles(List<Role> useRoles) {
        this.useRoles = useRoles;
    }

    @ManyToOne
    @JoinColumn(name = "deptId", insertable = false, updatable = false, nullable=true)
    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public String getLockRemark() {
        return lockRemark;
    }

    public void setLockRemark(String lockRemark) {
        this.lockRemark = lockRemark;
    }

    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    @Transient
    public void setUserRolesByArray(String[] roleIds){
        if(roleIds.length>0){
            for (String roleId:roleIds) {
                if (roleId!=null&&roleId.length()>0){
                    Role r=new Role();
                    r.setId(Long.valueOf(roleId));
                    this.useRoles.add(r);
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (status != user.status) return false;
        if (deptId != null ? !deptId.equals(user.deptId) : user.deptId != null) return false;
        if (parentId != null ? !parentId.equals(user.parentId) : user.parentId != null) return false;
        if (loginName != null ? !loginName.equals(user.loginName) : user.loginName != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
        if (avatar != null ? !avatar.equals(user.avatar) : user.avatar != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (salt != null ? !salt.equals(user.salt) : user.salt != null) return false;
        if (userType != null ? !userType.equals(user.userType) : user.userType != null) return false;
        if (refuseDes != null ? !refuseDes.equals(user.refuseDes) : user.refuseDes != null) return false;
        return useRoles != null ? useRoles.equals(user.useRoles) : user.useRoles == null;
    }

    @Override
    public int hashCode() {
        int result = deptId != null ? deptId.hashCode() : 0;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (refuseDes != null ? refuseDes.hashCode() : 0);
        result = 31 * result + (useRoles != null ? useRoles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "deptId=" + deptId +
                ", parentId=" + parentId +
                ", loginName='" + loginName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", userType='" + userType + '\'' +
                ", status=" + status +
                ", refuseDes='" + refuseDes + '\'' +
                ", useRoles=" + useRoles +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", lastModifyTime=" + lastModifyTime +
                ", lastModifyBy='" + lastModifyBy + '\'' +
                ", id=" + id +
                ", sessCode=" + sessCode +
                '}';
    }
}
