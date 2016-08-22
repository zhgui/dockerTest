package com.shark.common.exception;

/**
 * User: Tony
 * Date: 11-9-3
 * Time: 下午4:55
 */
public class FatalBizException extends GenericBizException {

    // --------------------------- CONSTRUCTORS ---------------------------

    public FatalBizException() {
        super();
    }

    public FatalBizException(String message) {
        super(message);
    }

    /**
     * @param msgToPrompt
     * @param msgToLog
     */
    public FatalBizException(String msgToPrompt, String msgToLog) {
        super(msgToPrompt, msgToLog);
    }


    /**
     * @param msgToPrompt
     * @param msgToLog
     * @param p_errorcode
     */
    public FatalBizException(String msgToPrompt, String msgToLog, String p_errorcode) {
        super(msgToPrompt, msgToLog, p_errorcode);
    }

    // -------------------------- STATIC METHODS --------------------------

    /**
     * @param maybeFalse
     * @param msgToUsr
     */
    public static void throwWhenFalse(boolean maybeFalse, String msgToUsr) throws FatalBizException {
        if (!maybeFalse) {
            throw new FatalBizException(msgToUsr);
        }
    }

    /**
     * @param maybeFalse
     * @param msgToUsr
     */
    public static void throwWhenTrue(boolean maybeFalse, String msgToUsr) throws FatalBizException {
        if (maybeFalse) {
            throw new FatalBizException(msgToUsr);
        }
    }


    /**
     * @param maybeFalse
     * @param msgToUsr
     * @param msgToLog
     * @param p_errorcode
     */
    public static void throwWhenTrue(boolean maybeFalse, String msgToUsr, String msgToLog, String p_errorcode) throws FatalBizException {
        if (maybeFalse) {
            throw new FatalBizException(msgToUsr, msgToLog, p_errorcode);
        }
    }


    /**
     * @param maybeFalse
     * @param msgToUsr
     * @param msgToLog
     */
    public static void throwWhenFalse(boolean maybeFalse, String msgToUsr, String msgToLog) throws FatalBizException {
        if (!maybeFalse) {
            throw new FatalBizException(msgToUsr, msgToLog);
        }
    }

    /**
     * @param maybeFalse
     * @param msgToUsr
     * @param msgToLog
     * @param p_errorcode
     */
    public static void throwWhenFalse(boolean maybeFalse, String msgToUsr, String msgToLog, String p_errorcode) throws FatalBizException {
        if (!maybeFalse) {
            throw new FatalBizException(msgToUsr, msgToLog, p_errorcode);
        }
    }

    /**
     * @param objMayBeNull
     * @param msgToUsr
     */
    public static void throwWhenNull(Object objMayBeNull, String msgToUsr) throws FatalBizException {
        if (objMayBeNull == null) {
            throw new FatalBizException(msgToUsr);
        }
    }

    /**
     * @param objMayBeNull
     * @param msgToUsr
     * @param msgToLog
     */
    public static void throwWhenNull(Object objMayBeNull, String msgToUsr, String msgToLog) throws FatalBizException {
        if (objMayBeNull == null) {
            throw new FatalBizException(msgToUsr, msgToLog);
        }
    }


    /**
     * @param objMayBeNull
     * @param msgToUsr
     * @param msgToLog
     * @param p_errorcode
     */
    public static void throwWhenNull(Object objMayBeNull, String msgToUsr, String msgToLog, String p_errorcode) throws FatalBizException {
        if (objMayBeNull == null) {
            throw new FatalBizException(msgToUsr, msgToLog, p_errorcode);
        }
    }
}
