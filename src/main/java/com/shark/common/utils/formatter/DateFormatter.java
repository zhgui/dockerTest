package com.shark.common.utils.formatter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Tony
 * Date: 11-9-3
 * Time: 下午4:52
 */
public final class DateFormatter {

    // ------------------------------ FIELDS ------------------------------

    private static final SimpleDateFormat YY_MM_DDHHmm = new SimpleDateFormat("yy-MM-dd HH:mm");

    private static final SimpleDateFormat YY_MM_DD = new SimpleDateFormat("yy-MM-dd");

    private static final SimpleDateFormat YYYY_MM_DDHHmm = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private static final SimpleDateFormat YYYY_MM_DDHH24miss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddhhmmss");

    private static final SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat YYYY = new SimpleDateFormat("yyyy");

    private static final SimpleDateFormat YY = new SimpleDateFormat("yy");

    private static final SimpleDateFormat MM = new SimpleDateFormat("MM");

    private static final SimpleDateFormat DD = new SimpleDateFormat("dd");

    private static final SimpleDateFormat YYMMDD = new SimpleDateFormat("yyMMdd");

    private static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

    private static final SimpleDateFormat YYYYMMDDHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final SimpleDateFormat YYYYcnMMcnDDcn = new SimpleDateFormat("yyyy年MM月dd日");

    private static final SimpleDateFormat YYYYMM = new SimpleDateFormat("yyyyMM");

    private static final SimpleDateFormat YYMM = new SimpleDateFormat("yyMM");

    static {
        YY_MM_DDHHmm.setLenient(false);
        YY_MM_DD.setLenient(false);
        YYYY_MM_DDHHmm.setLenient(false);
        YYYY_MM_DD.setLenient(false);
        YYYY.setLenient(false);
        YY.setLenient(false);
        MM.setLenient(false);
        DD.setLenient(false);
        YYMMDD.setLenient(false);
        YYYYMMDD.setLenient(false);
        YYYYMMDDHHmmss.setLenient(false);
        YYYYcnMMcnDDcn.setLenient(false);
        YYYYMM.setLenient(false);
    }

    // -------------------------- STATIC METHODS --------------------------

    public static String getDDFromLong(Date time) {
        if (time == null) return "";
        return DD.format(time);
    }

    public static String getMMFromLong(Date time) {
        if (time == null) return "";
        return MM.format(time);
    }

    public static String getYYFromLong(Date time) {
        if (time == null) return "";
        return YY.format(time);
    }

    public static String getYYYYFromLong(Date time) {
        if (time == null) return "";
        return YYYY.format(time);
    }

    public static String getYYYYMMFromLong(Date time) {
        if (time == null) return "";
        return YYYYMM.format(time);
    }

    public static String long2YYMMDD(Date time) {
        if (time == null) return "";
        return YYMMDD.format(time);
    }

     public static String long2YYMM(Date time) {
        if (time == null) return "";
        return YYMM.format(time);
    }

    public static String long2YYYYMMDD(Date time) {
        if (time == null) return "";
        return YYYYMMDD.format(time);
    }

    public static String long2YYYYMMDDHHmmss(Date time) {
        if (time == null) return "";
        return YYYYMMDDHHmmss.format(time);
    }

    public static String long2YYYY_MM_DDHH24miss(Date time) {
        if (time == null) return "";
        return YYYY_MM_DDHH24miss.format(time);
    }

    public static String long2YYYY_MM_DD(Date time) {
        if (time == null) return "";
        return YYYY_MM_DD.format(time);
    }

    public static String long2YYYY_MM_DDHHmm(Date time) {
        if (time == null) return "";
        return YYYY_MM_DDHHmm.format(time);
    }

    public static String long2YYYYcnMMcnDDcn(Date time) {
        if (time == null) return "";
        return YYYYcnMMcnDDcn.format(time);
    }

    public static String long2YY_MM_DD(Date time) {
        if (time == null) return "";
        return YY_MM_DD.format(time);
    }

    public static String long2YY_MM_DDHHmm(Date time) {
        if (time == null) return "";
        return YY_MM_DDHHmm.format(time);
    }

    public static Timestamp yy_MM_DD2Timestamp(String yy_mm_dd) {
        try {
            return new Timestamp(YY_MM_DD.parse(yy_mm_dd).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yy_mm_dd + " ERROR." + e.getMessage());
        }
    }

    public static Timestamp yyyyMMDD2Timestamp(String yyyymmdd) {
        try {
            return new Timestamp(YYYYMMDD.parse(yyyymmdd).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyymmdd + " ERROR." + e.getMessage());
        }
    }

    public static Timestamp yyyy_MM_DD2Timestamp(String yyyy_mm_dd) {
        try {
            return new Timestamp(YYYY_MM_DD.parse(yyyy_mm_dd).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyy_mm_dd + " ERROR." + e.getMessage());
        }
    }

    public static long yyyy_MM_DD2Long(String yyyy_mm_dd) {
        try {
            return YYYY_MM_DD.parse(yyyy_mm_dd).getTime();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyy_mm_dd + " ERROR." + e.getMessage());
        }
    }


    public static Timestamp yyyycnMMcnDDcn2Timestamp(String yyyycnMMcnDDcn) {
        try {
            return new Timestamp(YYYYcnMMcnDDcn.parse(yyyycnMMcnDDcn).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyycnMMcnDDcn + " ERROR." + e.getMessage());
        }
    }

    public static Timestamp yyyymmddhhmmss2Timestamp(String yyyymmddhhmmss) {
        try {
            return new Timestamp(YYYYMMDDHHMMSS.parse(yyyymmddhhmmss).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyymmddhhmmss + " ERROR." + e.getMessage());
        }
    }

    public static Date yyyyMMDD2Date(String yyyyMMDD) {
        try {
            return new Date(YYYYMMDD.parse(yyyyMMDD).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyyMMDD + " ERROR." + e.getMessage());
        }
    }

    public static long yyyycnMMcnDDcn2Long(String yyyycnMMcnDDcn) {
        try {
            return YYYYcnMMcnDDcn.parse(yyyycnMMcnDDcn).getTime();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + yyyycnMMcnDDcn + " ERROR." + e.getMessage());
        }
    }

    public static Date YYYY_MM_DDHH24miss_NOW(){
        String st = "";
        try {
            st = YYYY_MM_DDHH24miss.format(new Date());
            return YYYY_MM_DDHH24miss.parse(st);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatter Date" + st + " ERROR." + e.getMessage());
        }
    }

}


