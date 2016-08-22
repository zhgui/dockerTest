package com.shark.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: Tony
 * Date: 11-9-3
 * Time: 下午4:55
 */
public class GenericBizException extends RuntimeException {
    // ------------------------------ FIELDS ------------------------------

    /**
     * 错误编码
     */
    protected String errorCode = "";

    public static final Logger logger = LoggerFactory.getLogger(GenericBizException.class);

    protected String msgToLog = "";

    // --------------------------- CONSTRUCTORS ---------------------------

    protected GenericBizException() {
        super();
        SubConstructor("", "0");
    }

    protected GenericBizException(String msgToPrompt) {
        super(msgToPrompt);
        SubConstructor(msgToLog, "0");
    }

    protected GenericBizException(String msgToPrompt, String msgToLog) {
        super(msgToPrompt);
        SubConstructor(msgToLog, "0");
    }



    protected GenericBizException(String msgToPrompt, String msgToLog, String p_errorcode) {
        super(msgToPrompt);
        SubConstructor(msgToLog, p_errorcode);
    }

    // ------------------------ EXPOSE METHODS ------------------------

    public String toString() {
        return "BIZ EXCEPTION : ErrorCode - " + errorCode + " MSGtoLOG - " + msgToLog +
                " MSGtoUsr - " + getMessage() + " : BIZ EXCEPTION";
    }

    // --------------------- GETTER / SETTER METHODS ---------------------


    // -------------------------- OTHER METHODS --------------------------

    private void SubConstructor(String msgToLog, String errorCode) {
        this.msgToLog = msgToLog;
        this.errorCode = errorCode;
        logger.warn(" >>>>>>> " + toString() + " <<<<<<< ", this);
    }
}
