package com.shark.pcf.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by win7 on 2014/11/25.
 */
public class PcfPermissionVO implements Serializable{


    private Long permissionId;

    private String permissionName;


    private String permission;

    private String notes;

    private String deleteFlag;

    private String sortKey;

    private String createUserCd;

    private Date createDate;

    private String recordUserCd;

    private Date recordDate;

    public PcfPermissionVO() {
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
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

    public String getRecordUserCd() {
        return recordUserCd;
    }

    public void setRecordUserCd(String recordUserCd) {
        this.recordUserCd = recordUserCd;
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

}
