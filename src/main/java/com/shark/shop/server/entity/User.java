package com.shark.shop.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shark.common.entity.AbstractEntity;
/**
 * 会员实体
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "business_user")
public class User extends AbstractEntity<Long> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 708854065869710786L;
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private Long userId;

    @Column
    private String userCd;

    @Column
    private String password;

    @Column
    private String userName;
    
    //用户昵称
 	@Column
 	private String nickname;
 	
    @Column
    private String sex;  
  
    // 用户标识
 	@Column
    private String openid;
 		
  	// 国家
  	@Column	
    private String country;
     
  	// 省份
  	@Column	
    private String province;
      
  	// 城市
  	@Column	
    private String city;
      
  	// 用户头像链接
  	@Column	
    private String headimgurl;
    /**
     * 签名
     */
    @Column
    private String sect;

    @Column
    private String deleteFlag;

    @Column
    private Date createTime;
	
    @Column
    private Date updateTime;

    @Column
    private Integer status;
       
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	
	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	public String getSect() {
		return sect;
	}

	public void setSect(String sect) {
		this.sect = sect;
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
