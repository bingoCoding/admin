package com.bingo.admin.entity;

import com.bingo.admin.utils.SpringSecurityUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity extends BaseObject {
    private static final long serialVersionUID = 2633080185333846843L;
    protected Date createTime;
    protected String createBy;
    protected Date lastModifyTime;
    protected String lastModifyBy;


    /**
     * 创建时间. 本属性只在save时有效,update时无效.
     */
    @CreatedDate
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
        this.createTime=createTime;
    }

    /**
     * 创建的操作员的登录名.
     */
    @Column(updatable = false)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        if(createBy != null){
            this.createBy = createBy;
        }else{
            this.createBy=SpringSecurityUtils.getCurrentUserName();
        }
    }

    /**
     * 最后修改时间. 本属性只在update时有效,save时无效.
     */
    @LastModifiedDate
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
        if (lastModifyBy!=null){
            this.lastModifyBy = lastModifyBy;
        }else{
            this.lastModifyBy = SpringSecurityUtils.getCurrentUserName();
        }
    }

}
