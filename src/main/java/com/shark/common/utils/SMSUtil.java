package com.shark.common.utils;

import org.jboss.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mac on 2016/3/23.
 */
public class SMSUtil {


    private static Logger logger = Logger.getLogger(SMSUtil.class);

    private static final String addr = "http://api.sms.cn/mtutf8/";
    private static final String userId = "cloudin";

	/*
	 * 如uid是：test，登录密码是：123123
	 * 加密后：则加密串为  md5(123123test)=b9887c5ebb23ebb294acab183ecf0769
	 *
	 * 可用在线生成地址：http://www.sms.cn/password
	 */

    private static final String pwd = "3fbdef96d7ddf9bdb2b87d1c02b24c6e";

    private static final String encode = "utf8";

    public static String send(String msgContent, String mobile) throws Exception {

        // 组建请求
        String straddr = addr + "?uid=" + userId + "&pwd=" + pwd + "&mobile="
                + mobile + "&encode=" + encode + "&content="
                + URLEncoder.encode(msgContent, "UTF-8");

        StringBuffer sb = new StringBuffer(straddr);
        //System.out.println("URL:" + sb);

        // 发送请求
        URL url = new URL(sb.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        BufferedReader in = new BufferedReader(new InputStreamReader(url
                .openStream()));

        // 返回结果
        String inputline = in.readLine();
        //System.out.println("Response:" + inputline);
        return inputline;
    }

    public static Map<String,String> smsBackResult(String inputline){
        Map<String,String> smsMap=new HashMap<String,String>();
        if(inputline!=null&&!"".equals(inputline)){
            String[] arr=inputline.split("&");
            if(arr[1]!=null&&!"".equals(arr[1])){
                String[] va=arr[1].split("=");
                smsMap.put("stat",va[1]);
            }
            if(arr[2]!=null&&!"".equals(arr[2])){
                String[] va=arr[2].split("=");
                smsMap.put("message",va[1]);
            }
        }
        return smsMap;
    }



    public static void main(String[] a){
        try {
            System.out.println(send("南无阿弥陀佛！师父法体安康！信众供养您的100元请接纳！佛子合十顶礼！【佛子】","15201992294"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
