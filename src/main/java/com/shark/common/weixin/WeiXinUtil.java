package com.shark.common.weixin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shark.common.weixin.utils.HttpClient;
import com.shark.common.weixin.utils.SignUtil;

public class WeiXinUtil {

	private static ResourceBundle bundle = ResourceBundle.getBundle("weixin");
	
	/*微信统一下单*/
	public static final String prepay_url= bundle.getString("prepay_url");
	/* 获取商户号 */
	public static final String mchId = bundle.getString("mchId");
	/* 获取paySignKey */
	public static final String paySignKey = bundle.getString("paySignKey");

	public static final String notify_url = bundle.getString("notify_url");
	
	
	/* 获取appid */
	public static final String appId = bundle.getString("appId");
	/* 获取appSecret */
	public static final String appSecret = bundle.getString("appSecret");
	/* 获取access_token */
	public static final String ACCESS_TOKEN_URL = bundle.getString("access_token_url");

	// 获取jsapi_ticket的接口地址（GET） 限2000（次/天）
	public static final String JSAPI_TICKET_URL = bundle.getString("jsapi_ticket_url");

	// 生成签名的随机串
	public static final String NONCESTR = bundle.getString("noncestr");

	// ticket对应的key
	// public static final String TICKET = bundle.getString("ticket");
	/* 获取粉丝信息 */
	public static final String FANS_INFO_URL = bundle.getString("fans_info_url");
	
	/** 用户同意授权获取code **/
	public static final String CODE_URL = bundle.getString("code_url");

	/** 获取code后，请求以下链接获取access_token **/
	public static final String OAUTH2_ACCESS_TOKEN = bundle.getString("oauth2_access_token");

	/** 检查用户的token 是否有效 **/
	public static final String CHECK_OAUTH2_ACCESS_TOKEN = bundle.getString("check_oauth2_access_token");

	/** 刷新用户的token **/
	public static final String REFRESH_TOKEN_URL = bundle.getString("refresh_token_url");

	/** 获取通过openId获取用户基本信息 **/
	public static final String USERINFO_URL = bundle.getString("userInfo_url");

	/** 通过普通的access_token和openId获取用户基本信息，能拿到是否关注用户 **/
	public static final String GETUSERINFO_URL = bundle.getString("getUserInfo_url");

	/** 域名地址 **/
	public static final String domain = bundle.getString("domain");

	public static String getPropertyValue(String key) {
		return bundle.getString(key);
	}

	/** code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。 **/
	public static String getCode(String redirect_uri, String scope, String backUrl) {
		String redirect_url = "";
		try {
			redirect_url = URLEncoder.encode(redirect_uri, "UTF-8");
			if (!StringUtils.isBlank(redirect_url)) {
				String weixinuri = MessageFormat.format(CODE_URL, appId, redirect_url, scope,URLEncoder.encode(backUrl, "UTF-8"));
				return weixinuri;
			} else {
				return null;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回参数 access_token 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 * expires_in access_token接口调用凭证超时时间，单位（秒） refresh_token 用户刷新access_token
	 * openid 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID scope
	 * 用户授权的作用域，使用逗号（,）分隔 unionid
	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
	 * 
	 * @param code
	 * @return
	 */
	public static JSONObject getOAuth2AccessToken(String code) {
		AccessToken ac = StaticDIC.OAuth2AccessToken.get(code);
		if (ac == null) {
			String uri = MessageFormat.format(OAUTH2_ACCESS_TOKEN, appId.trim(),appSecret.trim(),
					code);
			String result = HttpClient.get(uri);
			ac = JSON.toJavaObject((JSON) JSON.parse(result), AccessToken.class);
			StaticDIC.OAuth2AccessToken.put(code, ac);// 放入openId缓存中
			return (JSONObject) JSON.parse(result);
		} else {
			int expiresIn = ac.getExpires_in();// 超时时间（秒）
			long addTime = ac.getAddTime();// 插入时间
			long currTime = System.currentTimeMillis();

			if (currTime - addTime > expiresIn * 1000) {// 说明超时，刷新
				return refreshToken(ac.getRefresh_token(), code);
			}
			return (JSONObject) JSON.toJSON(ac);
		}
	}

	/**
	 * 检查用户的token 是否有效
	 * 
	 * @param token
	 * @param openId
	 *            正确的Json返回结果： { "errcode":0,"errmsg":"ok"}
	 * @return
	 */
	public static boolean checkOAuth2AccessToken(String token, String openId) {
		boolean flag = false;
		String url = MessageFormat.format(CHECK_OAUTH2_ACCESS_TOKEN, token, openId);
		String result = HttpClient.get(url);
		JSONObject obj = (JSONObject) JSON.parse(result);
		if (obj != null) {
			if ("0".equals(obj.get("errcode"))) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 由于access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新，
	 * refresh_token拥有较长的有效期（7天、30天、60天、90天），当refresh_token失效的后，需要用户重新授权。
	 * 
	 * @param accessToken
	 * @return
	 */
	public static JSONObject refreshToken(String refreshToken, String code) {
		String url = MessageFormat.format(REFRESH_TOKEN_URL, appId.trim(), refreshToken);
		String result = HttpClient.get(url);
		JSONObject obj = (JSONObject) JSON.parse(result);
		AccessToken ac = JSON.toJavaObject((JSON) JSON.parse(result), AccessToken.class);
		StaticDIC.OAuth2AccessToken.put(code, ac);// 放入openId缓存中
		return obj;
	}

	/**
	 * 
	 * 如果网页授权作用域为snsapi_userinfo，则此时开发者可以通过access_token和openid拉取用户信息了。
	 * 
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static JSONObject getUserInfo(String accessToken, String openId) {
		String url = MessageFormat.format(USERINFO_URL, accessToken, openId);
		String result = HttpClient.get(url);
		JSONObject obj = (JSONObject) JSON.parse(result);
		return obj;
	}

	public static JSONObject getFanSiInfo(String openId) {
		System.out.println("传入的OpenId 为：" + openId);
		AccessToken accessTocken = getAccessToken(System.currentTimeMillis());
		String token = accessTocken.getToken();
		System.out.println("获取到的普通的token为：" + token);
		String url = MessageFormat.format(GETUSERINFO_URL, token, openId);
		System.out.println("拼接后的url为：" + url);
		String result = HttpClient.get(url);
		System.out.println("获取到的结果为：" + result);
		JSONObject obj = (JSONObject) JSON.parse(result);
		return obj;
	}

	/**
     * 构造签名
     * @param params
     * @param encode
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String createSign(Map<String, String> params, boolean encode) throws UnsupportedEncodingException {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = value.toString();
            }
            if (encode) {
                temp.append(URLEncoder.encode(valueString, "UTF-8"));
            } else {
                temp.append(valueString);
            }
        }
        return temp.toString();
    }
	
    
    
    
	
	/**
	 * 外部获取签名入口类
	 * 
	 * @param appUrl
	 *            应用的url
	 * @param timestamp
	 *            访问页面的时间
	 * @return
	 */
	public static String getSignature(String appUrl,long currentTime) {
		
		try {
			Long time = null;
			time = currentTime;
			String signature = null;
			AccessToken accessTocken = getAccessToken(time);
			AccessToken accessTicket = getTicket(accessTocken.getToken(), time);
			signature = signature(accessTicket.getTicket(), time + "",appUrl);
			return signature;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*public static void main(String[] args) {
		String si = WeiXinUtil.signature(
				"sM4AOVdWfPE4DxkXGEs8VCPpxY-F0485Wpgc-zvekbS0v_3kQvCEUPgx0Rh67R_HA0NxVXZj6SCVgrD-R2AaUQ",
				"1435739970342", "http://weixin.art-f.cn/WEB-INF/views/wode/wodeDetail.jsp");
		System.out.println(si);
	}*/

	/**
	 * 签名
	 * 
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static String signature(String jsapi_ticket, String timestamp,String url) {
		String[] paramArr = new String[] { "jsapi_ticket=" + jsapi_ticket, "timestamp=" + timestamp,
				"noncestr=" + WeiXinUtil.NONCESTR,"url=" + url };
		// 将token、timestamp、nonce,url参数进行字典序排序
		Arrays.sort(paramArr);
		// 将排序后的结果拼接成一个字符串
		String content = paramArr[0].concat("&" + paramArr[1]).concat("&" + paramArr[2]).concat("&" + paramArr[3]);
		System.out.println("排序后拼出的字符串:"+content);
		System.out.println("jsapi_ticket:"+jsapi_ticket);
		System.out.println("timestamp:"+timestamp);
		System.out.println("noncestr:"+WeiXinUtil.NONCESTR);
		System.out.println("url:"+url);
		String gensignature = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			//MessageDigest md = MessageDigest.getInstance("MD5");
			// 对拼接后的字符串进行 sha1 加密
			byte[] digest = md.digest(content.toString().getBytes());
			gensignature = SignUtil.byteToStr(digest);
			System.out.println("gensignature:"+gensignature.toLowerCase());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 将 sha1 加密后的字符串与 signature 进行对比
		if (gensignature != null) {
			return gensignature.toLowerCase();// 返回signature
		} else {
			return "false";
		}
	}

	public static AccessToken getAccessToken(Long currTime) {
		AccessToken tockenTicketCache = getTokenTicket(StaticDIC.TOKEN_KEY);
		AccessToken accessToken = null;
		if (tockenTicketCache != null
				&& (currTime - tockenTicketCache.getAddTime() <= tockenTicketCache.getExpires_in() * 1000)) {// 缓存存在并且没过期
			return tockenTicketCache;
		}
		accessToken = createAccessToken(appId.trim(), currTime);
		updateAccessToken(StaticDIC.TOKEN_KEY, accessToken);
		return accessToken;
	}

	/** 从缓存中读取token或者ticket */
	public static AccessToken getTokenTicket(String key) {
		AccessToken accessToken = null;
		if (StaticDIC.TOKEN_TICKET_CACHE != null && StaticDIC.TOKEN_TICKET_CACHE.get(key) != null) {
			accessToken = StaticDIC.TOKEN_TICKET_CACHE.get(key);
		}
		return accessToken;
	}

	/** * 更新缓存中token或者ticket */
	public static void updateAccessToken(String key, AccessToken accessTocken) {
		if (StaticDIC.TOKEN_TICKET_CACHE != null && StaticDIC.TOKEN_TICKET_CACHE.get(key) != null) {
			StaticDIC.TOKEN_TICKET_CACHE.remove(key);
		}
		StaticDIC.TOKEN_TICKET_CACHE.put(key, accessTocken);
	}

	/**
	 * 获取access_tokenb放入缓存中
	 * 
	 * @return
	 */
	public static AccessToken createAccessToken(String weixinId, Long currTime) {
		// WeChatAccount account = StaticDIC.WEI_XIN_ACCOUNT;
		String uri = MessageFormat.format(ACCESS_TOKEN_URL, appId,appSecret.trim());
		String result = HttpClient.get(uri);
		JSONObject obj = (JSONObject) JSON.parse(result);
		AccessToken accessToken = new AccessToken();
		accessToken.setToken(obj.getString("access_token"));
		accessToken.setExpires_in(obj.getInteger("expires_in") / 2);
		accessToken.setAddTime(currTime);
		return accessToken;
	}

	public static AccessToken getTicket(String token, Long currTime) {
		AccessToken tockenTicketCache = getTokenTicket(StaticDIC.JSAPI_TICKET_KEY);
		AccessToken accessToken = null;
		if (tockenTicketCache != null
				&& (currTime - tockenTicketCache.getAddTime() <= tockenTicketCache.getExpires_in() * 1000)) {// 缓存中有ticket
			return tockenTicketCache;
		}
		String uri = MessageFormat.format(JSAPI_TICKET_URL, token);
		String result = HttpClient.get(uri);
		JSONObject obj = (JSONObject) JSON.parse(result);
		accessToken = new AccessToken();
		accessToken.setTicket(obj.getString("ticket"));
		accessToken.setExpires_in(obj.getIntValue("expires_in") / 2);// 正常过期时间是7200秒，此处设置3600秒读取一次
		accessToken.setAddTime(currTime);
		updateAccessToken(StaticDIC.JSAPI_TICKET_KEY, accessToken);
		return accessToken;
	}
	
	 /**
     * 判断是否来自微信, 5.0 之后的支持微信支付
     *
     * @param request
     * @return
     */
   /* public static boolean isWeiXin(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (StringUtils.isNotBlank(userAgent)) {
            Pattern p = Pattern.compile("MicroMessenger/(\\d+).+");
            Matcher m = p.matcher(userAgent);
            String version = null;
            if (m.find()) {
                version = m.group(1);
            }
            return (null != version && NumberUtils.toInt(version) >= 5);
        }
        return false;
    }*/
    /**
     * @param params
     * @param paternerKey
     * @return
     * @throws UnsupportedEncodingException 
     */
    private static String packageSign(Map<String, String> params, String paternerKey) throws UnsupportedEncodingException {
        String string1 = createSign(params, false);
        String stringSignTemp = string1 + "&key=" + paternerKey;
        String signValue = DigestUtils.md5Hex(stringSignTemp).toUpperCase();
        String string2 = createSign(params, true);
        return string2 + "&sign=" + signValue;
    }
    
    /**
     * 参与 paySign 签名的字段包括：appid、timestamp、noncestr、package 以及 appkey。
     * 这里 signType 并不参与签名微信的Package参数
     * @param params
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String getPackage(Map<String, String> params) throws UnsupportedEncodingException {
        // 公共参数
        params.put("bank_type", "WX");
        params.put("mch_id", mchId);
        params.put("attach", "测试");
        params.put("notify_url", notify_url);
        params.put("input_charset", "UTF-8");
        return packageSign(params, paySignKey);
    }
    
	public static String getSign(Map<String, String> params, String paternerKey )throws UnsupportedEncodingException {
		String string1 = createSign(params, false);
		String stringSignTemp = string1 + "&key=" + paternerKey;
		String signValue = DigestUtils.md5Hex(stringSignTemp).toUpperCase();
		return  signValue;
	}
}
