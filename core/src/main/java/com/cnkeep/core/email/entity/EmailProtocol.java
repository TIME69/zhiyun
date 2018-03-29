package com.cnkeep.core.email.entity;

/**
 * 邮件协议枚举类
 * 
 * @description
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年11月20日
 */
public enum EmailProtocol {
	SMTP(25, "smtp"), POP3(110, "pop3"), IMAP4(143, "imap4");

	/** 协议端口 **/
	private int port;
	/** 协议名称 **/
	private String protocol;

	EmailProtocol(int port, String protocol) {
		this.port = port;
		this.protocol = protocol;
	}

	public int getPort() {
		return port;
	}

	public String getProtocol() {
		return protocol;
	}

	public EmailProtocol getProtocol(int port) {
		for (EmailProtocol protocol : EmailProtocol.values()) {
			if (protocol.port == port)
				return protocol;
		}
		throw new IllegalArgumentException(port + " not support");
	}
}
