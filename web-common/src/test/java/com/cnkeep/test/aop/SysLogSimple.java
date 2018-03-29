package com.cnkeep.test.aop;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class SysLogSimple {
	@SysLogAnnotation(option = "将username添加至数据中")
	public List<String> print(String userName,List<String> data){
		data.add(userName);
		System.out.println(data);
		return data;
	}
}
