package com.cnkeep.core.util;

import org.apache.commons.codec.binary.Base64;

/**
 * @description base64加解密 
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2018年1月2日
 */
public class Base64Util {

	/**
	 * @description base64编码 
	 * @param encodeStr
	 * @return
	 */
	public static String encode(final String encodeStr){
		if(null == encodeStr)
			throw new NullPointerException("encodeStr can't be null");
		return Base64.encodeBase64String(encodeStr.getBytes());
	}
	
	/**
	 * @description base64解码 
	 * @param decodeStr
	 * @return
	 */
	public static String decode(final String decodeStr){
		if(null == decodeStr)
			throw new NullPointerException("decodeStr can't be null");
		return new String(Base64.decodeBase64(decodeStr));
	}
}
