package com.cnkeep.zhiyun.web.common.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @description MD5加密工具类
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年9月29日
 */
@Component
public class MD5 {
	private static String SALT = "@#zhiyun**";

	// 计算利用传入的值计算出一个32位的 
	public static String md5(String password) {
		return DigestUtils.md2Hex(password);
	}
	//对前台没有加密过的原始数据进行两次MD5加密  ，旧系统的前台没有进行过加密，明文密码
	public static String mixMd5(String password) {
		return md5(md5(password) + SALT);
	}

	public static String md5(String src,String salt){
		return md5(src+salt);
	}
	
	//对已经MD5加密的密码进行加盐加密，新系统在前台进行过MD5加密
	public static String mixMd5Pwd(String password) {
		return md5(password ,SALT);
	}

	@Value("${systemConfig.salt}")
	public static void setSalt(String salt) {
		MD5.SALT = salt;
	}
	
	public static void main(String[] args) {
		String pwd = "123456";
		System.out.println(mixMd5Pwd(pwd));
	}
}
