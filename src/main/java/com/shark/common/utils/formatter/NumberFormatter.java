package com.shark.common.utils.formatter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

/**
 * User: Tony
 * Date: 11-9-3
 * Time: 下午5:10
 */
public final class NumberFormatter {



    // ------------------------------ FIELDS ------------------------------
    private static final DecimalFormat OdotOOO = new DecimalFormat("0.000");
    private static final DecimalFormat Odotx = new DecimalFormat("0.00000");
    private static final DecimalFormat OdotOO = new DecimalFormat("0.00");
    private static final DecimalFormat ZERO10 = new DecimalFormat("0000000000");
    private static final DecimalFormat Odot = new DecimalFormat("0.################");
    private static final DecimalFormat MMMcommaMMMdotOO = new DecimalFormat("###,###,###,##0.00");
    private static final DecimalFormat MMMcommaMMMdotO = new DecimalFormat("###,###,###,##0.0");
    private static final DecimalFormat MMMcommaMMM = new DecimalFormat("###,###,###,##0");
    private static final DecimalFormat ZERO12 = new DecimalFormat("000000000000");
    private static final char CURRENCY_SIGN = '￥'; // 人民币货币符号



    // -------------------------- STATIC METHODS --------------------------

    /**
     * 18.56=>"1856"
     *
     * @param amount double
     * @return String
     */
    public static String double2FullString(double amount) {
        DecimalFormat CURRENCYFORMATTER = new DecimalFormat("0.00");
        String str_amt = CURRENCYFORMATTER.format(amount);
        return str_amt.substring(0, str_amt.indexOf(".")) + str_amt.substring(str_amt.indexOf(".") + 1);
    }

    /**
     * 格式化长整型金额,前面补0,当长度参数少于值参数长度时,直接返回传入的值

     *
     * @param value  long 金额
     * @param length int 返回的格式化金额长度
     * @return String 格式化金额

     */
    public static String fillOInFrontOfLong(long value, int length) {
        long numlenght = String.valueOf(value).length();
        if (length <= numlenght) {
            return String.valueOf(value);
        }
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < length - numlenght; i++) {
            buf.append("0");
        }
        buf.append(value);
        return buf.toString();
    }

    /**
     * 格式化金额输出

     *
     * @param money BigDecimal BigDecimal型金额

     * @return 格式化的金额, 格式为:###,###,###,##0.00
     */
    public static String number2MMMcommaMMMdotOO(BigDecimal money) {
        return MMMcommaMMMdotOO.format(money);
    }

    //add by ken
    public static String number2MMMcommaMMMdotOO(String money) {
        if( null == money || "".equals(money.trim()))
            return "";
        return MMMcommaMMMdotOO.format(new BigDecimal(money));
    }

    /**
     * 格式化金额输出

     *
     * @param number double double型金额

     * @return 格式化的金额, 格式为:###,###,###,##0.00
     */
    public static String number2MMMcommaMMMdotOO(double number) {
        return MMMcommaMMMdotOO.format(number);
    }

    /**
     * 格式化金额输出

     *
     * @param number double double型金额

     * @return 格式化的金额, 格式为:###,###,###,##0.00
     */
    public static String number2MMMcommaMMMdotO(double number) {
        return MMMcommaMMMdotO.format(number);
    }

    /**
     * 格式化金额输出

     *
     * @param money BigDecimal BigDecimal型金额

     * @return 格式化的金额, 格式为:###,###,###,##0.00
     */
    public static String number2MMMcommaMMM(BigDecimal money) {
        return MMMcommaMMM.format(money);
    }

    //add by ken
    public static String number2MMMcommaMMM(String money) {
        if(null == money || "".equals(money.trim()))
            return "";
        return MMMcommaMMM.format(new BigDecimal(money));
    }

    /**
     * 格式化金额输出

     *
     * @param number double double型金额

     * @return 格式化的金额, 格式为:###,###,###,##0.00
     */
    public static String number2MMMcommaMMM(double number) {
        return MMMcommaMMM.format(number);
    }

    /**
     * 格式化金额输出,自定义精确度,不四舍五入.
     *
     * @param money     double double型金额

     * @param precision int 精确度(小数点位数),<1时作0处理
     * @return String 按精确度格式化后的金额字符串,不四舍五入

     */
    public static String number2OdotCustom(double money, int precision) {
        StringBuffer buf = new StringBuffer("0");
        if (precision < 1) {
            precision = 0;
        } else {
            buf.append(".");
        }
        for (int i = 0; i < precision; i++) {
            buf.append("0");
        }
        DecimalFormat formatter = new DecimalFormat(buf.toString());
        return formatter.format(money);
    }

    /**
     * 格式化金额输出

     *
     * @param money BigDecimal BigDecimal金额
     * @return String 格式化的金额,格式为:0.00
     */
    public static String number2OdotOO(BigDecimal money) {
        return OdotOO.format(money);
    }

    public static String number2Odot(double number) {
        return Odot.format(number);
    }

    public static String number2OdotOO(double number) {
        return OdotOO.format(number);
    }
    public static String number3OdotOOO(double number) {
        return OdotOOO.format(number);
    }
     public static String numberdotx(double number) {
        return Odotx.format(number);
    }
    /**
     * 转换小数为百分数,带精确度<br>
     * 例:<br>
     * number=0.02 digit=2 结果:02.00%<br>
     * number=0.02005 digit=2 结果:02.00%<br>
     *
     * @param number double 小数
     * @param digit  int 精确度(百分数的小数位数)
     * @return String 百分数

     */
    public static String number2Percent(double number, int digit) {
        StringTokenizer st = new StringTokenizer(number2OdotCustom(number, digit + 2), ".");
        StringBuffer inte = new StringBuffer(st.nextToken()); // 获取整数部分
        StringBuffer deci = new StringBuffer(st.nextToken()); // 获取小数部分

        while (deci.length() < (2 + digit)) { // 先保证小数部分位数达到2+精确度的长度,不足补0
            deci.append("0");
        }
        if ("0".equals(inte.toString().trim())) { // 整数部分为0,即考虑纯小数的情况
            return deci.substring(0, 2) + "." + deci.substring(2, 2 + digit) + "%"; // 小数部分前两位+"."+位置2开始,截到精确度长度+"%"
        } else {
            return inte + deci.substring(0, 2) + "." + deci.substring(2, 2 + digit) + "%";
        }
    }

    /**
     * 转换小数为百分数,带精确度<br>
     * 例:<br>
     * number=0.02 digit=2 结果:2.00%<br>
     * number=0.02005 digit=4 结果:2.005%<br>
     *
     * @param number double 小数
     * @param fill0  int 精确度(百分数的小数位数)
     * @return String 百分数

     */
    public static String number2PercentCustom(double number, int fill0) {
        StringTokenizer st = new StringTokenizer(number2OdotCustom(number, fill0 + 2), ".");
        StringBuffer inte = new StringBuffer(st.nextToken());
        StringBuffer deci = new StringBuffer(st.nextToken());
        if (deci.length() < (2 + fill0)) { // 小数部分长度<2+精确度的情况
            while (deci.length() < (2 + fill0)) { // 小数部分补0
                deci.append("0");
            }
            if ("0".equals(inte.toString().trim())) {// 纯小数的情况
                if ("0".equals(deci.substring(0, 1))) {// 小数第一位为0,输出的整数部分去0处理
                    return deci.substring(1, 2) + "." + deci.substring(2, 2 + fill0) + "%";
                } else {
                    return deci.substring(0, 2) + "." + deci.substring(2, 2 + fill0) + "%";
                }
            } else {
                return inte + deci.substring(0, 2) + "." + deci.substring(2, 2 + fill0) + "%";
            }
        } else {
            if ("0".equals(inte.toString().trim())) { // 纯小数的情况
                if ("0".equals(deci.substring(0, 1))) { // 小数第一位为0,输出的整数部分去0处理
                    return deci.substring(1, 2) + "." + deci.substring(2, deci.length()) + "%"; // 精确度以小数部分的位数为准



                } else {
                    return deci.substring(0, 2) + "." + deci.substring(2, deci.length()) + "%"; // 精确度以小数部分的位数为准



                }
            } else {
                return inte + deci.substring(0, 2) + "." + deci.substring(2, deci.length()) + "%"; // 精确度以小数部分的位数为准



            }
        }
    }

    /**
     * 按指定长度获取小数的数字,顺序从左到右,当指定长度大于小数作字符串的长度时,前面加上一个"￥"
     *
     * @param currency double 小数
     * @param digits   int 指定长度
     * @return char[] 格式化的字符数组
     */
    public static char[] number2YOdotCustom(double currency, int digits) {
        char[] result = new char[digits]; // 8 digits in receipt to print
        String s = number2OdotOO(currency); // 格式化金额为0.00只保留两位小数的格式
        int dotPos = s.indexOf(".");
        if (dotPos >= 0) // 存在小数点的情况
        {
            s = s.substring(0, dotPos) + s.substring(dotPos + 1); // 去除小数点,s=000
        }
        long ltmp = Long.parseLong(s);
        s = fillOInFrontOfLong(ltmp, digits); // 长度取精确度,判断前面是否用0补足长度,当digits>currency.length.
        boolean needMark = (s.charAt(0) == '0'); // 加入货币符的判断变量,进行了上面的补0则执行此步



        for (int i = 0; i < digits; i++) {
            if (needMark) // 当还没加入货币符的时候



            {
                if (s.charAt(i) == '0') // 去掉所有首0
                {
                    continue;
                } else {
                    if (i > 0) {
                        result[i - 1] = CURRENCY_SIGN;
                        needMark = false; // 加货币符判断置为false
                    }
                    result[i] = s.charAt(i); // 加入第一个字符



                }
            } else {
                result[i] = s.charAt(i); // 加入后续字符
            }
        }
        return result;
    }

    /**
     * 转换BigDecimal型金额为中文金额读数
     *
     * @param money BigDecimal BigDecimal型金额

     * @return String 中文表示的金额读数

     */
    public static final String toChineseCurrency(BigDecimal money) {
        // 暂时支持 123456789098 共12位整数的显示 (千亿级)
        StringBuffer RMB = new StringBuffer();
        String currency = number2OdotOO(money); // 总金额



        if (currency != null && !"".equals(currency.trim())) {
            currency = currency.trim();
            int intPartLength = 0, floatPartLength = 0; // 整数位及小数位长度



            int dotPosition = 0; // 小数点位置



            String intPart = "", floatPart = ""; // 整数部分及小数部分



            int multiZero = 0; // 连续的零的情况



            int maxDigitParts = 0; // 每四位存储段的数目



            boolean prefixZero = false; // 是否需要前导‘零’



            boolean suffixZero = false; // 是否需要后置‘零’



            String CHNCHAR = "零壹贰叁肆伍陆柒捌玖拾佰仟万亿";
            String UNIT = "元角分整";
            int i, j = 0; // 循环计数器



            int tempnum = 0; // 临时存放数字
            dotPosition = currency.indexOf(".");
            if (dotPosition >= 0) { //有小数点
                intPart = currency.substring(0, dotPosition);
                floatPart = currency.substring(dotPosition + 1);
            } else {
                intPart = currency;
                floatPart = "";
            }
            intPartLength = intPart.length();
            floatPartLength = floatPart.length();
            // 处理整数部分
            if (intPartLength % 4 == 0) {
                maxDigitParts = intPartLength / 4;
            } else {
                maxDigitParts = intPartLength / 4 + 1;
            }
            String[] digitPart = new String[maxDigitParts];
            for (i = 0; i < maxDigitParts; i++) {
                digitPart[i] = "";
            }
            for (j = 0, i = intPartLength - 1; i >= 0; i--) {
                digitPart[j] += intPart.charAt(i);
                if ((intPartLength - i) % 4 == 0) {
                    j++;
                }
            }
            for (i = 0; i < maxDigitParts; i++) {
                StringBuffer tempBuf = new StringBuffer();
                for (j = 0; j < digitPart[i].length(); j++) {
                    tempnum = digitPart[i].charAt(j) - '0';
                    if (tempnum != 0) {
                        if (multiZero > 0) {
                            if (multiZero == j) { // 后续都是‘零’



                                if (i > 0) { // ‘万’位以上
                                    if (RMB.length() > 0 && RMB.charAt(0) != CHNCHAR.charAt(0)) {
                                        RMB.insert(0, CHNCHAR.charAt(0));
                                    }
                                    suffixZero = false;
                                }
                            } else {
                                tempBuf.insert(0, CHNCHAR.charAt(0));
                            }
                        }
                        multiZero = 0;
                        switch (j) {
                            case 1:
                                tempBuf.insert(0, CHNCHAR.charAt(10));
                                break;
                            case 2:
                                tempBuf.insert(0, CHNCHAR.charAt(11));
                                break;
                            case 3:
                                tempBuf.insert(0, CHNCHAR.charAt(12));
                        }
                        tempBuf.insert(0, CHNCHAR.charAt(tempnum));
                    } else {
                        multiZero++;
                        if (j == 0 && i > 0) {
                            suffixZero = true;
                        }
                        if (j == 3) {
                            prefixZero = true;
                        }
                    }
                }
                if (suffixZero && multiZero != 4) {
                    tempBuf.append(CHNCHAR.charAt(0)); // 修正后续‘零’



                    suffixZero = false;
                }
                if (prefixZero && multiZero != 4) {
                    tempBuf.insert(0, CHNCHAR.charAt(0)); // 修正前导‘零’



                    prefixZero = false;
                }
                if (multiZero != 4) {
                    switch (i) {
                        case 1: // 加‘万’字
                            RMB.insert(0, CHNCHAR.charAt(13));
                            break;
                        case 2: // 加‘亿’字
                            RMB.insert(0, CHNCHAR.charAt(14));
                            break;
                    }
                }
                multiZero = 0;
                prefixZero = false;
                suffixZero = false;
                RMB.insert(0, tempBuf.toString());
            }
            if (Long.parseLong(intPart) != 0) {
                RMB.append(UNIT.charAt(0)); // 加‘元’字
            }
            // 处理小数部分
            if (floatPartLength == 0 || "0".equals(floatPart) || "00".equals(floatPart)) {
                RMB.append(UNIT.charAt(3));
            } else {
                tempnum = floatPart.charAt(0) - '0';
                if (tempnum != 0 || RMB.length() > 0) {
                    RMB.append(CHNCHAR.charAt(tempnum));
                }
                if (tempnum != 0) {
                    RMB.append(UNIT.charAt(1)); // 加‘角’字
                }
                if (floatPartLength == 2) {
                    tempnum = floatPart.charAt(1) - '0';
                    if (tempnum != 0) {
                        RMB.append(CHNCHAR.charAt(tempnum));
                        RMB.append(UNIT.charAt(2)); // 加‘分’字
                    }
                }
            }
        }
        return RMB.toString();
    }

    /**
     * 转换double型金额为中文金额读数,12.00->壹拾贰元整 12.12-> 壹拾贰元壹角贰分
     *
     * @param money double double型金额

     * @return String 中文表示的金额读数

     */
    public static final String toChineseCurrency(double money) {
        // 暂时支持 123456789098 共12位整数的显示 (千亿级)
        StringBuffer RMB = new StringBuffer();
        boolean lowerZero = false;
        if (money < 0) {
            money = -money;
            lowerZero = true;
        }
        String currency = number2OdotOO(money); // 总金额



        if (currency != null && !"".equals(currency.trim())) {
            currency = currency.trim();
            int intPartLength = 0, floatPartLength = 0; // 整数位及小数位长度



            int dotPosition = 0; // 小数点位置



            String intPart = "", floatPart = ""; // 整数部分及小数部分



            int multiZero = 0; // 连续的零的情况



            int maxDigitParts = 0; // 每四位存储段的数目



            boolean prefixZero = false; // 是否需要前导‘零’



            boolean suffixZero = false; // 是否需要后置‘零’



            String CHNCHAR = "零壹贰叁肆伍陆柒捌玖拾佰仟万亿";
            String UNIT = "元角分整";
            int i, j = 0; // 循环计数器



            int tempnum = 0; // 临时存放数字
            dotPosition = currency.indexOf(".");
            if (dotPosition >= 0) { //有小数点
                intPart = currency.substring(0, dotPosition);
                floatPart = currency.substring(dotPosition + 1);
            } else {
                intPart = currency;
                floatPart = "";
            }
            intPartLength = intPart.length();
            floatPartLength = floatPart.length();
            // 处理整数部分
            if (intPartLength % 4 == 0) {
                maxDigitParts = intPartLength / 4;
            } else {
                maxDigitParts = intPartLength / 4 + 1;
            }
            String[] digitPart = new String[maxDigitParts];
            for (i = 0; i < maxDigitParts; i++) {
                digitPart[i] = "";
            }
            for (j = 0, i = intPartLength - 1; i >= 0; i--) {
                digitPart[j] += intPart.charAt(i);
                if ((intPartLength - i) % 4 == 0) {
                    j++;
                }
            }
            for (i = 0; i < maxDigitParts; i++) {
                StringBuffer tempBuf = new StringBuffer();
                for (j = 0; j < digitPart[i].length(); j++) {
                    tempnum = digitPart[i].charAt(j) - '0';
                    if (tempnum != 0) {
                        if (multiZero > 0) {
                            if (multiZero == j) { // 后续都是‘零’



                                if (i > 0) { // ‘万’位以上
                                    if (RMB.length() > 0 && RMB.charAt(0) != CHNCHAR.charAt(0)) {
                                        RMB.insert(0, CHNCHAR.charAt(0));
                                    }
                                    suffixZero = false;
                                }
                            } else {
                                tempBuf.insert(0, CHNCHAR.charAt(0));
                            }
                        }
                        multiZero = 0;
                        switch (j) {
                            case 1:
                                tempBuf.insert(0, CHNCHAR.charAt(10));
                                break;
                            case 2:
                                tempBuf.insert(0, CHNCHAR.charAt(11));
                                break;
                            case 3:
                                tempBuf.insert(0, CHNCHAR.charAt(12));
                        }
                        tempBuf.insert(0, CHNCHAR.charAt(tempnum));
                    } else {
                        multiZero++;
                        if (j == 0 && i > 0) {
                            suffixZero = true;
                        }
                        if (j == 3) {
                            prefixZero = true;
                        }
                    }
                }
                if (suffixZero && multiZero != 4) {
                    tempBuf.append(CHNCHAR.charAt(0)); // 修正后续‘零’



                    suffixZero = false;
                }
                if (prefixZero && multiZero != 4) {
                    tempBuf.insert(0, CHNCHAR.charAt(0)); // 修正前导‘零’



                    prefixZero = false;
                }
                if (multiZero != 4) {
                    switch (i) {
                        case 1: // 加‘万’字
                            RMB.insert(0, CHNCHAR.charAt(13));
                            break;
                        case 2: // 加‘亿’字
                            RMB.insert(0, CHNCHAR.charAt(14));
                            break;
                    }
                }
                multiZero = 0;
                prefixZero = false;
                suffixZero = false;
                RMB.insert(0, tempBuf.toString());
            }
            if (Long.parseLong(intPart) != 0) {
                RMB.append(UNIT.charAt(0)); // 加‘元’字
            }
            // 处理小数部分
            if (floatPartLength == 0 || "0".equals(floatPart) || "00".equals(floatPart)) {
                RMB.append(UNIT.charAt(3));
            } else {
                tempnum = floatPart.charAt(0) - '0';
                if (tempnum != 0 || RMB.length() > 0) {
                    RMB.append(CHNCHAR.charAt(tempnum));
                }
                if (tempnum != 0) {
                    RMB.append(UNIT.charAt(1)); // 加‘角’字
                }
                if (floatPartLength == 2) {
                    tempnum = floatPart.charAt(1) - '0';
                    if (tempnum != 0) {
                        RMB.append(CHNCHAR.charAt(tempnum));
                        RMB.append(UNIT.charAt(2)); // 加‘分’字
                    }
                }
            }
        }
        if (lowerZero) {
            return "(负) " + RMB.toString();
        } else {
            return RMB.toString();
        }
    }

    public static char toChinese(int money) {
        char result = ' ';
        switch (money) {
            case 1:
                result = '壹';
                break;
            case 2:
                result = '贰';
                break;
            case 3:
                result = '叁';
                break;
            case 4:
                result = '肆';
                break;
            case 5:
                result = '伍';
                break;
            case 6:
                result = '陆';
                break;
            case 7:
                result = '柒';
                break;
            case 8:
                result = '捌';
                break;
            case 9:
                result = '玖';
                break;
            default:
                result = '零';
                break;
        }
        return result;
    }

    public static final String formatVerifyNoAmount(long amt) {
        return ZERO10.format(amt);
    }

    public static final String format12(double amt) {
        return ZERO12.format(amt);
    }

    public static void main(String[] args) {
        System.out.println(NumberFormatter.number2MMMcommaMMM(Double.parseDouble("11111111.99")));
    }
}
