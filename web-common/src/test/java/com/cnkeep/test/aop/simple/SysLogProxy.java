package com.cnkeep.test.aop.simple;

import java.util.List;

import com.cnkeep.test.aop.SysLogSimple;

public class SysLogProxy {
	private SysLogSimple sysLogSimple;

	public SysLogProxy(SysLogSimple sysLogSimple) {
		super();
		this.sysLogSimple = sysLogSimple;
	}

	public void before() {
		System.out.println("invoke method before");
	}

	public void after() {
		System.out.println("invoke method afer");
	}

	public List<String> print(String userName, List<String> data) {
		before();
		List<String> print = this.sysLogSimple.print(userName, data);
		after();
		return print;
	}
}
