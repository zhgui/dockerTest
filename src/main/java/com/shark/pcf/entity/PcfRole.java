package com.shark.pcf.entity;

import com.shark.common.entity.AbstractEntity;

import javax.persistence.*;

import java.util.Date;

/**
 * Created by win7 on 2014/11/29.
 */
@Entity
@Table(name = "PCF_ROLE_T")
public class PcfRole extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ROLE_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long roleId;
    @Basic
    @Column(name = "ROLE_NAME", nullable = false, insertable = true, updatable = true, length = 1000)
    private String roleName;
    @Basic
    @Column(name = "ROLE", nullable = false, insertable = true, updatable = true, length = 200)
    private String role;
    @Basic
    @Column(name = "NOTES", nullable = true, insertable = true, updatable = true, length = 4000)
    private String notes;
    @Basic
    @Column(name = "DELETE_FLAG", nullable = false, insertable = true, updatable = true, length = 1)
    private String deleteFlag;
    @Basic
    @Column(name = "SORT_KEY", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long sortKey;

    @Basic
    @Column(name = "CREATE_USER_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long createUserId;

    @Basic
    @Column(name = "CREATE_DATE", nullable = false, insertable = true, updatable = true)
    private Date createDate;
    @Basic
    @Column(name = "RECORD_USER_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long recordUserId;
    @Basic
    @Column(name = "RECORD_DATE", nullable = false, insertable = true, updatable = true)
    private Date recordDate;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }


    public Long getSortKey() {
        return sortKey;
    }

    public void setSortKey(Long sortKey) {
        this.sortKey = sortKey;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public Long getRecordUserId() {
        return recordUserId;
    }

    public void setRecordUserId(Long recordUserId) {
        this.recordUserId = recordUserId;
    }


    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public Long getId() {
        return roleId;
    }

    @Override
    public void setId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PcfRole pcfRole = (PcfRole) o;

        if (createDate != null ? !createDate.equals(pcfRole.createDate) : pcfRole.createDate != null) return false;
        if (createUserId != null ? !createUserId.equals(pcfRole.createUserId) : pcfRole.createUserId != null)
            return false;
        if (deleteFlag != null ? !deleteFlag.equals(pcfRole.deleteFlag) : pcfRole.deleteFlag != null) return false;
        if (notes != null ? !notes.equals(pcfRole.notes) : pcfRole.notes != null) return false;
        if (recordDate != null ? !recordDate.equals(pcfRole.recordDate) : pcfRole.recordDate != null) return false;
        if (recordUserId != null ? !recordUserId.equals(pcfRole.recordUserId) : pcfRole.recordUserId != null)
            return false;
        if (role != null ? !role.equals(pcfRole.role) : pcfRole.role != null) return false;
        if (roleId != null ? !roleId.equals(pcfRole.roleId) : pcfRole.roleId != null) return false;
        if (roleName != null ? !roleName.equals(pcfRole.roleName) : pcfRole.roleName != null) return false;
        if (sortKey != null ? !sortKey.equals(pcfRole.sortKey) : pcfRole.sortKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (deleteFlag != null ? deleteFlag.hashCode() : 0);
        result = 31 * result + (sortKey != null ? sortKey.hashCode() : 0);
        result = 31 * result + (createUserId != null ? createUserId.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (recordUserId != null ? recordUserId.hashCode() : 0);
        result = 31 * result + (recordDate != null ? recordDate.hashCode() : 0);
        return result;
    }
}
