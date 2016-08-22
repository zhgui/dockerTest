package com.shark.pcf.entity;

import com.shark.common.entity.AbstractEntity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.sql.Timestamp;

/**
 * Created by win7 on 2014/11/25.
 */
@Entity
@Table(name = "PCF_PERMISSION_T")
public class PcfPermission extends AbstractEntity<Long> {

    public static final String DELETEFLAG_VAILD = "0";//有效记录
    public static final String DELETEFLAG_INVAILD = "1";//无效记录

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "PERMISSION_ID")
    private Long permissionId;

    @Basic
    @Column(name = "PERMISSION_NAME", nullable = false, insertable = true, updatable = true, length = 1000)
    private String permissionName;

    @Basic
    @Column(name = "PERMISSION", nullable = true, insertable = true, updatable = true, length = 25)
    private String permission;
    @Basic
    @Column(name = "NOTES", nullable = true, insertable = true, updatable = true, length = 4000)
    private String notes;
    @Basic
    @Column(name = "DELETE_FLAG", nullable = false, insertable = true, updatable = true, length = 1)
    private String deleteFlag;
    @Basic
    @Column(name = "SORT_KEY", nullable = true, insertable = true, updatable = true, length = 15)
    private String sortKey;
    @Basic
    @Column(name = "CREATE_USER_CD", nullable = true, insertable = true, updatable = true, length = 100)
    private String createUserCd;
    @Basic
    @Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    private Timestamp createDate;
    @Basic
    @Column(name = "RECORD_USER_CD", nullable = true, insertable = true, updatable = true, length = 100)
    private String recordUserCd;
    @Basic
    @Column(name = "RECORD_DATE", nullable = true, insertable = true, updatable = true)
    private Timestamp recordDate;


    public Long getPermissionId() {
        return permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
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

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }


    public String getCreateUserCd() {
        return createUserCd;
    }

    public void setCreateUserCd(String createUserCd) {
        this.createUserCd = createUserCd;
    }


    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }


    public String getRecordUserCd() {
        return recordUserCd;
    }

    public void setRecordUserCd(String recordUserCd) {
        this.recordUserCd = recordUserCd;
    }


    public Timestamp getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Timestamp recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public Long getId() {
        return permissionId;
    }

    @Override
    public void setId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PcfPermission that = (PcfPermission) o;

        if (permissionId != that.permissionId) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (createUserCd != null ? !createUserCd.equals(that.createUserCd) : that.createUserCd != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (permission != null ? !permission.equals(that.permission) : that.permission != null) return false;
        if (permissionName != null ? !permissionName.equals(that.permissionName) : that.permissionName != null)
            return false;
        if (recordDate != null ? !recordDate.equals(that.recordDate) : that.recordDate != null) return false;
        if (recordUserCd != null ? !recordUserCd.equals(that.recordUserCd) : that.recordUserCd != null) return false;
        if (sortKey != null ? !sortKey.equals(that.sortKey) : that.sortKey != null) return false;
        if (deleteFlag != null ? !deleteFlag.equals(that.deleteFlag) : that.deleteFlag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = permissionId != null ? permissionId.hashCode() : 0;
        result = 31 * result + (permissionName != null ? permissionName.hashCode() : 0);
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (deleteFlag != null ? deleteFlag.hashCode() : 0);
        result = 31 * result + (sortKey != null ? sortKey.hashCode() : 0);
        result = 31 * result + (createUserCd != null ? createUserCd.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (recordUserCd != null ? recordUserCd.hashCode() : 0);
        result = 31 * result + (recordDate != null ? recordDate.hashCode() : 0);
        return result;
    }
}
