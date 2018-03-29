package com.cnkeep.zhiyun.web.frontkit.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cnkeep.zhiyun.web.common.domain.JsonResp;
import com.cnkeep.zhiyun.web.common.domain.entity.User;
import com.cnkeep.zhiyun.web.common.exception.CoreException;
import com.cnkeep.zhiyun.web.common.security.SessionUtils;
import com.cnkeep.zhiyun.web.common.service.UserService;
import com.cnkeep.zhiyun.web.common.util.Constants;

@Component
@Path("login")
public class LoginResource {
	private final static Logger logger = LoggerFactory.getLogger(LoginResource.class);
	@Autowired
	private UserService userService;

	/**
	 * 是否开启验证码校验
	 */
	@Value("${systemConfig.verifyCode}")
	private String verfiyVode;
	
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	public JsonResp login() {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return JsonResp.fail(Constants.LOGIN_TIMEOUT_OR_FAIL);
		}
		return JsonResp.success();
	}

	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_JSON })
	public JsonResp login(@Valid User user) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken();
		token.setUsername(user.getIdentify());
		token.setPassword(user.getCredential().toCharArray());

		try {
			subject.login(token);
		} catch (CoreException e) {
			return JsonResp.fail(e.getMessage());
		} catch (AuthenticationException e) {
			return JsonResp.fail(Constants.LOGIN_TIMEOUT_OR_FAIL);
		} catch (Exception e) {
			logger.error("user[{}] login error, may be by server's error. {}", user, e);
			return JsonResp.fail(Constants.LOGIN_TIMEOUT_OR_FAIL);
		}
		User loginUser = userService.getLoginUser(user.getIdentify());
		SessionUtils.setUser(loginUser);

		return JsonResp.success();
	}
//
//	@GET
//	@Path("hello/{id}")
//	@Produces(value = { MediaType.APPLICATION_JSON })
//	public JsonResp hello(@PathParam("id") int id) {
//		User user = userService.getById(id);
//
//		return JsonResp.success("hello" + user.getIdentify());
//	}
}
