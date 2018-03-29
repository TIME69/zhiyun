package com.cnkeep.test.spring;

public class JdbcPropertesInitTest {
	private String userName;

	private String password;

	private String driverClass;

	public void init() {
		System.out.println("init ........");
	}

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
