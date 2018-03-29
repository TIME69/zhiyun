package com.cnkeep.zhiyun.web.common.domain;

/**
 * * @Description 返回json结果实体类
 * 
 * @author <a href="mailto:zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @Date 2017年9月16日
 * @Version 0.0.0
 */
public class JsonResp {
	/** 默认空白信息 **/
	private final static String BLANK_MESSAGE = "";

	/** 成功 **/
	private final static int SUCCESS = 0;

	/** 失败 **/
	private final static int FAILED = -1;

	/** 状态码 **/
	private int code;

	/** 响应数据 **/
	private Object data;

	/** 响应提示信息 **/
	private String message = BLANK_MESSAGE;

	public JsonResp(int code, Object data, String message) {
		super();
		this.code = code;
		this.data = data;
		this.message = message;
	}

	public static JsonResp success() {
		return new JsonResp(SUCCESS, null, BLANK_MESSAGE);
	}

	public static JsonResp success(Object data) {
		return new JsonResp(SUCCESS, data, BLANK_MESSAGE);
	}

	public static JsonResp success(String message) {
		return new JsonResp(SUCCESS, null, message);
	}

	public static JsonResp success(Object data, String message) {
		return new JsonResp(SUCCESS, data, message);
	}

	public static JsonResp fail() {
		return new JsonResp(FAILED, null, BLANK_MESSAGE);
	}

	public static JsonResp fail(Object data) {
		return new JsonResp(FAILED, data, BLANK_MESSAGE);
	}

	public static JsonResp fail(String message) {
		return new JsonResp(FAILED, null, message);
	}

	public static JsonResp fail(Object data, String message) {
		return new JsonResp(FAILED, data, message);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
