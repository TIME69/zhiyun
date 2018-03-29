package com.cnkeep.zhiyun.web.common.security.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnkeep.zhiyun.web.common.util.RequestUtil;

/**
 * @Description 权限拦截器
 * @author <a href="mailto:zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @Date 2017年11月28日
 * @Version 0.0.0
 */
public class ShiroAuthorizationFilter extends AuthorizationFilter {
	private static final Logger logger = LoggerFactory.getLogger(ShiroAuthorizationFilter.class);

	/**
	 * @description 权限认证
	 * @return 是否允许访问，false将会跳转到配置的unauthorizedUrl
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		Subject subject = SecurityUtils.getSubject();
		logger.info("access allowed mappedValue {}",mappedValue);
		boolean isPermitted = false;
		if (subject.isAuthenticated()) {
			//获取访问url
			String url = RequestUtil.gerUrlPath(req);
			if (StringUtils.isBlank(url)) {
				logger.info("ShiroAuthorizationFilter: URL is blank!");
				return false;
			}
			
			logger.info("ShiroAuthorizationFilter: URL is {}", url);
			//交由shiro判断是否具有权限
			if(!subject.isPermitted(url)){
				resp.addHeader("access-controll", "no permission");
			}else{
				isPermitted = true;
			}
		}else{
			resp.addHeader("access-controll", "unlogin");
		}
		
		return isPermitted;
	}

}
