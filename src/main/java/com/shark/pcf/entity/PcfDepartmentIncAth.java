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
 * 组织内包实体
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
@Entity
@Table(name = "PCF_DEPARTMENT_INC_ATH_T")
public class PcfDepartmentIncAth extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "DEPARTMENT_INC_ATH_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long departmentIncAthId;

    public Long getDepartmentIncAthId() {
        return departmentIncAthId;
    }

    public void setDepartmentIncAthId(Long departmentIncAthId) {
        this.departmentIncAthId = departmentIncAthId;
    }

    @Basic
    @Column(name = "PARENT_DEPARTMENT_ID", nullable = false, insertable = true, updatable = true, length = 100)
    private Long parentDepartmentId;

    public Long getParentDepartmentId() {
        return parentDepartmentId;
    }

    public void setParentDepartmentId(Long parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    @Basic
    @Column(name = "DEPARTMENT_ID", nullable = false, insertable = true, updatable = true, length = 100)
    private Long departmentId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "DEPTH", nullable = false, insertable = true, updatable = true, precision = 0)
    private int depth;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
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

        PcfDepartmentIncAth that = (PcfDepartmentIncAth) o;

        if (departmentIncAthId != that.departmentIncAthId)
            return false;
        if (depth != that.depth)
            return false;
        if (sortKey != that.sortKey)
            return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null)
            return false;
        if (createUserCd != null
            ? !createUserCd.equals(that.createUserCd)
            : that.createUserCd != null)
            return false;
        if (deleteFlag != null ? !deleteFlag.equals(that.deleteFlag) : that.deleteFlag != null)
            return false;
        if (departmentId != that.departmentId)
            return false;
        if (parentDepartmentId != that.parentDepartmentId)
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
        int result = departmentIncAthId.hashCode();
        result = 31 * result + parentDepartmentId.hashCode();
        result = 31 * result + departmentId.hashCode();
        result = 31 * result + depth;
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
        return departmentIncAthId;
    }

    @Override
    public void setId(Long id) {
        this.departmentIncAthId = id;
    }
}
