package com.shark.pcf.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shark.common.entity.AbstractEntity;

/**
 * 用户实体
 *
 * @author shark
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "PCF_USER_T")
public class PcfUser extends AbstractEntity<Long> implements Serializable {

	private static final long serialVersionUID = 8442980556537218623L;
	public static final int USERNAME_MIN_LENGTH = 2;
    public static final int USERNAME_MAX_LENGTH = 20;
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 50;


    public static final String DELETEFLAG_VAILD = "0";//有效记录
    public static final String DELETEFLAG_INVAILD = "1";//无效记录

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "USER_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "USER_CD", nullable = false, insertable = true, updatable = true, length = 100)
    private String userCd;

    public String getUserCd() {
        return userCd;
    }

    public void setUserCd(String userCd) {
        this.userCd = userCd;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, insertable = true, updatable = true, length = 255)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    private Date createDate;

    public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
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
    private Date recordDate;

    public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createUserCd == null) ? 0 : createUserCd.hashCode());
		result = prime * result
				+ ((deleteFlag == null) ? 0 : deleteFlag.hashCode());	
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((recordDate == null) ? 0 : recordDate.hashCode());
		result = prime * result
				+ ((recordUserCd == null) ? 0 : recordUserCd.hashCode());
		result = prime * result + ((userCd == null) ? 0 : userCd.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PcfUser other = (PcfUser) obj;
		
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (createUserCd == null) {
			if (other.createUserCd != null)
				return false;
		} else if (!createUserCd.equals(other.createUserCd))
			return false;
		if (deleteFlag == null) {
			if (other.deleteFlag != null)
				return false;
		} else if (!deleteFlag.equals(other.deleteFlag))
			return false;
		
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (recordDate == null) {
			if (other.recordDate != null)
				return false;
		} else if (!recordDate.equals(other.recordDate))
			return false;
		if (recordUserCd == null) {
			if (other.recordUserCd != null)
				return false;
		} else if (!recordUserCd.equals(other.recordUserCd))
			return false;
		if (userCd == null) {
			if (other.userCd != null)
				return false;
		} else if (!userCd.equals(other.userCd))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;	
		return true;
	}

	@Override
    public Long getId() {
        return userId;
    }

    @Override
    public void setId(Long id) {
        this.userId = id;
    }
}
