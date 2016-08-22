package com.shark.common.weixin;

import java.util.HashMap;
import java.util.Map;


/**
 * 用于保存常量信息
 */
public class StaticDIC {
	
	public static final Integer ALLOW_0 = 0;//0表示必须参加活动才能评论
	public static final Integer ALLOW_1 = 0;//1表示不参加活动也可以评论
	
	public static String NONCESTR="fotuozi5"; // 微信需要使用的签名
	
	public enum SCOPE{
		snsapi_base,//不用弹出授权页直接拿到openId
		snsapi_userinfo//弹出授权页，可以拿到用户信息
	}
	//key 为openId 存储这个openId下的accessToken 的有效时间
	public static Map<String, AccessToken> OAuth2AccessToken = new HashMap<String, AccessToken>();
	// token,ticket缓存
	public static Map<String, AccessToken> TOKEN_TICKET_CACHE = new HashMap<String, AccessToken>();
	//token 的key
	public static final String TOKEN_KEY= "tokensmg";
	//jsapi_ticket 的key
	public static final String JSAPI_TICKET_KEY = "jsapi_ticket_smg";
	
	public static String WEI_XIN_ACCOUNT = null;
	
	//用于存放session中的KEY
	public enum SESSION_KEY{
		sysUserInfo, //session中存放的用户KEY
		snsapiUserinfo,//授权用户信息
		snsapiBaseOpenId//没有授权只拿到openId
	}	
	//性别
	public enum SEX{
		男,
		女
	}	
	public static final Integer STATUS_1 = 1;//有效
	public static final Integer STATUS_0 = 0;//无效
	
	public static final Integer EXIST_TRUE = 1;		//存在
	public static final Integer EXIST_FALSE = 0;	//不存在
	
	/**
	 * 用于存放错误提示信息
	 */
	public enum FAILURE_MESSAGE{
		请您先登录,
		服务器异常请稍后重试		
	}

}
