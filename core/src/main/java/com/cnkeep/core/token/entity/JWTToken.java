package com.cnkeep.core.token.entity;

import com.cnkeep.core.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @description jwt TOken
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2018年1月2日
 * 
 *       <pre>
 * {
	"header":{
		"typ":"JWT",
		"alg":"HS256"
	},
	"payload":{
		"iss":"www.cnkeep.cn",
		"iat":1441593502,
		"exp":1441594755,
		"aud":"zhiyun",
		"sub":"zhiyun_user",
		"from_user":"admin",
		"target_user":""
	},
	"signature":""
}
 *       </pre>
 */
public class JWTToken implements TokenTemplate{
	private Header header;
	private Payload payload;
	private String signature;

	
	public JWTToken() {
		this.header = new Header();
		this.payload = new Payload();
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String toJson() throws JsonProcessingException {
		return JsonUtil.serialize(this);
	}

	public static void main(String[] args) throws JsonProcessingException {
		JWTToken token = new JWTToken();
		token.setHeader(new Header());
		token.setPayload(new Payload());
		System.out.println(token.toString());
	}

	@Override
	public long getExpired() {
		return this.getPayload().getExp();
	}
}
