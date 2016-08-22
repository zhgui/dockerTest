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
 * 部门实体
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
@Entity
@Table(name = "PCF_DEPARTMENT_T")
public class PcfDepartment extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "DEPARTMENT_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long departmentId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "DEPARTMENT_CD", nullable = false, insertable = true, updatable = true, length = 100)
    private String departmentCd;

    public String getDepartmentCd() {
        return departmentCd;
    }

    public void setDepartmentCd(String departmentCd) {
        this.departmentCd = departmentCd;
    }

    @Basic
    @Column(name = "DEPARTMENT_NAME", nullable = true, insertable = true, updatable = true, length = 1000)
    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Basic
    @Column(name = "DEPARTMENT_SHORT_NAME", nullable = true, insertable = true, updatable = true, length = 1000)
    private String departmentShortName;

    public String getDepartmentShortName() {
        return departmentShortName;
    }

    public void setDepartmentShortName(String departmentShortName) {
        this.departmentShortName = departmentShortName;
    }

    @Basic
    @Column(name = "DEPARTMENT_SEARCH_NAME", nullable = true, insertable = true, updatable = true, length = 1000)
    private String departmentSearchName;

    public String getDepartmentSearchName() {
        return departmentSearchName;
    }

    public void setDepartmentSearchName(String departmentSearchName) {
        this.departmentSearchName = departmentSearchName;
    }

    @Basic
    @Column(name = "COUNTRY_CD", nullable = true, insertable = true, updatable = true, length = 100)
    private String countryCd;

    public String getCountryCd() {
        return countryCd;
    }

    public void setCountryCd(String countryCd) {
        this.countryCd = countryCd;
    }

    @Basic
    @Column(name = "ZIP_CODE", nullable = true, insertable = true, updatable = true, length = 50)
    private String zipCode;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Basic
    @Column(name = "ADDRESS1", nullable = true, insertable = true, updatable = true, length = 1000)
    private String address1;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Basic
    @Column(name = "ADDRESS2", nullable = true, insertable = true, updatable = true, length = 1000)
    private String address2;

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Basic
    @Column(name = "ADDRESS3", nullable = true, insertable = true, updatable = true, length = 1000)
    private String address3;

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @Basic
    @Column(name = "TELEPHONE_NUMBER", nullable = true, insertable = true, updatable = true, length = 50)
    private String telephoneNumber;

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Basic
    @Column(name = "EXTENSION_NUMBER", nullable = true, insertable = true, updatable = true, length = 50)
    private String extensionNumber;

    public String getExtensionNumber() {
        return extensionNumber;
    }

    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    @Basic
    @Column(name = "FAX_NUMBER", nullable = true, insertable = true, updatable = true, length = 50)
    private String faxNumber;

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    @Basic
    @Column(name = "EXTENSION_FAX_NUMBER", nullable = true, insertable = true, updatable = true, length = 50)
    private String extensionFaxNumber;

    public String getExtensionFaxNumber() {
        return extensionFaxNumber;
    }

    public void setExtensionFaxNumber(String extensionFaxNumber) {
        this.extensionFaxNumber = extensionFaxNumber;
    }

    @Basic
    @Column(name = "EMAIL_ADDRESS1", nullable = true, insertable = true, updatable = true, length = 256)
    private String emailAddress1;

    public String getEmailAddress1() {
        return emailAddress1;
    }

    public void setEmailAddress1(String emailAddress1) {
        this.emailAddress1 = emailAddress1;
    }

    @Basic
    @Column(name = "EMAIL_ADDRESS2", nullable = true, insertable = true, updatable = true, length = 256)
    private String emailAddress2;

    public String getEmailAddress2() {
        return emailAddress2;
    }

    public void setEmailAddress2(String emailAddress2) {
        this.emailAddress2 = emailAddress2;
    }

    @Basic
    @Column(name = "URL", nullable = true, insertable = true, updatable = true, length = 1000)
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "NOTES", nullable = true, insertable = true, updatable = true, length = 4000)
    private String notes;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
    private Long sortKey;

    public Long getSortKey() {
        return sortKey;
    }

    public void setSortKey(Long sortKey) {
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

        PcfDepartment that = (PcfDepartment) o;

        if (departmentId != that.departmentId)
            return false;
        if (address1 != null ? !address1.equals(that.address1) : that.address1 != null)
            return false;
        if (address2 != null ? !address2.equals(that.address2) : that.address2 != null)
            return false;
        if (address3 != null ? !address3.equals(that.address3) : that.address3 != null)
            return false;
        if (countryCd != null ? !countryCd.equals(that.countryCd) : that.countryCd != null)
            return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null)
            return false;
        if (createUserCd != null
            ? !createUserCd.equals(that.createUserCd)
            : that.createUserCd != null)
            return false;
        if (deleteFlag != null ? !deleteFlag.equals(that.deleteFlag) : that.deleteFlag != null)
            return false;
        if (departmentCd != null
            ? !departmentCd.equals(that.departmentCd)
            : that.departmentCd != null)
            return false;
        if (departmentName != null
            ? !departmentName.equals(that.departmentName)
            : that.departmentName != null)
            return false;
        if (departmentSearchName != null
            ? !departmentSearchName.equals(that.departmentSearchName)
            : that.departmentSearchName != null)
            return false;
        if (departmentShortName != null
            ? !departmentShortName.equals(that.departmentShortName)
            : that.departmentShortName != null)
            return false;
        if (emailAddress1 != null
            ? !emailAddress1.equals(that.emailAddress1)
            : that.emailAddress1 != null)
            return false;
        if (emailAddress2 != null
            ? !emailAddress2.equals(that.emailAddress2)
            : that.emailAddress2 != null)
            return false;
        if (extensionFaxNumber != null
            ? !extensionFaxNumber.equals(that.extensionFaxNumber)
            : that.extensionFaxNumber != null)
            return false;
        if (extensionNumber != null
            ? !extensionNumber.equals(that.extensionNumber)
            : that.extensionNumber != null)
            return false;
        if (faxNumber != null ? !faxNumber.equals(that.faxNumber) : that.faxNumber != null)
            return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null)
            return false;
        if (recordDate != null ? !recordDate.equals(that.recordDate) : that.recordDate != null)
            return false;
        if (recordUserCd != null
            ? !recordUserCd.equals(that.recordUserCd)
            : that.recordUserCd != null)
            return false;
        if (sortKey != null ? !sortKey.equals(that.sortKey) : that.sortKey != null)
            return false;
        if (telephoneNumber != null
            ? !telephoneNumber.equals(that.telephoneNumber)
            : that.telephoneNumber != null)
            return false;
        if (url != null ? !url.equals(that.url) : that.url != null)
            return false;
        if (zipCode != null ? !zipCode.equals(that.zipCode) : that.zipCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = departmentId.hashCode();
        result = 31 * result + (departmentCd != null ? departmentCd.hashCode() : 0);
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        result = 31 * result + (departmentShortName != null ? departmentShortName.hashCode() : 0);
        result = 31 * result + (departmentSearchName != null ? departmentSearchName.hashCode() : 0);
        result = 31 * result + (countryCd != null ? countryCd.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (address1 != null ? address1.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (address3 != null ? address3.hashCode() : 0);
        result = 31 * result + (telephoneNumber != null ? telephoneNumber.hashCode() : 0);
        result = 31 * result + (extensionNumber != null ? extensionNumber.hashCode() : 0);
        result = 31 * result + (faxNumber != null ? faxNumber.hashCode() : 0);
        result = 31 * result + (extensionFaxNumber != null ? extensionFaxNumber.hashCode() : 0);
        result = 31 * result + (emailAddress1 != null ? emailAddress1.hashCode() : 0);
        result = 31 * result + (emailAddress2 != null ? emailAddress2.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (deleteFlag != null ? deleteFlag.hashCode() : 0);
        result = 31 * result + (sortKey != null ? sortKey.hashCode() : 0);
        result = 31 * result + (createUserCd != null ? createUserCd.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (recordUserCd != null ? recordUserCd.hashCode() : 0);
        result = 31 * result + (recordDate != null ? recordDate.hashCode() : 0);
        return result;
    }

    @Override
    public Long getId() {
        return departmentId;
    }

    @Override
    public void setId(Long id) {
        this.departmentId = id;

    }
}
