/**
 *
 */
package com.shark.common.exception;

import com.shark.common.utils.MessageUtils;
import org.springframework.util.StringUtils;

/**
 * 基础异常
 * <p>Date: 13-3-11 下午8:19
 * <p>Version: 1.0
 */
public class BaseException extends RuntimeException {

    //所属模块
    private String module;

    /**
     * 默认错误编码
     */
    private static final String defaultErrorCode = null;

    /**
     * 错误编码
     */
    protected String errorCode = defaultErrorCode;


    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String defaultMessage;

    public BaseException() {
    }

    public BaseException(String module, String errorCode, Object[] args, String defaultMessage) {
        this.module = module;
        this.errorCode = errorCode;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public BaseException(String module, String errorCode, Object[] args) {
        this(module, errorCode, args, null);
    }

    public BaseException(String module, String defaultMessage) {
        this(module, null, null, defaultMessage);
    }

    public BaseException(String errorCode, Object[] args) {
        this(null, errorCode, args, null);
    }

    public BaseException(String defaultMessage) {
        this(null, null, null, defaultMessage);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        String message = null;
        if (!StringUtils.isEmpty(errorCode)) {
            message = MessageUtils.message(errorCode, args);
        }
        if (message == null) {
            message = defaultMessage;
        }
        return message;
    }


    public String getModule() {
        return module;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "module='" + module + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
