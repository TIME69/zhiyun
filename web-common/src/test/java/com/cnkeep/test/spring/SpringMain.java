package com.cnkeep.test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		//获取通过注解注入properties的bean
		JdbcPropertesAnnotationInitTest beanCreateByAnnotation = applicationContext.getBean(JdbcPropertesAnnotationInitTest.class);
		System.out.println(beanCreateByAnnotation);
		
		//获取通过xml注入properties的bean
		JdbcPropertesInitTest beanCreateByXml = (JdbcPropertesInitTest) applicationContext.getBean("jdbcPropertiesInit");
		System.out.println(beanCreateByXml);
		
	}
}
