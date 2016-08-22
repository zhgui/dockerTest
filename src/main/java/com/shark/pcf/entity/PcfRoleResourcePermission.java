package com.shark.pcf.entity;

import com.shark.common.entity.AbstractEntity;

import javax.persistence.*;

import java.util.Date;

/**
 * Created by win7 on 2014/12/9.
 */
@Entity
@Table(name = "PCF_ROLE_RESOURCE_PERMISSION")
public class PcfRoleResourcePermission extends AbstractEntity<Long> {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "RRP_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long rrpId;
    @Basic
    @Column(name = "ROLE_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long roleId;
    @Basic
    @Column(name = "RESOURCE_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long resourceId;
    @Basic
    @Column(name = "PERMISSION_IDS", nullable = false, insertable = true, updatable = true, length = 200)
    private String permissionIds;
    @Basic
    @Column(name = "CREATE_USER_ID", nullable = false, insertable = true, updatable = true, length = 100)
    private Long createUserId;

    @Basic
    @Column(name = "CREATE_DATE", nullable = false, insertable = true, updatable = true)
    private Date createDate;


    public Long getRrpId() {
        return rrpId;
    }

    public void setRrpId(Long rrpId) {
        this.rrpId = rrpId;
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


    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    @Override
    public Long getId() {
        return rrpId;
    }

    @Override
    public void setId(Long rrpId) {
        this.rrpId = rrpId;
    }
}
