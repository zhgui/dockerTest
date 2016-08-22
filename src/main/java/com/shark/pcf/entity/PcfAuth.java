package com.shark.pcf.entity;

import com.shark.common.entity.AbstractEntity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;


/**
 */
@Entity
@Table(name = "PCF_AUTH_T")
public class PcfAuth extends AbstractEntity<Long> implements Serializable {


    /**
     * 授权表ID
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "AUTH_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long authId;

    /**
     * 角色ID
     */
    @Basic
    @Column(name = "ROLE_ID", nullable = false, insertable = true, updatable = true, length = 11)
    private Long roleId;
    
    /**
     * 资源ID
     */
    @Basic
    @Column(name = "RESOURCE_ID", nullable = false, insertable = true, updatable = true, length = 11)
    private Long resourceId;
    
    /**
     * 权限ID
     */
    @Basic
    @Column(name = "PERMISSION_ID", nullable = false, insertable = true, updatable = true, length = 11)
    private Long permissionId;
    
    
    /**
     * 标志 0:有效 1:无效
     */
    @Basic
    @Column(name = "DELETE_FLAG", nullable = false, insertable = true, updatable = true, length = 1)
    private String deleteFlag;
    
    
    /**
     * 排序键
     */
    @Basic
    @Column(name = "SORT_KEY", nullable = false, insertable = true, updatable = true, length = 15)
    private String sortKey;
    

    /**
     * 创建人
     */
    @Basic
    @Column(name = "CREATE_USER_CD", nullable = false, insertable = true, updatable = true, length = 100)
    private String createUserCd;
    
    
    /**
     * 创建时间
     */
    @Basic
    @Column(name = "CREATE_DATE", nullable = false, insertable = true, updatable = true)
    private Date createDate; 
    
    /**
     * 最后更新人
     */
    @Basic
    @Column(name = "RECORD_USER_CD", nullable = false, insertable = true, updatable = true, length = 100)
    private String recordUserCd;
    
    
    /**
     * 最后更新时间
     */
    @Basic
    @Column(name = "RECORD_DATE", nullable = false, insertable = true, updatable = true)
    private Date recordDate;
    
    
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return authId;
	}
	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.authId = id;
	}
	public Long getAuthId() {
		return authId;
	}
	public void setAuthId(Long authId) {
		this.authId = authId;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getSortKey() {
		return sortKey;
	}
	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	public String getCreateUserCd() {
		return createUserCd;
	}
	public void setCreateUserCd(String createUserCd) {
		this.createUserCd = createUserCd;
	}
	public String getRecordUserCd() {
		return recordUserCd;
	}
	public void setRecordUserCd(String recordUserCd) {
		this.recordUserCd = recordUserCd;
	}
}


