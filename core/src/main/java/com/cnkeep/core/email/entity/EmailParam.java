package com.cnkeep.core.email.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮件发送基本信息实体
 * 
 * @description
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年11月20日
 */
public class EmailParam {
	/** 服务器主机地址 **/
	private String host = "smtp.mxhichina.com";

	/** 服务端口 **/
	private int port = 25;

	/** 登录名 **/
	private String user = "zhangleili@cnkeep.cn";

	/** 登录密码 **/
	private String password = "ZhangYu131417";

	/** 收件人列表 **/
	private List<String> receviceAddress = new ArrayList<String>();

	/** 抄送人列表 **/
	private List<String> ccAddress = new ArrayList<String>();

	/** 私密收件人列表 **/
	private List<String> bccAddress = new ArrayList<String>();

	/** 正文内容实体（文字内容+图片信息） **/
	private EmailContent content = new EmailContent();

	/** 邮件协议 **/
	private EmailProtocol protocol = EmailProtocol.SMTP;

	/** 是否开启debug模式，打印相关 信息 **/
	private String debugger = "true";

	private String isSSL = "false";
	/** 编码 **/
	private String charset;

	/** 默认编码格式 **/
	private final String DEFAULT_CHARSET = "utf-8";

	/**
	 * @description 邮件服务器主机
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @description 邮件服务器主机
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @description 登录名
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @description 登录名
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @description 收件人列表
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public List<String> getReceviceAddress() {
		return receviceAddress;
	}

	/**
	 * @description 收件人列表
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param receviceAddress
	 */
	public void setReceviceAddress(List<String> receviceAddress) {
		this.receviceAddress = receviceAddress;
	}

	/**
	 * @description 邮件内容
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public EmailContent getContent() {
		return content == null ? new EmailContent() : content;
	}

	public void setContent(EmailContent content) {
		this.content = content;
	}

	/**
	 * @description 邮件协议
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public EmailProtocol getProtocol() {
		return protocol;
	}

	/**
	 * @description 邮件协议
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param protocol
	 */
	public void setProtocol(EmailProtocol protocol) {
		this.protocol = protocol;
	}

	public String getDebugger() {
		return debugger;
	}

	/**
	 * @description 是否开启debugger模式，打印信息
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param debugger
	 */
	public void setDebugger(String debugger) {
		this.debugger = debugger;
	}

	/**
	 * @description 抄送人列表
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public List<String> getCcAddress() {
		return ccAddress;
	}

	/**
	 * @description 抄送人列表
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param ccAddress
	 */
	public void setCcAddress(List<String> ccAddress) {
		this.ccAddress = ccAddress;
	}

	/**
	 * @description 抄送人列表
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public List<String> getBccAddress() {
		return bccAddress;
	}

	/**
	 * @description 设置私密收件人列表
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param bccAddress
	 */
	public void setBccAddress(List<String> bccAddress) {
		this.bccAddress = bccAddress;
	}

	/**
	 * @description 获取内容字符编码
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public String getCharset() {
		return charset == null ? DEFAULT_CHARSET : charset;
	}

	/**
	 * @description 设置字符编码
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @param charset
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getIsSSL() {
		return isSSL;
	}

	public void setIsSSL(String isSSL) {
		this.isSSL = isSSL;
	}
}
