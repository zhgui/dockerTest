/**
 *
 */
package com.shark.common.web.validate;

import java.io.Serializable;

/**
 * <p>Date: 13-2-8 上午11:10
 * <p>Version: 1.0
 */
public class AjaxResponse implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1290830745905844589L;
	private Boolean success;
    private String message;
    private Integer resultCode = 500;

    private Object responseData;

    public AjaxResponse() {
        this(Boolean.TRUE, "操作成功");
    }

    public AjaxResponse(Boolean success) {
        this(success, null);
    }

    public AjaxResponse(String message) {
        this(Boolean.TRUE, "操作成功");
    }

    public AjaxResponse(Boolean success, String message, Integer resultCode, Object responseData) {
        this.success = success;
        this.message = message;
        this.resultCode = resultCode;
        this.responseData = responseData;
        if (this.message == null) {
            if (Boolean.FALSE.equals(success)) {
                this.message = "操作失败";
            }
            if (Boolean.TRUE.equals(success)) {
                this.message = "操作成功";
            }

        }
    }
    
    public AjaxResponse(Boolean success, String message, Integer resultCode) {
        this.success = success;
        this.message = message;
        this.resultCode = resultCode;
        if (this.message == null) {
            if (Boolean.FALSE.equals(success)) {
                this.message = "操作失败";
            }
            if (Boolean.TRUE.equals(success)) {
                this.message = "操作成功";
            }

        }
    }    
    
    public AjaxResponse(Boolean success, String message, Object object ) {
        this.success = success;
        this.message = message;
        this.responseData = responseData;
        if (this.message == null) {
            if (Boolean.FALSE.equals(success)) {
                this.message = "操作失败";
            }
            if (Boolean.TRUE.equals(success)) {
                this.message = "操作成功";
            }

        }
    }  


    public AjaxResponse(Boolean success, String message) {
        this(success, message,null, null);
    }


    public static AjaxResponse fail() {
        return fail(null);
    }

    public static AjaxResponse fail(String message) {
        return new AjaxResponse(Boolean.FALSE, message);
    }

    public static AjaxResponse fail(String message,Integer resultCode, Object object) {
        return new AjaxResponse(Boolean.FALSE, message,resultCode, object);
    }
    
    public static AjaxResponse fail(String message,Integer resultCode) {
        return new AjaxResponse(Boolean.FALSE, message,resultCode);
    }

    public static AjaxResponse success() {
        return success(null);
    }

    public static AjaxResponse success(String message) {
        return new AjaxResponse(Boolean.TRUE, message);
    }

    public static AjaxResponse success(String message,Integer resultCode, Object object) {
        return new AjaxResponse(Boolean.TRUE, message, resultCode, object);
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}
    
    
}
