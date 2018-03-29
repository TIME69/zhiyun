package com.cnkeep.core.token.manager;

import com.cnkeep.core.token.entity.JWTToken;

public class DefaultTokenManager implements TokenManager{
	private JWTToken serverToken;
	
	
	
	public DefaultTokenManager(JWTToken serverToken) {
		super();
		this.serverToken = serverToken;
	}

	public JWTToken getInitToken(){
		JWTToken token = new JWTToken();
		token.getHeader().setType(serverToken.getHeader().getType());
		return token;
	}
	
	public String make(String target) {
		JWTToken token = getInitToken();
		token.getPayload().setTargetUser(target);
		return token.toString();
	}

	@Override
	public int check(String token) {
		return 0;
	}
}
