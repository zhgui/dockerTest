package com.shark.common.utils.formatter;

import java.util.Calendar;

/**
 * User: Tony
 * Date: 11-9-3
 * Time: 下午4:58
 */
public class TimeUtils {
    // ------------------------------ FIELDS ------------------------------

    public static final long ONEDAYMILLSECONDS = 1 * 24 * 3600 * 1000;

    // -------------------------- STATIC METHODS --------------------------

    /**
     * 计算日期comparsionDT和日期benchmarkDT相隔天数,返回值恒为整数

     *
     * @param benchmarkDT  long 日期毫秒值

     * @param comparisonDT long 日期毫秒值

     * @return long 返回传入日期之间的天数的毫秒值

     */
    public static final long dayDifferByMillis(long benchmarkDT, long comparisonDT) {
        benchmarkDT = getFirstMillsecondOfDay(benchmarkDT);
        comparisonDT = getFirstMillsecondOfDay(comparisonDT);
        long ONE_DAY_MILLSECONDS = 86400000; // 24 * 60 * 60 * 1000 = (Total millseconds in one day)
        long duedays = 0;
        duedays = Math.abs(((comparisonDT - benchmarkDT) / ONE_DAY_MILLSECONDS));
        if (((comparisonDT - benchmarkDT) % ONE_DAY_MILLSECONDS) != 0) {
            duedays += 1;
        }
        return duedays;
    }

    /**
     * 获取每月第一天时间的毫秒数

     *
     * @param tm 日期毫秒数

     * @return 每月1日 00:00:00的毫秒数
     */
    public final static long getFirstMillSecondofMonth(long tm) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date(tm));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime().getTime();
    }

    /**
     * 获取每年第一天时间的毫秒数

     *
     * @param tm long 日期毫秒数

     * @return long 日期每年1月1日 00:00:00的毫秒数
     */
    public final static long getFirstMillSecondofYear(long tm) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date(tm));
        cal.set(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTime().getTime();
    }

    /**
     * 获取每天零时堆分零秒的毫秒数
     *
     * @param time long 日期毫秒数

     * @return long 日期00:00:00 的毫秒数
     */
    public static final long getFirstMillsecondOfDay(long time) {
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date(time));
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime().getTime();
    }

    /**
     * 获取每月第一天时间的毫秒数

     *
     * @param tm 日期毫秒数

     * @return 每月1日 00:00:00的毫秒数
     */
    public final static long getLastMillSecondofMonth(long tm) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date(tm));
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime().getTime() - ONEDAYMILLSECONDS;
    }

    /**
     * 获取每年第一天时间的毫秒数

     *
     * @param datetime long 日期毫秒数

     * @return long 日期每年11月31日 23:59:59的毫秒数
     */
    public final static long getLastMillSecondofYear(long datetime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date(datetime));
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime().getTime();
    }

    /**
     * 获取每天最后时间毫秒数
     *
     * @param time long 日期毫秒数

     * @return long 日期23:59:59 的毫秒数
     */
    public static final long getLastMillsecondOfDay(long time) {
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date(time));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime().getTime();
    }

    /**
     * 根据日期参数获取日期对上一周的起始时间
     *
     * @param arg String
     * @return long
     */
    public final static long getLastWeekFirstDay(String arg) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(arg.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(arg.substring(4, 6)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arg.substring(6, 8)));
        cal.add(Calendar.DAY_OF_MONTH, -12);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        return cal.getTime().getTime();
    }

    /**
     * 根据日期参数获取日期对上一周的终止时间
     *
     * @param arg String
     * @return long
     */
    public final static long getLastWeekLastDay(String arg) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(arg.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(arg.substring(4, 6)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arg.substring(6, 8)));
        cal.add(Calendar.DAY_OF_MONTH, -6);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        return cal.getTime().getTime();
    }

    /**
     * 计算知道一个日期和间隔天数，求另一个日期

     *
     * @param time         long 日期
     * @param intervalDays 间隔天数
     * @return long 日期
     */
    public static final long getMillsFromDayDiffer(long time, int intervalDays) {
        return time + ONEDAYMILLSECONDS * intervalDays;
    }

    /**
     * @return 返回一天的毫秒数

     */
    public static final long getMillsecondOfOneDay() {
        return ONEDAYMILLSECONDS;
    }
}