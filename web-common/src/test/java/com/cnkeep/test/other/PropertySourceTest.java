package com.cnkeep.test.other;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @description 测试@PropertySource 注解加载自定义配置文件 
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年9月27日
 */

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes=PropertySourceTest.class)// 指定spring-boot的启动类 
@Configuration
@PropertySource("classpath:config.properties")
public class PropertySourceTest {
	@Value("${conf.ip}")
	private String ip;
	
	@Value("${conf.port}")
	private int port;
	
	@Test
	public void test(){
		System.out.println("ip:"+ip);
		System.out.println("port:"+port);
	}
}
