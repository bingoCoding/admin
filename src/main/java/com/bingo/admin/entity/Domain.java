package com.bingo.admin.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "acc_domain")
@Entity
public class Domain extends BaseEntity{
    /** 父部门ID */
    private Long parentId;
    /** 父部门名称 */
    private String parentName;
    /** 部门名称 */
    private String deptName;
    /** 显示顺序 */
    private String orderNum;
    /** 负责人 */
    private String leader;
    /** 联系电话 */
    private String phone;
    /** 邮箱 */
    private String email;

    /** 状态（0正常 1停用） */
    private int status=0;
    /** 用户是否存在此岗位标识 默认不存在 */
    private boolean flag = false;
    /** 备注 */
    private String remark;


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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

        Domain domain = (Domain) o;

        if (status != domain.status) return false;
        if (flag != domain.flag) return false;
        if (parentId != null ? !parentId.equals(domain.parentId) : domain.parentId != null) return false;
        if (parentName != null ? !parentName.equals(domain.parentName) : domain.parentName != null) return false;
        if (deptName != null ? !deptName.equals(domain.deptName) : domain.deptName != null) return false;
        if (orderNum != null ? !orderNum.equals(domain.orderNum) : domain.orderNum != null) return false;
        if (leader != null ? !leader.equals(domain.leader) : domain.leader != null) return false;
        if (phone != null ? !phone.equals(domain.phone) : domain.phone != null) return false;
        if (email != null ? !email.equals(domain.email) : domain.email != null) return false;
        return remark != null ? remark.equals(domain.remark) : domain.remark == null;
    }

    @Override
    public int hashCode() {
        int result = parentId != null ? parentId.hashCode() : 0;
        result = 31 * result + (parentName != null ? parentName.hashCode() : 0);
        result = 31 * result + (deptName != null ? deptName.hashCode() : 0);
        result = 31 * result + (orderNum != null ? orderNum.hashCode() : 0);
        result = 31 * result + (leader != null ? leader.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (flag ? 1 : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Domain{" +
                "parentId=" + parentId +
                ", parentName='" + parentName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", leader='" + leader + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", flag=" + flag +
                ", remark='" + remark + '\'' +
                '}';
    }
}
