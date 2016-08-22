package com.shark.shop.server.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shark.common.entity.AbstractEntity;

@Entity
@Table(name="business_merchant")
public class Merchant extends AbstractEntity<Long> implements Serializable  {

	private static final long serialVersionUID = 1L;
	/**
	 *可用 */
	public static final int enabled =1;
	/**
	 *不可用*/
	public static final int disabled=0;
	/***
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Long merchId;
	/***
	 * 企业名字
	 */
	@Column
	private String merchantName;
	/***
	 * 用id
	 */
	@Column
	private Long userId;
	/***
	 * 执照（照片）
	 */
	@Column
	private String licenseURL;		
	/***
	 * 法人
	 */
	@Column
	private String legalPerson;
	/***
	 * 法人身份号码
	 */
	@Column
	private String identityNumber;	
	
	/***
	 *是否可用*/
	@Column
	public Integer available=Merchant.enabled;
    
	/***
	 * 是否删除  true 已删除 false 没有删除
	 */
	@Column
    private Boolean deleteFlag=false;
	/***
	 * 注册时间
	 */
	@Column
	private Date createTime=new Date();
	
	/***
	 * 更新时间
	 */
	@Column
	private Date updateTime=new Date();
	
	/**
	 *操作人员*/
	@Column
	private String operator;
	/***
	 * 操作时间
	 */
	@Column
	private Date operatorTime;
	
	
	public Long getMerchId() {
		return merchId;
	}
	public void setMerchId(Long merchId) {
		this.merchId = merchId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getLicenseURL() {
		return licenseURL;
	}
	public void setLicenseURL(String licenseURL) {
		this.licenseURL = licenseURL;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getIdentityNumber() {
		return identityNumber;
	}
	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperatorTime() {
		return operatorTime;
	}
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	
	public Boolean getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	@Override
	public Long getId() {
		return merchId;
	}
	@Override
	public void setId(Long id) {
		this.merchId = id;		
	}		
}

