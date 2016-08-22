package com.shark.common.utils;

import java.util.UUID;

import org.jboss.logging.Logger;

/**
 * 根据java UUID 生成 32位唯一Token码;
 * @author User
 *
 */
public class Token {

	private static Logger logger = Logger.getLogger(Token.class);
	/**
	 * 生成唯一token码
	 * @return
	 * @throws Exception
	 */
    public static String generateToken() {
    	// 创建 GUID 对象
        UUID uuid = UUID.randomUUID();
        // 得到对象产生的ID
        String a = uuid.toString();
        // 转换为大写
        a = a.toUpperCase();
        // 去除"-"符号
        a = a.replaceAll("-", "");
        logger.info("this is a UUID Token ****  "+a+"  *****");
        return a;
    }

}
