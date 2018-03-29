package com.cnkeep.core.token.entity;

import com.cnkeep.core.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @description 负载内容 
 * @date 2018年1月2日
 */
public class Payload {
	/**签发者**/
	@JsonProperty("iss")
	private String iss;
	
	/**签发日期**/
	@JsonProperty("iat")
	private long iat;
	
	/**过期日期**/
	@JsonProperty("exp")
	private long exp;
	
	/**接收方**/
	@JsonProperty("aud")
	private String aud;
	
	/**面向的对象**/
	@JsonProperty("sub")
	private String sub;
	
	/**签发人**/
	@JsonProperty("from_user")
	private String fromUser;
	
	/**目标对象**/
	@JsonProperty("target_user")
	private String targetUser;

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public long getIat() {
		return iat;
	}

	public void setIat(long iat) {
		this.iat = iat;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public String getAud() {
		return aud;
	}

	public void setAud(String aud) {
		this.aud = aud;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getTargetUser() {
		return targetUser;
	}

	public void setTargetUser(String targetUser) {
		this.targetUser = targetUser;
	}
	
	public String toJson() throws JsonProcessingException {
		return JsonUtil.serialize(this);
	}
}

