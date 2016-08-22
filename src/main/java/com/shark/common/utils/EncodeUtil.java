package com.shark.common.utils;

import java.io.IOException;

/**
 * <p>ClassName: EncodeUtil </p>
 * <p>Description: 内码转换</p>
 * @author wuxu date 2009-9-18 下午9:50:20
 * 
 */
public class EncodeUtil {
	/**
	 * 转成HtmlEncode编码格式
	 * 
	 * @param s
	 * @return String
	 */
	public static String htmlEncode(String s) {
		StringBuffer stringbuffer = new StringBuffer();
		int j = s.length();
		for (int i = 0; i < j; i++) {
			char c = s.charAt(i);
			switch (c) {
			case 60:
				stringbuffer.append("&lt;");
				break;
			case 62:
				stringbuffer.append("&gt;");
				break;
			case 38:
				stringbuffer.append("&amp;");
				break;
			case 34:
				stringbuffer.append("&quot;");
				break;
			case 169:
				stringbuffer.append("&copy;");
				break;
			case 174:
				stringbuffer.append("&reg;");
				break;
			case 165:
				stringbuffer.append("&yen;");
				break;
			case 8364:
				stringbuffer.append("&euro;");
				break;
			case 8482:
				stringbuffer.append("&#153;");
				break;
			case 13:
				if (i < j - 1 && s.charAt(i + 1) == 10) {
					stringbuffer.append("<br>");
					i++;
				}
				break;
			case 32:
				stringbuffer.append("&nbsp");
				break;

			default:
				stringbuffer.append(c);
				break;
			}
		}
		return new String(stringbuffer.toString());
	}
	
	/**
	 * 转成XmlEncode编码格式
	 * @param s
	 * @return String
	 */
	public static String xmlEncode(String s) {
		StringBuffer stringbuffer = new StringBuffer();
		int j = 0;
		if(s!=null)j=s.length();
		for (int i = 0; i < j; i++) {
			char c = s.charAt(i);
			switch (c) {
			case 60:
				stringbuffer.append("&lt;");
				break;
			case 62:
				stringbuffer.append("&gt;");
				break;
			case 38:
				stringbuffer.append("&amp;");
				break;
			case '\''://不清楚其keycode
				stringbuffer.append("&apos;");
				break;
			case 34:
				stringbuffer.append("&quot;");
				break;
			default:
				stringbuffer.append(c);
				break;
			}
		}
		return new String(stringbuffer.toString());
	}

	
	/**
	 * 转成JSONEncode编码格式
	 * @param s
	 * @return String
	 */
	public static String jsonEncode(String s) {
		StringBuffer stringbuffer = new StringBuffer();
		int j = s.length();
		for (int i = 0; i < j; i++) {
			char c = s.charAt(i);
			switch (c) {
			case 60:
				stringbuffer.append("&lt;");
				break;
			case 62:
				stringbuffer.append("&gt;");
				break;
			case 38:
				stringbuffer.append("&amp;");
				break;
//			case 34:                
//				stringbuffer.append("&quot;");
//				break;
			default:
				stringbuffer.append(c);
				break;
			}
		}
		return new String(stringbuffer.toString());
	}

	/**
	 * UrlEncode 编码转换
	 * 
	 * @param text
	 * @return String
	 */
	public static String URLEncode(String text) {
		StringBuffer StrUrl = new StringBuffer();
		for (int i = 0; i < text.length(); ++i) {
			switch (text.charAt(i)) {
			case ' ':
				StrUrl.append("%20");
				break;
			case '+':
				StrUrl.append("%2b");
				break;
			case '\'':
				StrUrl.append("%27");
				break;
			case '/':
				StrUrl.append("%2F");
				break;
			case '.':
				StrUrl.append("%2E");
				break;
			case '<':
				StrUrl.append("%3c");
				break;
			case '>':
				StrUrl.append("%3e");
				break;
			case '#':
				StrUrl.append("%23");
				break;
			case '%':
				StrUrl.append("%25");
				break;
			case '&':
				StrUrl.append("%26");
				break;
			case '{':
				StrUrl.append("%7b");
				break;
			case '}':
				StrUrl.append("%7d");
				break;
			case '\\':
				StrUrl.append("%5c");
				break;
			case '^':
				StrUrl.append("%5e");
				break;
			case '~':
				StrUrl.append("%73");
				break;
			case '[':
				StrUrl.append("%5b");
				break;
			case ']':
				StrUrl.append("%5d");
				break;
			default:
				StrUrl.append(text.charAt(i));
				break;
			}
		}
		return StrUrl.toString();
	}

	/**
	 *
	 * 
	 * @param instr
	 * @return String
	 * @throws java.io.IOException
	 */
	static public String convertUTF8String2Unicode(String instr)
			throws IOException {
		// byte[] strbytes = instr.getBytes();
		int charindex = instr.length();
		int actualValue;
		int inputValue;
		StringBuffer sbtemp = new StringBuffer();

		for (int i = 0; i < charindex;) {

			actualValue = -1;
			inputValue = instr.charAt(i++);

			inputValue &= 0xff;

			if ((inputValue & 0x80) == 0) {
				actualValue = inputValue;
			} else if ((inputValue & 0xF8) == 0xF0) {
				actualValue = (inputValue & 0x1f) << 18;

				int nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F) << 12;

				nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F) << 6;

				nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F);
			} else if ((inputValue & 0xF0) == 0xE0) {
				actualValue = (inputValue & 0x1f) << 12;

				int nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F) << 6;

				nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F);
			} else if ((inputValue & 0xE0) == 0xC0) {
				actualValue = (inputValue & 0x1f) << 6;

				int nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F);
			}
			sbtemp.append((char) actualValue);
		}

		return sbtemp.toString();
	}

	/**
	 * GB2312或BIG5转换成unicode string 转成byte[]
	 * 
	 * @param instr
	 * @return byte[]
	 */
	public static byte[] convertUnicode2UTF8Byte(String instr) {
		int len = instr.length();
		byte[] abyte = new byte[len << 2];
		int j = 0;
		for (int i = 0; i < len; i++) {
			char c = instr.charAt(i);

			if (c < 0x80) {
				abyte[j++] = (byte) c;
			} else if (c < 0x0800) {
				abyte[j++] = (byte) (((c >> 6) & 0x1F) | 0xC0);
				abyte[j++] = (byte) ((c & 0x3F) | 0x80);
			} else if (c < 0x010000) {
				abyte[j++] = (byte) (((c >> 12) & 0x0F) | 0xE0);
				abyte[j++] = (byte) (((c >> 6) & 0x3F) | 0x80);
				abyte[j++] = (byte) ((c & 0x3F) | 0x80);
			} else if (c < 0x200000) {
				abyte[j++] = (byte) (((c >> 18) & 0x07) | 0xF8);
				abyte[j++] = (byte) (((c >> 12) & 0x3F) | 0x80);
				abyte[j++] = (byte) (((c >> 6) & 0x3F) | 0x80);
				abyte[j++] = (byte) ((c & 0x3F) | 0x80);
			}
		}

		byte[] retbyte = new byte[j];
		for (int i = 0; i < j; i++) {
			retbyte[i] = abyte[i];
		}
		return retbyte;
	}

	/**
	 * GB2312或BIG5转换成unicode byte[] 转成string
	 * 
	 * @param myByte
	 * @return String
	 */
	public static String ISO106462Unicode(byte[] myByte) {
		String result = new String("");

		StringBuffer sb = new StringBuffer("");
		try {
			/* 将字符串转换成byte数组 */
			// byte[] myByte= str.getBytes("ISO10646");
			int len = myByte.length;

			for (int i = 0; i < len; i = i + 2) {
				byte hiByte = myByte[i];
				byte loByte = myByte[i + 1];

				int ch = (int) hiByte << 8;
				ch = ch & 0xff00;
				ch += (int) loByte & 0xff;

				sb.append((char) ch);
			}

			result = new String(sb.toString());

		} catch (Exception e) {
			System.out.println("Encoding Error");
		}
		return result;
	}

	/**
	 * 
	 * @param s
	 * @return byte[]
	 */
	public static byte[] Unicode2Byte(String s) {
		int len = s.length();
		byte abyte[] = new byte[len << 1];
		int j = 0;
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			abyte[j++] = (byte) (c & 0xff);
			abyte[j++] = (byte) (c >> 8);
		}

		return abyte;
	}
}
