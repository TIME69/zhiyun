package com.cnkeep.zhiyun.web.common.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	/**请求头访问标识key**/
	public final static String ACCESS_CONTROLL = "Access-controll";
	//未登录请求头标识
	public final static String UNLOGIN = "UN_LOGIN";
	//未授权请求头标识
	public final static String UNAUTHORIZATION = "UN_AUTHORIZATION";
	
	/**共有访问接口**/
	public final static String PUBLIC = "/common";
	
	/**
	 * @description  获取请求URL
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param request
	 * @return
	 */
	public static String gerUrlPath(HttpServletRequest request){
		String url = request.getPathInfo();
		if(url==null || url.isEmpty()){
			url = request.getServletPath();
		}
		return url;
	}
}
