/**
 * <p>ClassName: StringUtil</p>
 * <p>Description: 字符串操作</p>
 * @author wuxu
 * <p>date 2009-9-18 下午9:40:30</p>
 *
 */

package com.shark.common.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * <p>
 * ClassName: StringUtil
 * </p>
 * <p>
 * Description: 字符串操作
 * </p>
 * 
 * @author hwh
 *         <p>
 *         date 2013-5-14 下午9:40:30
 *         </p>
 * 
 */
public class StringUtil {
	/**
	 * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
	 */
	private StringUtil() {
	}

	/**
	 * 此方法将给出的字符串source使用delim划分为单词数组。
	 * 
	 * @param source
	 *            需要进行划分的原字符串
	 * @param delim
	 *            单词的分隔字符串
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组，
	 *         如果delim为null则使用逗号作为分隔字符串。
	 * @since 0.1
	 */
	public static String[] split(String source, String delim) {
		String[] wordLists;
		if (source == null) {
			wordLists = new String[1];
			wordLists[0] = source;
			return wordLists;
		}
		if (delim == null) {
			delim = ",";
		}
		StringTokenizer st = new StringTokenizer(source, delim);
		int total = st.countTokens();
		wordLists = new String[total];
		for (int i = 0; i < total; i++) {
			wordLists[i] = st.nextToken();
		}
		return wordLists;
	}

	/**
	 * 此方法将给出的字符串source使用delim划分为单词数组。
	 * 
	 * @param source
	 *            需要进行划分的原字符串
	 * @param delim
	 *            单词的分隔字符
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
	 * @since 0.2
	 */
	public static String[] split(String source, char delim) {
		return split(source, String.valueOf(delim));
	}

	/**
	 * 此方法将给出的字符串source使用逗号划分为单词数组。
	 * 
	 * @param source
	 *            需要进行划分的原字符串
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
	 * @since 0.1
	 */
	public static String[] split(String source) {
		return split(source, ",");
	}

	/**
	 * 循环打印字符串数组。 字符串数组的各元素间以指定字符分隔，如果字符串中已经包含指定字符则在字符串的两端加上双引号。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param delim
	 *            分隔符
	 * @param out
	 *            打印到的输出流
	 * @since 0.4
	 */
	public static void printStrings(String[] strings, String delim,
			OutputStream out) {
		try {
			if (strings != null) {
				int length = strings.length - 1;
				for (int i = 0; i < length; i++) {
					if (strings[i] != null) {
						if (strings[i].indexOf(delim) > -1) {
							out.write(("\"" + strings[i] + "\"" + delim)
									.getBytes());
						} else {
							out.write((strings[i] + delim).getBytes());
						}
					} else {
						out.write("null".getBytes());
					}
				}
				if (strings[length] != null) {
					if (strings[length].indexOf(delim) > -1) {
						out.write(("\"" + strings[length] + "\"").getBytes());
					} else {
						out.write(strings[length].getBytes());
					}
				} else {
					out.write("null".getBytes());
				}
			} else {
				out.write("null".getBytes());
			}
			// out.write(Constants.LINE_SEPARATOR.getBytes());
		} catch (IOException e) {

		}
	}

	/**
	 * 循环打印字符串数组到标准输出。 字符串数组的各元素间以指定字符分隔，如果字符串中已经包含指定字符则在字符串的两端加上双引号。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param delim
	 *            分隔符
	 * @since 0.4
	 */
	public static void printStrings(String[] strings, String delim) {
		printStrings(strings, delim, System.out);
	}

	/**
	 * 循环打印字符串数组。 字符串数组的各元素间以逗号分隔，如果字符串中已经包含逗号则在字符串的两端加上双引号。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param out
	 *            打印到的输出流
	 * @since 0.2
	 */
	public static void printStrings(String[] strings, OutputStream out) {
		printStrings(strings, ",", out);
	}

	/**
	 * 循环打印字符串数组到系统标准输出流System.out。 字符串数组的各元素间以逗号分隔，如果字符串中已经包含逗号则在字符串的两端加上双引号。
	 * 
	 * @param strings
	 *            字符串数组
	 * @since 0.2
	 */
	public static void printStrings(String[] strings) {
		printStrings(strings, ",", System.out);
	}

	/**
	 * 将字符串中的变量使用values数组中的内容进行替换。 替换的过程是不进行嵌套的，即如果替换的内容中包含变量表达式时不会替换。
	 * 
	 * @param prefix
	 *            变量前缀字符串
	 * @param source
	 *            带参数的原字符串
	 * @param values
	 *            替换用的字符串数组
	 * @return 替换后的字符串。 如果前缀为null则使用“%”作为前缀；
	 *         如果source或者values为null或者values的长度为0则返回source；
	 *         如果values的长度大于参数的个数，多余的值将被忽略；
	 *         如果values的长度小于参数的个数，则后面的所有参数都使用最后一个值进行替换。
	 * @since 0.2
	 */
	public static String getReplaceString(String prefix, String source,
			String[] values) {
		String result = source;
		if (source == null || values == null || values.length < 1) {
			return source;
		}
		if (prefix == null) {
			prefix = "%";
		}

		for (int i = 0; i < values.length; i++) {
			String argument = prefix + Integer.toString(i + 1);
			int index = result.indexOf(argument);
			if (index != -1) {
				String temp = result.substring(0, index);
				if (i < values.length) {
					temp += values[i];
				} else {
					temp += values[values.length - 1];
				}
				temp += result.substring(index + 2);
				result = temp;
			}
		}
		return result;
	}

	/**
	 * 将字符串中的变量（以“%”为前导后接数字）使用values数组中的内容进行替换。
	 * 替换的过程是不进行嵌套的，即如果替换的内容中包含变量表达式时不会替换。
	 * 
	 * @param source
	 *            带参数的原字符串
	 * @param values
	 *            替换用的字符串数组
	 * @return 替换后的字符串
	 * @since 0.1
	 */
	public static String getReplaceString(String source, String[] values) {
		return getReplaceString("%", source, values);
	}

	/**
	 * 字符串数组中是否包含指定的字符串。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param string
	 *            字符串
	 * @param caseSensitive
	 *            是否大小写敏感
	 * @return 包含时返回true，否则返回false
	 * @since 0.4
	 */
	public static boolean contains(String[] strings, String string,
			boolean caseSensitive) {
		for (int i = 0; i < strings.length; i++) {
			if (caseSensitive == true) {
				if (strings[i].equals(string)) {
					return true;
				}
			} else {
				if (strings[i].equalsIgnoreCase(string)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 字符串数组中是否包含指定的字符串。大小写敏感。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param string
	 *            字符串
	 * @return 包含时返回true，否则返回false
	 * @since 0.4
	 */
	public static boolean contains(String[] strings, String string) {
		return contains(strings, string, true);
	}

	/**
	 * 不区分大小写判定字符串数组中是否包含指定的字符串。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param string
	 *            字符串
	 * @return 包含时返回true，否则返回false
	 * @since 0.4
	 */
	public static boolean containsIgnoreCase(String[] strings, String string) {
		return contains(strings, string, false);
	}

	/**
	 * 将字符串数组使用指定的分隔符合并成一个字符串。
	 * 
	 * @param array
	 *            字符串数组
	 * @param delim
	 *            分隔符，为null的时候使用""作为分隔符（即没有分隔符）
	 * @return 合并后的字符串
	 * @since 0.4
	 */
	public static String combineStringArray(String[] array, String delim) {
		int length = array.length - 1;
		if (delim == null) {
			delim = "";
		}
		StringBuffer result = new StringBuffer(length * 8);
		for (int i = 0; i < length; i++) {
			result.append(array[i]);
			result.append(delim);
		}
		result.append(array[length]);
		return result.toString();
	}

	/**
	 * 以指定的字符和长度生成一个该字符的指定长度的字符串。
	 * 
	 * @param c
	 *            指定的字符
	 * @param length
	 *            指定的长度
	 * @return 最终生成的字符串
	 * @since 0.6
	 */
	public static String fillString(char c, int length) {
		String ret = "";
		for (int i = 0; i < length; i++) {
			ret += c;
		}
		return ret;
	}

	/**
	 * 去除左边多余的空格。
	 * 
	 * @param value
	 *            待去左边空格的字符串
	 * @return 去掉左边空格后的字符串
	 * @since 0.6
	 */
	public static String trimLeft(String value) {
		String result = value;
		if (result == null)
			return result;
		char ch[] = result.toCharArray();
		int index = -1;
		for (int i = 0; i < ch.length; i++) {
			if (Character.isWhitespace(ch[i])) {
				index = i;
			} else {
				break;
			}
		}
		if (index != -1) {
			result = result.substring(index + 1);
		}
		return result;
	}

	/**
	 * 去除右边多余的空格。
	 * 
	 * @param value
	 *            待去右边空格的字符串
	 * @return 去掉右边空格后的字符串
	 * @since 0.6
	 */
	public static String trimRight(String value) {
		String result = value;
		if (result == null)
			return result;
		char ch[] = result.toCharArray();
		int endIndex = -1;
		for (int i = ch.length - 1; i > -1; i--) {
			if (Character.isWhitespace(ch[i])) {
				endIndex = i;
			} else {
				break;
			}
		}
		if (endIndex != -1) {
			result = result.substring(0, endIndex);
		}
		return result;
	}

	/**
	 * 根据转义列表对字符串进行转义。
	 * 
	 * @param source
	 *            待转义的字符串
	 * @param escapeCharMap
	 *            转义列表
	 * @return 转义后的字符串
	 * @since 0.6
	 */
	public static String escapeCharacter(String source, HashMap escapeCharMap) {
		if (source == null || source.length() == 0)
			return source;
		if (escapeCharMap.size() == 0)
			return source;
		StringBuffer sb = new StringBuffer();
		StringCharacterIterator sci = new StringCharacterIterator(source);
		for (char c = sci.first(); c != StringCharacterIterator.DONE; c = sci
				.next()) {
			String character = String.valueOf(c);
			if (escapeCharMap.containsKey(character))
				character = (String) escapeCharMap.get(character);
			sb.append(character);
		}
		return sb.toString();
	}

	/**
	 * 得到字符串的字节长度
	 * 
	 * @param source
	 *            字符串
	 * @return 字符串的字节长度
	 * @since 0.6
	 */
	public static int getByteLength(String source) {
		int len = 0;
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			int highByte = c >>> 8;
			len += highByte == 0 ? 1 : 2;
		}
		return len;
	}

	/**
	 * 判断字符串是否为（空或只含空格）
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNullOrEmptyString(Object str)

	{

		if (str == null)
			return true;

		if (str.toString().trim().length() == 0)
			return true;
		return false;

	}

	/**
	 * 替换字符串
	 * 
	 * @param line
	 * @param oldString
	 * @param newString
	 * @return String
	 */
	public static String replace(String line, String oldString, String newString) {

		if (line == null) {

			return null;

		}

		int i = 0;

		if ((i = line.indexOf(oldString, i)) >= 0) {

			char[] line2 = line.toCharArray(); // 字符串放入数组

			char[] newString2 = newString.toCharArray(); // 要替换的字符串

			int oLength = oldString.length(); // 被替换的字符串的长度

			StringBuffer buf = new StringBuffer(line2.length);

			buf.append(line2, 0, i).append(newString2);

			i += oLength;

			int j = i;

			while ((i = line.indexOf(oldString, i)) > 0) {

				buf.append(line2, j, i - j).append(newString2);

				i += oLength;

				j = i;

			}

			buf.append(line2, j, line2.length - j);

			return buf.toString();

		}

		return line;

	}


	/**
	 * 在下标 l 前插入字符串
	 * 
	 * @param s1
	 * @param s2
	 * @param l
	 * @return StringBuilder
	 */
	public static StringBuilder getString(String s1, String s2, int l) {
		StringBuilder sb = new StringBuilder();
		sb.append(s1).insert(l, s2);
		return sb;
	}

	/**
	 * 判断两个字符串是否相等
	 * 
	 * @param lin
	 * @param line
	 * @return boolean
	 */
	public static boolean equals(String lin, String line) {
		return lin.equals(line);
	}

	/**
	 *判断字符串是否包含，包含则返回下标，否则返回 -1
	 * 
	 * @param lin
	 * @param line
	 * @return int
	 */
	public static int indexOf(String lin, String line) {
		return lin.indexOf(line);
	}

	/**
	 * 两个字符串相加
	 * 
	 * @param lin
	 * @param line
	 * @return String
	 */
	public static String stringAddstring(String lin, String line) {
		String linlene = lin + line;
		return linlene;
	}

	/**
	 * 将"'"转义
	 * 
	 * @param value
	 * @return String
	 */
	public static String convertSqlString(String value) {
		if (value == null)
			return "";
		// String str = value.replace("\'", "\'\'");
		return value;
	}

	/**
	 * 将"'"转义
	 * 
	 * @param value
	 * @return String
	 */
	public static String convertSqlString(Object value) {
		if (value == null)
			return null;
		// String str = value.toString().replace("\'", "\'\'");
		return value.toString();
	}

	/**
	 * 将"'"转义
	 * 
	 * @param value
	 * @return String
	 */
	public static String convertSqlStringFile(String value) {
		if (value == null)
			return null;
		String str = value.toString().replace("'", "’");
		return str;
	}

	/**
	 * 获取Map中key的Value
	 * 
	 * @param valueMap
	 * @param key
	 * @return
	 */
	public static String convertMapString(Map valueMap, String key) {
		if (valueMap == null || key == null)
			return null;
		if (valueMap.get(key) == null) {
			return null;
		}
		if (valueMap.get(key) instanceof String) {
			return (String) valueMap.get(key);
		}
		return valueMap.get(key).toString();
	}

	/**
	 * 获取Map中Key的 Sql格式 value
	 * 
	 * @param valueMap
	 * @param key
	 * @return
	 */
	public static String convertMapSqlString(Map valueMap, String key) {
		return convertSqlString(convertMapString(valueMap, key));
	}

	/**
	 * 日期格式转换为某格式的字符串
	 * 
	 * @param date
	 *            日期
	 * @param formatString
	 *            格式
	 * @return
	 * @author huangwenhua
	 */
	public static String formatDatoToString(Date date, String formatString) {
		java.text.DateFormat df = new SimpleDateFormat(formatString);
		return df.format(date);
	}

	/**
	 * 判断某对象是否为空
	 * 
	 * @param object
	 * @return
	 * @author huangwenhua
	 */
	public static boolean isNull(Object object) {
		if (object == null || object.equals("null") || object.equals("")) {
			return true;
		}
		return false;
	}
	
    /**
     * 判断某对象是否为空
     * 
     * @param object
     * @return
     * @author huangwenhua
     */
    public static boolean isNotEmpty(Object object) {
        if (object == null || object.equals("null") || object.equals("")) {
            return false;
        }
        return true;
    }

	/**
	 * Description: 返回取一个时间范围内SQL语句片段
	 * 
	 * @param DBTime
	 *            匹配字段名称
	 * @param fromTime
	 *            起始时间
	 * @param toTime
	 *            终止时间
	 */
	public static String getTimeSql(String DBTime, String fromTime,
			String toTime) {
		String sql = "";
		if (!isNull(fromTime) || !isNull(toTime)) {
			String sqlFromTime = DBTime + ">= to_date('" + fromTime
					+ "', 'yyyy-mm-dd HH24:MI:SS')";
			String sqlToTime = DBTime + "<= to_date('" + toTime
					+ "', 'yyyy-mm-dd HH24:MI:SS')";
			// 起始时间
			if (!isNull(fromTime)) {
				sql = sqlFromTime;
				// 终止时间
				if (!isNull(toTime)) {
					sql = sqlFromTime + " and " + sqlToTime;
				}
			} else {
				sql = sqlToTime;
			}
		}
		return sql;
	}

	public static String getLastTime(String date) {
		return "to_date('" + date + " 23:59:59', 'yyyy-mm-dd HH24:MI:SS')";
	}

	// 判断是不是小写
	public static String isLower(String str, String noType) {
		String s = "";
		boolean isNum = str.matches("[0-9]+");
		// Pattern p = Pattern.compile( "[0-9]+");
		// Matcher m1 = p.matcher(str); //判断是否含有大写字符

		if (isNum || str.indexOf("XN") == 0) {
			s = noType + "='" + str + "'";

		} else
			s = " LOWER(" + noType + ") = '" + str.toLowerCase() + "'";
		return s;
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 * @return
	 */
	public static String toDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}
	
	/**
	 * 
	 * @param string
	 * @param x
	 * @param length
	 * @return
	 */
	public static String fillXInFrontOfString(String string, String x, int length) {
        if (string == null || string.length() == length) {
            return string;
        }
        for (int i = string.length(); i < length; i++) {
            string = x + string;
        }
        return string;
    }
	
	public static String format00(String money){
		if(".00".equals(money)||".0".equals(money)){
			return "0.00";
		}
		if(!StringUtil.isNull(money)){
			if(money.indexOf(".")==0){
				money="0"+money;
			}
		}
		return money;
	}

	public static String genInAndOrSql(Object[] aa, String dataFile) {
		if (aa.length == 0)
			return "";
		int max = 500;
		int n = 0;
		if (aa.length % max == 0) {
			n = aa.length / max;
		} else {
			n = aa.length / max + 1;
		}
		String sql = dataFile + " in (";
		for (int i = 0; i < n; i++) {
			int last = 0;
			if (aa.length < max)
				last = aa.length;
			else if ((i + 1) * max > aa.length)
				last = aa.length % max;
			else
				last = max;
			for (int j = 0; j < last; j++) {
				sql += "'"+aa[i * max + j]+"'";
				if (j != last - 1)
					sql += ",";
			}
			sql += ")";
			if (i != n - 1 && aa.length > max)
				sql += " or " + dataFile + " in (";
		}

		return sql;
	}
	
	public static double getLength(String s) {  
	    double valueLength = 0;    
	       String chinese = "[\u4e00-\u9fa5]";    
	       // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1    
	       for (int i = 0; i < s.length(); i++) {    
	           // 获取一个字符    
	           String temp = s.substring(i, i + 1);    
	           // 判断是否为中文字符    
	           if (temp.matches(chinese)) {    
	               // 中文字符长度为1    
	               valueLength += 1;    
	           } else {    
	               // 其他字符长度为0.5    
	               valueLength += 1;    
	           }    
	       }    
	       //进位取整    
	       return  Math.ceil(valueLength);    
	   }  
		public static boolean isLetter(char c) {   
	       int k = 0x80;   
	       return c / k == 0 ? true : false;   
	   } 
}
