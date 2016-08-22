/**
 *
 */
package com.shark.common;

/**
 * <p>Date: 13-2-7 下午5:14
 * <p>Version: 1.0
 */
public interface Constants {
    /**
     * 操作名称
     */
    String OP_NAME = "op";


    /**
     * 消息key
     */
    String MESSAGE = "message";

    /**
     * 错误key
     */
    String ERROR = "error";

    /**
     * 上个页面地址
     */
    String BACK_URL = "BackURL";

    String IGNORE_BACK_URL = "ignoreBackURL";

    /**
     * 当前请求的地址 带参数
     */
    String CURRENT_URL = "currentURL";

    /**
     * 当前请求的地址 不带参数
     */
    String NO_QUERYSTRING_CURRENT_URL = "noQueryStringCurrentURL";

    String CONTEXT_PATH = "ctx";

    /**
     * 当前登录的用户
     */
    String CURRENT_USER = "user";
    String CURRENT_USERNAME = "username";

    String ENCODING = "UTF-8";

    // 短信类型
    public enum SmsResult {

        SmsSuccess(100,"发送成功"), SmsValiFail(101,"验证失败"),  SmsLow(102,"短信不足"),
        SmsFail(103,"操作失败"),SmsFfZf(104,"非法字符"),SmsMoreContent(105,"内容过多"),
        SmsMoreMobile(106,"号码过多"),SmsMoreOpt(107,"频率过快"),SmsNullContent(108,"号码内容空"),
        SmsFobbiden(110,"禁止频繁单条发送"),SmsErrorMobile(112,"号码错误"),SmsTimeFormmaterError(113,"定时时间格式不对"),
        SmsAccountLock(114,"账号被锁"),SmsFobbidenInte(116,"禁止接口发送"),SmsErrorIp(117,"绑定IP不正确"),SmsSystemUpdate(120,"系统升级");

        private int _value;
        private String _name;

        private  SmsResult(int value, String name) {
            _value = value;
            _name = name;
        }

        public int value() {
            return _value;
        }

        public String getName() {
            return _name;
        }
    }

}
