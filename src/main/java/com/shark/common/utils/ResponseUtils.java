package com.shark.common.utils;


import javax.servlet.ServletResponse;

/**
 * Response工具类
 * 
 * @author 
 * 
 */
public class ResponseUtils {
	
	public static void renderJson(ServletResponse resp,
			final Object object) throws Exception{
		resp.setCharacterEncoding("UTF-8"); //设置编码格式  
		resp.setContentType("text/html");   //设置数据格式  
		resp.getWriter().println(
				EncodeUtil.jsonEncode(object.toString()));
	}
}
