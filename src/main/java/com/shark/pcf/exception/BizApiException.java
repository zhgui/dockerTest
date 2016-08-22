package com.shark.pcf.exception;

import java.util.ArrayList;
import java.util.Collection;

/**
 * shark_pcf异常处理类。 <BR>
 * <BR>
 * shark_pcf中发生异常的时候抛出。<BR>
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
public class BizApiException extends Exception {

    private static final long serialVersionUID = 1L;

    /** 默认错误编码 */
    private static final String defaultErrorCode = null;

    /**
     * 错误编码
     */
    private String errorCode = BizApiException.defaultErrorCode;

    /** 错误消息列表 */
    private Collection<String> subMessage = new ArrayList<String>();

    /** 构造函数 。 */
    public BizApiException() {
        super();
    }

    /**
     * 构造函数 。
     * 
     * @param message
     *            异常消息
     */
    public BizApiException(String message) {
        super(message);
    }

    /**
     * 构造函数 。
     * 
     * @param cause
     *            异常的原因
     */
    public BizApiException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造函数 。
     * 
     * @param message
     *            异常消息
     * @param cause
     *            异常的原因
     */
    public BizApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造函数 。
     * 
     * @param errorCode
     *            异常编码
     * @param message
     *            异常消息
     * @param cause
     *            异常的原因
     */
    public BizApiException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * 构造函数 。
     * 
     * @param errorCode
     *            异常编码
     * @param message
     *            异常消息
     * @param subMessage
     *            消息列表
     * @param cause
     *            异常的原因
     */
    public BizApiException(String errorCode, String message, Collection<String> subMessage,
            Throwable cause) {
        super(message, cause);

        if (errorCode != null) {
            this.errorCode = errorCode;
        }

        if (subMessage != null) {
            this.subMessage = subMessage;
        }
    }

    /**
     * 取得错误编码。
     * 
     * @return 错误编码
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 取得异常消息列表。
     * 
     * @return 异常消息列表
     */
    public Collection<String> getSubMessage() {
        return subMessage;
    }

}
