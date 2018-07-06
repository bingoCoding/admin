package com.bingo.admin.commons.gen.utils;

import java.util.Random;
import java.util.Vector;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils {
	/**
	 * The empty String <code>""</code>.
	 * 
	 * @since 2.0
	 */
	public static final String EMPTY = "";

	private static Logger log = LoggerFactory.getLogger(StringUtils.class);

	public static void main(String[] args) {

		System.out.print(underscores("carryingCapacity"));
	}

	/**
	 * 转换realName -> real_name
	 * 
	 * @param str
	 * @return
	 */
	public static String underscores(String str) {
		// Convert to underscores
		char[] ca = str.toCharArray();
		StringBuilder build = new StringBuilder("" + ca[0]);
		boolean lower = true;
		for (int i = 1; i < ca.length; i++) {
			char c = ca[i];
			if (Character.isUpperCase(c) && lower) {
				build.append("_");
				lower = false;
			} else if (!Character.isUpperCase(c)) {
				lower = true;
			}

			build.append(c);
		}

		str = build.toString().toLowerCase();

		return str;

	}

	/**
	 * 连接字符串
	 * 
	 * @param parts
	 * @return
	 */
	public static String string(String... parts) {
		StringBuilder sb = new StringBuilder();
		for (String part : parts) {
			sb.append(part);
		}
		return sb.toString();
	}

	/**
	 * 连接字符串
	 * 
	 * @param parts
	 * @return
	 */
	public static StringBuilder stringBuilder(String... parts) {
		StringBuilder sb = new StringBuilder();
		for (String part : parts) {
			sb.append(part);
		}
		return sb;
	}

	/**
	 * 字符串分割 ，又见org.apache.commons.lang.StringUtils.split
	 * 
	 * @param strSrc
	 * @param strSep
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String[] splitStr(String strSrc, String strSep) {
		if (strSrc == null || strSep == null) {
			return null;
		}
		int intPos = 0;
		int intBox = 0;
		Vector vecResult = new Vector();
		String strTmp = null;
		while ((intPos = strSrc.indexOf(strSep, intBox)) != -1) {
			if (intBox == intPos) {
				strTmp = "";
			} else {
				strTmp = strSrc.substring(intBox, intPos);
			}
			vecResult.addElement(strTmp);
			// intBox = intPos + 1;
			intBox = intPos + strSep.length();
		}
		if (intBox < strSrc.length()) {
			vecResult.addElement(strSrc.substring(intBox));
		}
		return (String[]) vecResult.toArray(new String[0]);
	}

	/**
	 * 替换字符串
	 * 
	 * @param origin
	 *            原始字符串
	 * @param src
	 *            需要更换的字符串
	 * @param dest
	 *            更换后的字符串
	 * @return
	 */
	public static String replaceString(String origin, String src, String dest) {
		if (origin == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer(origin.length());

		int srcLength = src.length();
		int destLength = dest.length();

		int preOffset = 0;
		int offset = 0;
		while ((offset = origin.indexOf(src, preOffset)) != -1) {
			sb.append(origin.substring(preOffset, offset));
			sb.append(dest);
			preOffset = offset + srcLength;
		}
		sb.append(origin.substring(preOffset, origin.length()));

		return sb.toString();
	}

	/**
	 * 生成随机数
	 * 
	 * @param num
	 *            位数
	 * @return
	 */
	public static String getRandom(int num) {
		Random r = new Random();
		String result = "";
		while (result.length() < num) {
			int temp1 = r.nextInt(8);
			if (result.indexOf(temp1 + "") == -1) {
				result = result + temp1;
			}
		}
		return result;
	}

	/**
	 * Encode a string using Base64 encoding. Used when storing passwords as
	 * cookies.
	 * 
	 * This is weak encoding in that anyone can use the decodeString routine to
	 * reverse the encoding.
	 * 
	 * @param str
	 * @return String
	 */
	public static String encodeString(String str) {
		Base64 encoder = new Base64();
		return String.valueOf(encoder.encode(str.getBytes())).trim();
	}

	/**
	 * Decode a string using Base64 encoding.
	 * 
	 * @param str
	 * @return String
	 */
	public static String decodeString(String str) {
		Base64 dec = new Base64();
		return String.valueOf(dec.decode(str));
	}

	/**
	 * 
	 * @method_name getFirstUpperStr
	 * @author wangxiaoxi ( email:tcwangxiaoxi@gmail.com )
	 * @date 2012-5-11 下午3:46:40
	 * @description proName-->ProName
	 * @reviewed_by
	 * @param @param string
	 * @param @return
	 * @throw
	 */
	public static String getFirstUpperStr(String string) {
		String firstString = string.substring(0, 1).toUpperCase();
		return firstString + string.substring(1);
	}

	/**
	 * 
	 * @method_name getFirstLowerStr
	 * @author wangxiaoxi ( email:tcwangxiaoxi@gmail.com )
	 * @date 2012-5-11 下午3:46:40
	 * @description ProName-->proName
	 * @reviewed_by
	 * @param @param string
	 * @param @return
	 * @throw
	 */
	public static String getFirstLowerStr(String string) {
		String firstString = string.substring(0, 1).toLowerCase();
		return firstString + string.substring(1);
	}

	// SubStringAfter/SubStringBefore
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Gets the substring before the first occurrence of a separator. The
	 * separator is not returned.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> string input will return <code>null</code>. An empty
	 * ("") string input will return the empty string. A <code>null</code>
	 * separator will return the input string.
	 * </p>
	 * 
	 * <p>
	 * If nothing is found, the string input is returned.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.substringBefore(null, *)      = null
	 * StringUtils.substringBefore("", *)        = ""
	 * StringUtils.substringBefore("abc", "a")   = ""
	 * StringUtils.substringBefore("abcba", "b") = "a"
	 * StringUtils.substringBefore("abc", "c")   = "ab"
	 * StringUtils.substringBefore("abc", "d")   = "abc"
	 * StringUtils.substringBefore("abc", "")    = ""
	 * StringUtils.substringBefore("abc", null)  = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to get a substring from, may be null
	 * @param separator
	 *            the String to search for, may be null
	 * @return the substring before the first occurrence of the separator,
	 *         <code>null</code> if null String input
	 * @since 2.0
	 */
	public static String substringBefore(String str, String separator) {
		if (isEmpty(str) || separator == null) {
			return str;
		}
		if (separator.length() == 0) {
			return EMPTY;
		}
		int pos = str.indexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	/**
	 * <p>
	 * Gets the substring after the first occurrence of a separator. The
	 * separator is not returned.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> string input will return <code>null</code>. An empty
	 * ("") string input will return the empty string. A <code>null</code>
	 * separator will return the empty string if the input string is not
	 * <code>null</code>.
	 * </p>
	 * 
	 * <p>
	 * If nothing is found, the empty string is returned.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.substringAfter(null, *)      = null
	 * StringUtils.substringAfter("", *)        = ""
	 * StringUtils.substringAfter(*, null)      = ""
	 * StringUtils.substringAfter("abc", "a")   = "bc"
	 * StringUtils.substringAfter("abcba", "b") = "cba"
	 * StringUtils.substringAfter("abc", "c")   = ""
	 * StringUtils.substringAfter("abc", "d")   = ""
	 * StringUtils.substringAfter("abc", "")    = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to get a substring from, may be null
	 * @param separator
	 *            the String to search for, may be null
	 * @return the substring after the first occurrence of the separator,
	 *         <code>null</code> if null String input
	 * @since 2.0
	 */
	public static String substringAfter(String str, String separator) {
		if (isEmpty(str)) {
			return str;
		}
		if (separator == null) {
			return EMPTY;
		}
		int pos = str.indexOf(separator);
		if (pos == -1) {
			return EMPTY;
		}
		return str.substring(pos + separator.length());
	}

	// Empty checks
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Checks if a String is empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isEmpty(null)      = true
	 * StringUtils.isEmpty("")        = true
	 * StringUtils.isEmpty(" ")       = false
	 * StringUtils.isEmpty("bob")     = false
	 * StringUtils.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * <p>
	 * NOTE: This method changed in Lang version 2.0. It no longer trims the
	 * String. That functionality is available in isBlank().
	 * </p>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is empty or null
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
}
