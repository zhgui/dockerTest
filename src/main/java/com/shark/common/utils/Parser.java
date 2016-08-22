package com.shark.common.utils;

import java.util.Properties;

public class Parser {

    /**
     * Insert the method's description here.
     * Creation date: (4/8/2002 6:52:13 PM)
     *
     * @param strValue java.lang.String
     * @param dftValue int
     * @return int
     */
    public static int parseValue(String strValue, int dftValue) {
        int result;
        if (strValue == null)
            return dftValue;

        strValue = strValue.trim();
        if (strValue.length() == 0)
            result = dftValue;
        else
            try {
                result = Integer.parseInt(strValue);
            } catch (NumberFormatException nfe) {
                result = dftValue;
            }

        return result;
    }

    /**
     * Insert the method's description here.
     * Creation date: (4/8/2002 6:52:13 PM)
     *
     * @param strValue java.lang.String
     * @param dftValue double
     * @return double
     */
    public static double parseValue(String strValue, double dftValue) {
        double result;
        if (strValue == null)
            return dftValue;

        strValue = strValue.trim();
        if (strValue.length() == 0)
            result = dftValue;
        else
            try {
                result = Double.parseDouble(strValue);
            } catch (NumberFormatException nfe) {
                result = dftValue;
            }

        return result;
    }

    /**
     * Insert the method's description here.
     * Creation date: (4/8/2002 6:52:13 PM)
     *
     * @param strValue java.lang.String
     * @param dftValue int
     * @return long
     */
    public static long parseValue(String strValue, long dftValue) {
        if (strValue == null)
            return dftValue;

        long result;

        strValue = strValue.trim();
        if (strValue.length() == 0)
            result = dftValue;
        else
            try {
                result = Long.parseLong(strValue);
            } catch (NumberFormatException nfe) {
                result = dftValue;
            }

        return result;
    }

    /**
     * Insert the method's description here.
     * Creation date: (4/8/2002 6:52:27 PM)
     *
     * @param strValue java.lang.String
     * @param dftValue short
     * @return short
     */
    public static short parseValue(String strValue, short dftValue) {
        short result;
        if (strValue == null)
            return dftValue;

        strValue = strValue.trim();
        if (strValue.length() == 0)
            result = dftValue;
        else
            try {
                result = Short.parseShort(strValue);
            } catch (NumberFormatException nfe) {
                result = dftValue;
            }

        return result;
    }

    /**
     * Insert the method's description here.
     * Creation date: (4/8/2002 6:59:03 PM)
     *
     * @param strValue java.lang.String
     * @param dftValue boolean
     * @return boolean
     */
    public static boolean parseValue(String strValue, boolean dftValue) {
        boolean result;

        if (strValue == null)
            return dftValue;

        strValue = strValue.trim();
        if (strValue.length() == 0)
            result = dftValue;
        else if (strValue.equals("1"))
            result = true;
        else if (strValue.equals("0"))
            result = false;
        else
            result = Boolean.valueOf(strValue).booleanValue();

        return result;
    }

    /**
     * Insert the method's description here.
     * Creation date: (6/22/2002 11:06:28 AM)
     *
     * @param cfg       java.util.Properties
     * @param paramName java.lang.String
     * @param dftValue  int
     * @return int
     */
    public static int parseValue(Properties cfg, String paramName, int dftValue) {
        return parseValue(cfg.getProperty(paramName), dftValue);
    }

    /**
     * Insert the method's description here.
     * Creation date: (6/22/2002 10:53:13 AM)
     *
     * @param cfg       java.util.Properties
     * @param paramName java.lang.String
     * @param dftValue  java.lang.String
     * @return java.lang.String
     */
    public static String parseValue(Properties cfg, String paramName,
                                    String dftValue) {
        String paramValue = cfg.getProperty(paramName);
        if (paramValue == null || ((paramValue = paramValue.trim()).length() == 0))
            paramValue = dftValue;
        return paramValue;
    }

    /**
     * Insert the method's description here.
     * Creation date: (6/22/2002 11:06:47 AM)
     *
     * @param cfg       java.util.Properties
     * @param paramName java.lang.String
     * @param dftValue  short
     * @return short
     */
    public static short parseValue(Properties cfg, String paramName,
                                   short dftValue) {
        return parseValue(cfg.getProperty(paramName), dftValue);
    }

    /**
     * Insert the method's description here.
     * Creation date: (6/22/2002 11:06:13 AM)
     *
     * @param cfg       java.util.Properties
     * @param paramName java.lang.String
     * @param dftValue  boolean
     * @return boolean
     */
    public static boolean parseValue(Properties cfg, String paramName,
                                     boolean dftValue) {
        return parseValue(cfg.getProperty(paramName), dftValue);
    }

    public static String parseValue(String strValue, String dftValue) {

        if (strValue == null)
            return dftValue;
        else
            return strValue.trim();
    }

     /**
     * 字符串转换为double,当字符串为空或含有不正确字符时,以默认double参数返回
     *
     * @param strValue String 要被转换的字符串
     * @param dftValue double 默认的双浮数
     * @return double 转换后的双浮数

     */
    public static double parseValue(String strValue, double dftValue, String replaceChar) {
        double result;
        if (strValue == null) {
            return dftValue;
        }
        if (strValue.indexOf(replaceChar) >= 0) {
            strValue = strValue.replaceAll(replaceChar, "");
        }
        strValue = strValue.trim();
        if (strValue.length() == 0) {
            result = dftValue;
        } else {
            try {
                result = Double.parseDouble(strValue);
            } catch (NumberFormatException nfe) {
                result = dftValue;
            }
        }

        return result;
    }
}
