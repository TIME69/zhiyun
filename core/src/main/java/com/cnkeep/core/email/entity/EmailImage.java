package com.cnkeep.core.email.entity;
/**
 * @description 邮件图片实体 
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年11月30日
 */
public class EmailImage {
	private byte[] data;
	private String url;
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
