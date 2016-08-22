package com.shark.common.weixin;


/**
 * 微信通用接口凭证
 */
public class AccessToken {
	// 获取到的凭证
	private String token;
	// 获取到ticket
	private String ticket;
	// 凭证有效时间，单位：秒
	private int expires_in;
	// 添加时间
	private long addTime=System.currentTimeMillis();
	
	//获取openId用到的token
	private String access_token;
	//用户刷新access_token
	private String refresh_token;
	private String openid;
	//用户授权的作用域，使用逗号（,）分隔
	private String scope;
	//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
	private String unionid;
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public long getAddTime() {
		return addTime;
	}

	public void setAddTime(long addTime) {
		this.addTime = addTime;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

}