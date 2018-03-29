package com.cnkeep.zhiyun.web.common.util;

import java.io.UnsupportedEncodingException;

public class StringUtil {
	/**
	 * 改变字符串编码
	 * 
	 * @param src
	 *            源字符串
	 * @param srcCharset
	 *            源编码
	 * @param destCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String src, String srcCharset, String destCharset) throws UnsupportedEncodingException {
		return new String(src.getBytes(srcCharset), destCharset);
	}
}
