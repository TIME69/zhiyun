package com.cnkeep.zhiyun.web.common.security;

import org.apache.shiro.SecurityUtils;

import com.cnkeep.zhiyun.web.common.domain.entity.User;
/**
 * 用户session管理支持类
 * @description 
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年11月17日
 */
public class SessionUtils {
	private final static String CURRENT_USER = "CURRENT_USER";

	public static void setUser(User user) {
		SecurityUtils.getSubject().getSession().setAttribute(CURRENT_USER, user);
		;
	}

	public static User getCurrentUser() {
		return (User) SecurityUtils.getSubject().getSession().getAttribute(CURRENT_USER);
	}
}
