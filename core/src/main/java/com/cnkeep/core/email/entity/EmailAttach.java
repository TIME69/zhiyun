package com.cnkeep.core.email.entity;

/**
 * @description 邮件附件实体
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年11月30日
 */
public class EmailAttach {
	/**
	 * 附件名称
	 */
	private String fileName;

	/**
	 * 附件url地址
	 */
	private String url;

	/**
	 * 附件字节数据
	 */
	private byte[] data;

	/**
	 * @description 获取文件名
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @description  设置文件名
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @description 获取附件URL 
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @description 设置附件URL 
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @description 获取附件字节数据 
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * @description 设置附件字节数据 
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param data
	 */
	public void setData(byte[] data) {
		this.data = data;
	}
}
