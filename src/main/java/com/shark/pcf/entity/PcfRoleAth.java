package com.shark.pcf.entity;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.shark.common.entity.AbstractEntity;

/**
 * 用户角色关系实体
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
@Entity
@Table(name = "PCF_ROLE_ATH_T")
public class PcfRoleAth extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ROLE_ATH_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long roleAthId;

    public Long getRoleAthId() {
        return roleAthId;
    }

    public void setRoleAthId(Long roleAthId) {
        this.roleAthId = roleAthId;
    }

    @Basic
    @Column(name = "ROLE_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "USER_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "DELETE_FLAG", nullable = false, insertable = true, updatable = true, length = 1)
    private String deleteFlag;

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Basic
    @Column(name = "SORT_KEY", nullable = false, insertable = true, updatable = true, precision = 0)
    private int sortKey;

    public int getSortKey() {
        return sortKey;
    }

    public void setSortKey(int sortKey) {
        this.sortKey = sortKey;
    }

    @Basic
    @Column(name = "CREATE_USER_CD", nullable = false, insertable = true, updatable = true, length = 100)
    private String createUserCd;

    public String getCreateUserCd() {
        return createUserCd;
    }

    public void setCreateUserCd(String createUserCd) {
        this.createUserCd = createUserCd;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = false, insertable = true, updatable = true)
    private Timestamp createDate;

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "RECORD_USER_CD", nullable = false, insertable = true, updatable = true, length = 100)
    private String recordUserCd;

    public String getRecordUserCd() {
        return recordUserCd;
    }

    public void setRecordUserCd(String recordUserCd) {
        this.recordUserCd = recordUserCd;
    }

    @Basic
    @Column(name = "RECORD_DATE", nullable = false, insertable = true, updatable = true)
    private Timestamp recordDate;

    public Timestamp getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Timestamp recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        PcfRoleAth that = (PcfRoleAth) o;

        if (roleAthId != that.roleAthId)
            return false;
        if (roleId != that.roleId)
            return false;
        if (sortKey != that.sortKey)
            return false;
        if (userId != that.userId)
            return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null)
            return false;
        if (createUserCd != null
            ? !createUserCd.equals(that.createUserCd)
            : that.createUserCd != null)
            return false;
        if (deleteFlag != null ? !deleteFlag.equals(that.deleteFlag) : that.deleteFlag != null)
            return false;
        if (recordDate != null ? !recordDate.equals(that.recordDate) : that.recordDate != null)
            return false;
        if (recordUserCd != null
            ? !recordUserCd.equals(that.recordUserCd)
            : that.recordUserCd != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (roleAthId != null ? roleAthId.hashCode() : 0);
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (deleteFlag != null ? deleteFlag.hashCode() : 0);
        result = 31 * result + sortKey;
        result = 31 * result + (createUserCd != null ? createUserCd.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (recordUserCd != null ? recordUserCd.hashCode() : 0);
        result = 31 * result + (recordDate != null ? recordDate.hashCode() : 0);
        return result;
    }

    @Override
    public Long getId() {
        return roleAthId;
    }

    @Override
    public void setId(Long id) {
        this.roleAthId = id;
    }
}
