package com.shark.pcf.entity;

import com.shark.common.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by win7 on 2014/11/29.
 */
@Entity
@Table(name = "PCF_RESOURCE_T")
public class PcfResource extends AbstractEntity<Long> {

    public static final String DELETEFLAG_VAILD = "0";//有效记录
    public static final String DELETEFLAG_INVAILD = "1";//无效记录

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "RESOURCE_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long resourceId;
    @Basic
    @Column(name = "RESOURCE_NAME", nullable = false, insertable = true, updatable = true, length = 4000)
    private String resourceName;
    @Basic
    @Column(name = "IDENTIFY", nullable = true, insertable = true, updatable = true, length = 200)
    private String identify;
    @Basic
    @Column(name = "URL", nullable = true, insertable = true, updatable = true, length = 200)
    private String url;
    @Basic
    @Column(name = "ICON", nullable = true, insertable = true, updatable = true, length = 200)
    private String icon;
    @Basic
    @Column(name = "parent_id", nullable = true, insertable = true, updatable = true, length = 10)
    private Long parentId;
    @Basic
    @Column(name = "parent_ids", nullable = true, insertable = true, updatable = true, length = 400)
    private String parentIds;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Long getResourceId() {
        return resourceId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public Long getId() {
        return resourceId;
    }

    @Override
    public void setId(Long resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PcfResource that = (PcfResource) o;

        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (createUserId != null ? !createUserId.equals(that.createUserId) : that.createUserId != null) return false;
        if (deleteFlag != null ? !deleteFlag.equals(that.deleteFlag) : that.deleteFlag != null) return false;
        if (identify != null ? !identify.equals(that.identify) : that.identify != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (recordDate != null ? !recordDate.equals(that.recordDate) : that.recordDate != null) return false;
        if (recordUserId != null ? !recordUserId.equals(that.recordUserId) : that.recordUserId != null) return false;
        if (resourceId != null ? !resourceId.equals(that.resourceId) : that.resourceId != null) return false;
        if (resourceName != null ? !resourceName.equals(that.resourceName) : that.resourceName != null) return false;
        if (sortKey != null ? !sortKey.equals(that.sortKey) : that.sortKey != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = resourceId != null ? resourceId.hashCode() : 0;
        result = 31 * result + (resourceName != null ? resourceName.hashCode() : 0);
        result = 31 * result + (identify != null ? identify.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
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
