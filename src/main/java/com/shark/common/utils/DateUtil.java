package com.shark.common.utils;

import com.shark.common.utils.formatter.DateFormatter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

public class DateUtil {

    public static Date getNowTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        Date currentTime_2 = new Date();
        try {
            currentTime_2 = formatter.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentTime_2;
    }


 public static Date getNowDateYYYYMMDD() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String dateString = formatter.format(currentTime);
	  Date currentTime_2=new Date();
	try {
		currentTime_2 = formatter.parse(dateString);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return currentTime_2;
	 }
 /**
  * 获取现在时间
  * 
  * @return返回短时间格式 yyyy-MM-dd
  */
 public static Date getNowDateShort() {
  Date currentTime = new Date();
  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  String dateString = formatter.format(currentTime);
  ParsePosition pos = new ParsePosition(8);
  Date currentTime_2 = formatter.parse(dateString, pos);
  return currentTime_2;
 }
 
 public static String  getNowDateShortNoFormat() {
	 Date currentTime = new Date();
     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
     String dateString = formatter.format(currentTime);
     return dateString;
	 }



    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    public static void main(String[] s){
        System.out.println(strToDateLong("20160711153210"));
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        if(StringUtil.isNull(strtodate)){
            return DateFormatter.yyyymmddhhmmss2Timestamp(strDate);
        }
        return strtodate;
    }
    
    /**
     * 根据时间戳返回长类型的时间格式
     * @param strDate
     * @return
     */
    public static String timeStampToStringLong(Long strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date(strDate*1000L));
        return date;
    }

    
    /**
     * 把一段yyyyMMddHHmmss的字符串转换成yyyy-MM-dd HH:mm:ss格式的字符串
     * @param date
     * @param oldFormat yyyyMMddHHmmss
     * @param newFormat yyyy-MM-dd HH:mm:ss
     * @param index 0
     * @return
     */
    public static String dateTrans(String date) {
        String result = null;
        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//            ParsePosition pos = new ParsePosition(0);
//            Date d = sdf.parse(date, pos);
//            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            result = sdf.format(d);
            SimpleDateFormat oldFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = oldFormat.parse(date);
            result =  newFormat.format(date1);
        } catch (Exception err) {
            System.out.println("Trans Error: "+ err.getMessage());
        }
        return result;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @param
     * @return
     */
    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 得到现在时间
     *
     * @return
     */
    public static Date getNow() {
        Date currentTime = new Date();
        return currentTime;
    }

    /**
     * 提取一个月中的最后一天
     *
     * @param day
     * @return
     */
    public static Date getLastDate(long day) {
        Date date = new Date();
        long date_3_hm = date.getTime() - 3600000 * 34 * day;
        Date date_3_hm_date = new Date(date_3_hm);
        return date_3_hm_date;
    }

    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    public static String getStringTodayNoMark() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 得到现在小时
     */
    public static String getHour() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }

    /**
     * 得到现在分钟
     *
     * @return
     */
    public static String getTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(14, 16);
        return min;
    }

    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
     *
     * @param sformat yyyyMMddhhmmss
     * @return
     */
    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
     */
    public static String getTwoHour(String st1, String st2) {
        String[] kk = null;
        String[] jj = null;
        kk = st1.split(":");
        jj = st2.split(":");
        if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
            return "0";
        else {
            double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
            double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
            if ((y - u) > 0)
                return y - u + "";
            else
                return "0";
        }
    }

    /**
     * 得到二个日期间的间隔天数
     */
    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    public static String getPreTime(String sj1, String jj) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try {
            Date date1 = format.parse(sj1);
            long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
        }
        return mydate1;
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     */
    public static String getNextDay(String nowdate, String delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String mdate = "";
            Date d = strToDate(nowdate);
            long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * 返回特殊时间
     * 1. tmp 为 now  返回当前时间
     * 2. tmp 为 yesterday 返回昨天这个时间点的时间
     * 3. tmp 为 zero 返回今天零点零分零秒
     * 4. tmp 为twelve 返回今天23点59分59秒
     * @param tmp
     * @return
     */
    public static Date getSpecialFormatDate(String tmp){
    	long current=System.currentTimeMillis();//当前时间毫秒数
		long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
		long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
		long yesterday=System.currentTimeMillis()-24*60*60*1000;//昨天的这一时间的毫秒数
		if("now".equals(tmp)){
			return new Timestamp(current);//当前时间
		}else if("yesterday".equals(tmp)){
			return new Timestamp(yesterday);//昨天这一时间点
		}else if("zero".endsWith(tmp)){
			return new Timestamp(zero); //今天零点零分零秒
		}else if("twelve".equals(tmp)){
			return new Timestamp(twelve); //今天23点59分59秒
		}else{
			return null;
		}
    }

    /**
     * 判断是否润年
     *
     * @param ddate
     * @return
     */
    public static boolean isLeapYear(String ddate) {

        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰年
         */
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0)
            return true;
        else if ((year % 4) == 0) {
            if ((year % 100) == 0)
                return false;
            else
                return true;
        } else
            return false;
    }

    /**
     * 返回美国时间格式 26 Apr 2006
     *
     * @param str
     * @return
     */
    public static String getEDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
    }

    /**
     * 获取一个月的最后一天
     *
     * @param dat
     * @return
     */
    public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
        String str = dat.substring(0, 8);
        String month = dat.substring(5, 7);
        int mon = Integer.parseInt(month);
        if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
            str += "31";
        } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
            str += "30";
        } else {
            if (isLeapYear(dat)) {
                str += "29";
            } else {
                str += "28";
            }
        }
        return str;
    }

    /**
     * 判断二个时间是否在同一个周
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    /**
     * 产生周序列,即得到当前时间所在的年度是第几周
     *
     * @return
     */
    public static String getSeqWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1)
            week = "0" + week;
        String year = Integer.toString(c.get(Calendar.YEAR));
        return year + week;
    }

    /**
     * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
     *
     * @param sdate
     * @param num
     * @return
     */
    public static String getWeek(String sdate, String num) {
        // 再转换为时间
        Date dd = DateUtil.strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(dd);
        if (num.equals("1")) // 返回星期一所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        else if (num.equals("2")) // 返回星期二所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        else if (num.equals("3")) // 返回星期三所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        else if (num.equals("4")) // 返回星期四所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        else if (num.equals("5")) // 返回星期五所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        else if (num.equals("6")) // 返回星期六所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        else if (num.equals("0")) // 返回星期日所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate) {
        // 再转换为时间
        Date date = DateUtil.strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    public static String getWeekStr(String sdate) {
        String str = "";
        str = DateUtil.getWeek(sdate);
        if ("1".equals(str)) {
            str = "星期日";
        } else if ("2".equals(str)) {
            str = "星期一";
        } else if ("3".equals(str)) {
            str = "星期二";
        } else if ("4".equals(str)) {
            str = "星期三";
        } else if ("5".equals(str)) {
            str = "星期四";
        } else if ("6".equals(str)) {
            str = "星期五";
        } else if ("7".equals(str)) {
            str = "星期六";
        }
        return str;
    }

    /**
     * 两个时间之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
     * 此函数返回该日历第一行星期日所在的日期
     *
     * @param sdate
     * @return
     */
    public static String getNowMonth(String sdate) {
        // 取该时间所在月的一号
        sdate = sdate.substring(0, 8) + "01";

        // 得到这个月的1号是星期几
        Date date = DateUtil.strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int u = c.get(Calendar.DAY_OF_WEEK);
        String newday = DateUtil.getNextDay(sdate, (1 - u) + "");
        return newday;
    }

    /**
     * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
     *
     * @param k 表示是取几位随机数，可以自己定
     */

    public static String getNo(int k) {

        return getUserDate("yyyyMMddhhmmss") + getRandom(k);
    }

    /**
     * 返回一个随机数
     *
     * @param i
     * @return
     */
    public static String getRandom(int i) {
        Random jjj = new Random();
        // int suiJiShu = jjj.nextInt(9);
        if (i == 0)
            return "";
        String jj = "";
        for (int k = 0; k < i; k++) {
            jj = jj + jjj.nextInt(9);
        }
        return jj;
    }

    /**
     * @param
     */
    public static boolean RightDate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ;
        if (date == null)
            return false;
        if (date.length() > 10) {
            sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        try {
            sdf.parse(date);
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    /**
     * 格式化日期
     *
     * @param dateStr 字符型日期
     * @param format  格式
     * @return 返回日期
     */

    public static Date parseDate(String dateStr, String format) {
        Date date = null;
        try {
            java.text.DateFormat df = new SimpleDateFormat(format);
            // 存在问题
            // String dt = Normal.parse(dateStr).replaceAll("-", "/");
            String dt = (dateStr).replaceAll("-", "/");
            if ((!dt.equals("")) && (dt.length() < format.length())) {
                dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]",
                        "0");
            }
            date = (Date) df.parse(dt);
        } catch (Exception e) {
        }
        return date;
    }

    public static Date str2Date(String yyyyMMddHHmmss) {
        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = df.parse(yyyyMMddHHmmss);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return date;
    }
    
    //按照给定的格式化字符串格式化日期
    public static String formatDate(Date date, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }
 
    //按照给定的格式化字符串解析日期
    public static Date parseDateFormat(String dateStr, String formatStr) throws ParseException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        date = sdf.parse(dateStr);
        return date;
    }
    
    //从字符串中分析日期
    public static Date parseDate(String dateStr) throws ParseException {
        Date date=null;
        String[] dateArray = dateStr.split("\\D+");     //+防止多个非数字字符在一起时导致解析错误
        int dateLen = dateArray.length;
        int dateStrLen=dateStr.length();
        if(dateLen>0){
            if(dateLen==1&&dateStrLen>4){
                if(dateStrLen=="yyyyMMddHHmmss".length()){
                    //如果字符串长度为14位并且不包含其他非数字字符，则按照（yyyyMMddHHmmss）格式解析
                    date=parseDateFormat(dateStr,"yyyyMMddHHmmss");
                }else if(dateStrLen=="yyyyMMddHHmm".length()){
                    date=parseDateFormat(dateStr,"yyyyMMddHHmm");
                }else if(dateStrLen=="yyyyMMddHH".length()){
                    date=parseDateFormat(dateStr,"yyyyMMddHH");
                }else if(dateStrLen=="yyyyMMdd".length()){
                    date=parseDateFormat(dateStr,"yyyyMMdd");
                }else if(dateStrLen=="yyyyMM".length()){
                    date=parseDateFormat(dateStr,"yyyyMM");
                }
            }else{
                String fDateStr=dateArray[0];
                for(int i=1;i<dateLen;i++){
                    //左补齐是防止十位数省略的情况
                    fDateStr+=leftPad(dateArray[i],"0",2);
                }
                 
                if(dateStr.trim().matches("^\\d{1,2}:\\d{1,2}(:\\d{1,2})?$")){
                    //补充年月日3个字段
                    dateLen+=3;
                    fDateStr=formatDate(new Date(),"yyyyMMdd")+fDateStr;
                }
                 
                date=parseDate(fDateStr,"yyyyMMddHHmmss".substring(0, (dateLen-1)*2+4));
            }
        }
         
        return date;
    }
 
    //左补齐
    public static String leftPad(String str,String pad,int len){
        String newStr=(str==null?"":str);
        while(newStr.length()<len){
            newStr=pad+newStr;
        }
        if(newStr.length()>len){
            newStr=newStr.substring(newStr.length()-len);
        }
        return newStr;
    }
    
    /**
     * 判断某个时间在当前时间之前多久
     * @param newTime
     * @param createTime
     * @return
     */
    public static String judgementTime(String newTime, String createTime){
		long[] disTime=new long[5];
        String times="";
		//循环出时间
        int i=0;
        disTime = DateDistanceUtils.getDistanceMonth(newTime,createTime); 
        for(i=0;i<disTime.length;i++){
        	if(disTime[i]!=0) break;
        }              
        if(i==0){
        	long disTimeTmp = disTime[i];
       	  times=  String.valueOf(disTimeTmp).replaceAll("-", "")+"月前";
         }
        if(i==1){
       	  times=  disTime[i]+"天前";
        }
        if(i==2){
       	  times=disTime[i]+"小时前";
        }
        if(i==3){
       	  times= disTime[i]+"分钟前"; 
        }
        if(i==4){
    	 times= "刚刚"; 
        }
        return times;
	}

}
