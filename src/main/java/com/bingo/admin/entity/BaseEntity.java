package com.bingo.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity extends BaseObject {
    private static final long serialVersionUID = 2633080185333846843L;
    protected Date createTime;
    protected String createBy;
    protected Date lastModifyTime;
    protected String lastModifyBy;


    /**
     * 创建时间. 本属性只在save时有效,update时无效.
     */
    @Column(updatable = false)
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

//    @Transient
//    public String getCraeteTimeStr(){
//		if (createTime == null) {
//			return "";
//		}
//		return DateFormatUtils.format(createTime, "yyyy-MM-dd HH:mm:ss");
//    }

    public void setCreateTime(Date createTime) {
        if(createTime != null){
            this.createTime = createTime;
        }
    }

    /**
     * 创建的操作员的登录名.
     */
    @Column(updatable = false)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        if(createBy != null && !createBy.equals("")){
            this.createBy = createBy;
        }
    }

    /**
     * 最后修改时间. 本属性只在update时有效,save时无效.
     */
    @Column(insertable = false)
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * 最后修改的操作员的登录名.
     */
    @Column(insertable = false)
    public String getLastModifyBy() {
        return lastModifyBy;
    }

    public void setLastModifyBy(String lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

}
