package com.cnkeep.zhiyun.web.common.domain.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.cnkeep.zhiyun.web.common.domain.BaseExtraEntity;
import com.cnkeep.zhiyun.web.common.domain.enums.AccountState;

/**
 * @Description 用户实体类
 * @author <a href="mailto:zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @Date 2017年12月3日
 * @Version 0.0.0
 */
public class User extends BaseExtraEntity {
	private static final long serialVersionUID = -8353679398760859881L;
	private String id;
	/**
	 * 用户名
	 */
	private String name;

	/**
	 * 登录账号名
	 */
	@NotEmpty(message = "不能为空")
	private String identify;

	/**
	 * 登录账号密码
	 */
	@NotBlank(message = "不能为空")
	private String credential;

	/** 激活账号邮箱地址 **/
	private String email;
	
	/**
	 * 用户状态
	 */
	private AccountState state;

	/**
	 * 最近一次登录时间
	 */
	private Date lastLoginTime;

	/**
	 * 最近一次登录ip地址
	 */
	private String lastLoginIp;

	/**
	 * 信任ip列表，IP之间使用英文逗号分隔
	 */
	private String trustIps;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AccountState getState() {
		return state;
	}

	public void setState(AccountState state) {
		this.state = state;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getTrustIps() {
		return trustIps;
	}

	public void setTrustIps(String trustIps) {
		this.trustIps = trustIps;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", identify=" + identify + ", credential=" + credential
				+ ", state=" + state + ", lastLoginTime=" + lastLoginTime + ", lastLoginIp=" + lastLoginIp
				+ ", trustIps=" + trustIps + "]";
	}
}
