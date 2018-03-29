package com.cnkeep.zhiyun.web.common.domain.dto;

import com.cnkeep.zhiyun.web.common.domain.entity.User;

public class UserDTO extends User{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String identity;
	private String token;
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
