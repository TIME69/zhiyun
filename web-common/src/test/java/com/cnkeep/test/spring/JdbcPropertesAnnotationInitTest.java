package com.cnkeep.test.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 
 * @description 通过@Value注解注入jdbc.properties中的属性 
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年9月27日
 */
@Component
public class JdbcPropertesAnnotationInitTest {
	@Value("${jdbc.username}")
	private String userName;
	
	@Value("${jdbc.password}")
	private String password;
	
	@Value("${jdbc.driverclass}")
	private String driverClass;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	@Override
	public String toString() {
		return "JdbcPropertesInitTest [userName=" + userName + ", password=" + password + ", driverClass=" + driverClass
				+ "]";
	}
}
