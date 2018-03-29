package com.cnkeep.test.aop;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ComponentScan("com.cnkeep.test.aop")
@SpringBootTest(classes=SysLogMain.class)
public class SysLogMain {
	@Autowired
	private SysLogSimple sysLogSimple;
	
	@Test
	public void sysLogAopTest(){
		List<String> data = new ArrayList<String>();
		data.add("水果");
		data.add("衣服");
		sysLogSimple.print("tom", data );
	}
}
