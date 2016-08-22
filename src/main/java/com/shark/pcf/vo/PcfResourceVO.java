package com.shark.pcf.vo;

import java.util.Date;

/**
 * Created by win7 on 2014/11/30.
 */
public class PcfResourceVO {
    private Long resourceId;

    private String resourceName;

    private String identify;

    private String url;

    private String icon;

    private Long parentId;

    private String parentIds;

    private String parentName;

    private String notes;

    private String deleteFlag;

    private Long sortKey;
    private Long createUserId;

    private Date createDate;

    private Long recordUserId;

    private Date recordDate;

    public Long getResourceId() {
        return resourceId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
}