package com.cnkeep.core.token.entity;

import com.cnkeep.core.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @description 头部
 * @date 2018年1月2日
 */
public class Header {
	/** token类型 **/
	@JsonProperty("typ")
	private String type;

	/** 加密算法 **/
	@JsonProperty("alg")
	private String alg;

	public Header() {
		super();
	}

	public Header(String type, String alg) {
		super();
		this.type = type;
		this.alg = alg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAlg() {
		return alg;
	}

	public void setAlg(String alg) {
		this.alg = alg;
	}

	public String toJson() throws JsonProcessingException {
		return JsonUtil.serialize(this);
	}
}
