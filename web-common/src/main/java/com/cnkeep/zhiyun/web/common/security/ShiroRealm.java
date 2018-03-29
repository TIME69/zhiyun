package com.cnkeep.zhiyun.web.common.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cnkeep.zhiyun.web.common.domain.entity.User;
import com.cnkeep.zhiyun.web.common.domain.enums.AccountState;
import com.cnkeep.zhiyun.web.common.exception.CoreException;
import com.cnkeep.zhiyun.web.common.service.UserService;
import com.cnkeep.zhiyun.web.common.util.Constants;

public class ShiroRealm extends AuthorizingRealm {
	private final Logger logger = LoggerFactory.getLogger(AuthorizingRealm.class);
	@Autowired
	private UserService userService;

	/**
	 * @description 获取授权信息，即权限信息
	 * @param principals
	 *            身份信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 获取可用身份信息，即登录名
		String identify = (String) getAvailablePrincipal(principals);
		// TODO 根据个人登录名获取个人权限信息

		Set<String> permissionStringSet = new HashSet<String>();
		permissionStringSet.add("/download/attachments");

		authorizationInfo.setStringPermissions(permissionStringSet);
		return authorizationInfo;
	}

	/**
	 * @description 判断是否拥有该权限
	 * @param principals
	 *            身份信息
	 * @param url
	 * @return 是否具有访问权限
	 */
	@Override
	public boolean isPermitted(PrincipalCollection principals, String url) {
		AuthorizationInfo info = getAuthorizationInfo(principals);
		Collection<String> permissions = info.getStringPermissions();
		for (String permission : permissions) {
			logger.info("{} is scanning by url({}) match permisson {}", principals, url, permission);
			if (permission.equals(url)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取登录认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken curToken = (UsernamePasswordToken) token;
		String identify = curToken.getUsername();

		User loginUser = userService.getLoginUser(identify);
		if (loginUser == null || null == loginUser.getId()) {
			throw new CoreException(Constants.PASSWORD_ERROR);
		}
		loginUser.setIdentify(identify);
		checkState(loginUser.getState());
		logger.info("login user:{}",loginUser);
		return new SimpleAuthenticationInfo(identify, loginUser.getCredential(), getName());
	}

	
	// 检查用户状态
	public void checkState(AccountState state) {
		switch (state) {
		case NORMAL:
			break;
		case SUSPEND:
			throw new CoreException(AccountState.SUSPEND.getDesc());
		case TERMINATE:
			throw new CoreException(AccountState.TERMINATE.getDesc());
		default:
			break;
		}
	}

}
