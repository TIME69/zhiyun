package com.cnkeep.zhiyun.web.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.cnkeep.zhiyun.web.common.domain.entity.User;
/**
 * 
 * @description 通过shiro的SecurityUtils工具类获取当前用户， 
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年9月29日
 */
public class SessionUtil {
	private static final String USER_KEY = Constants.KEY_USER;

	public static User getCurUser() {
		return (User) getSession().getAttribute(USER_KEY);
	}

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession(true);
	}
	
	public static void setCurUser(User user){
		getSession().setAttribute(USER_KEY, user);
	}
}
