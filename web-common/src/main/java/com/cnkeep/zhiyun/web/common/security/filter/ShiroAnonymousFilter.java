package com.cnkeep.zhiyun.web.common.security.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AnonymousFilter;

import com.cnkeep.zhiyun.web.common.util.RequestUtil;

public class ShiroAnonymousFilter extends AnonymousFilter {

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) {
		HttpServletResponse resp = (HttpServletResponse) response;
		String url = RequestUtil.gerUrlPath((HttpServletRequest) request);

		Subject subject = SecurityUtils.getSubject();
		if (!url.startsWith(RequestUtil.PUBLIC) && !subject.isAuthenticated() ) {
			resp.addHeader(RequestUtil.ACCESS_CONTROLL, RequestUtil.UNLOGIN);
		}
		return super.onPreHandle(request, response, mappedValue);
	}

}
