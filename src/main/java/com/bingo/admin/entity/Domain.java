package com.bingo.admin.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "acc_domain")
@Entity
public class Domain extends BaseEntity{
    /** 岗位编码 */
    private String postCode;
    /** 岗位名称 */
    private String postName;
    /** 岗位排序 */
    private String postSort;
    /** 状态（0正常 1停用） */
    private int status=0;
    /** 用户是否存在此岗位标识 默认不存在 */
    private boolean flag = false;
    /** 备注 */
    private String remark;


    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostSort() {
        return postSort;
    }

    public void setPostSort(String postSort) {
        this.postSort = postSort;
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
        if (postCode != null ? !postCode.equals(domain.postCode) : domain.postCode != null) return false;
        if (postName != null ? !postName.equals(domain.postName) : domain.postName != null) return false;
        if (postSort != null ? !postSort.equals(domain.postSort) : domain.postSort != null) return false;
        return remark != null ? remark.equals(domain.remark) : domain.remark == null;
    }

    @Override
    public int hashCode() {
        int result = postCode != null ? postCode.hashCode() : 0;
        result = 31 * result + (postName != null ? postName.hashCode() : 0);
        result = 31 * result + (postSort != null ? postSort.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (flag ? 1 : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Domain{" +
                "postCode='" + postCode + '\'' +
                ", postName='" + postName + '\'' +
                ", postSort='" + postSort + '\'' +
                ", status=" + status +
                ", flag=" + flag +
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
