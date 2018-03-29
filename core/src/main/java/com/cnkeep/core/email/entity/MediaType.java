package com.cnkeep.core.email.entity;

/**
 * 
 * @description 媒体文件类型枚举类
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年11月20日
 */
public enum MediaType {
	/** 图片 **/
	img(0),
	/** 音频 **/
	voice(1),
	/** 视频 **/
	vedio(2);
	private int code;

	MediaType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static MediaType getMediaType(int code) {
		for (MediaType type : MediaType.values()) {
			if (type.code == code) {
				return type;
			}
		}
		throw new IllegalArgumentException(code + " not support");
	}
}
