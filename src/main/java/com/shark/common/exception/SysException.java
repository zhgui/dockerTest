package com.shark.common.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: Tony
 * Date: 11-8-27
 * Time: 下午3:41
 */
public class SysException extends GenericBizException {

    // ------------------------------ FIELDS ------------------------------

    public static final Logger logger = LoggerFactory.getLogger(SysException.class);

    // --------------------------- CONSTRUCTORS ---------------------------

    public SysException(String msg) {
        super(msg);
        logger.error(" >>>>>>> " + toString() + " <<<<<<< ", this);
    }

    public SysException(String message, Throwable cause) {
        //super(message, cause);
        super(message);


        logger.error(" >>>>>>> " + toString() + " <<<<<<< ", cause);
    }

    // -------------------------- STATIC METHODS --------------------------

    public static void throwWhenFalse(boolean maybeFalse, String message) throws SysException {
        if (!maybeFalse) {
            throw new SysException(message);
        }
    }

    public static void throwWhenNull(Object objMayBeNull, String message) throws SysException {
        if (objMayBeNull == null) {
            throw new SysException(message);
        }
    }

    // ------------------------ EXPOSE METHODS ------------------------

    public String toString() {
        return "SYS EXCEPTION : ErrorCode - " + 0 + " MSG - " + getMessage() + " : SYS EXCEPTION";
    }
}