package com.cnkeep.core.token;

import com.cnkeep.core.token.entity.JWTToken;
import com.cnkeep.core.token.entity.TokenTemplate;
import com.cnkeep.core.token.entity.enums.TokenType;
import com.cnkeep.core.token.manager.DefaultTokenManager;
import com.cnkeep.core.token.manager.TokenManager;
import com.cnkeep.core.token.manager.UUIDTokenManager;

/**
 * @description token构建器
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2018年1月17日 { "header":{ "typ":"JWT", "alg":"HS256" }, "payload":{
 *       "iss":"www.cnkeep.cn", "iat":1441593502, "exp":1441594755,
 *       "aud":"zhiyun", "sub":"zhiyun_user", "from_user":"admin",
 *       "target_user":"" }, "signature":""
 */
public class TokenBuilder {
	private final TokenType DEFAULT_TYPE = TokenType.JWT;
	private final long DEFAULT_EXPIRED_TIME = 1000 * 60 * 3;
	private TokenType tokenType;

	public static void main(String[] args) {
		TokenManager manager = new TokenBuilder().build();
		String make = manager.make("");
	}

	public TokenBuilder tokenType(TokenType type) {
		this.tokenType = type;
		return this;
	}

	public TokenBuilder expireTime(long time) {
		return this;
	}

	public TokenManager build() {
		TokenManager tokenManager = null;
		switch (this.tokenType) {
		case JWT:
			JWTToken token = new JWTToken();
			token.getHeader().setType(tokenType.getMessage());
			tokenManager = new DefaultTokenManager(new JWTToken());
			break;
		case UUID:
			tokenManager = new UUIDTokenManager(null);
			break;
		default:
			tokenManager = new DefaultTokenManager(null);
			break;
		}
		return tokenManager;
	}
}
