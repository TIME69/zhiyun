package com.cnkeep.zhiyun.web.frontkit;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.cnkeep.zhiyun.web.common.security.ShiroConfig;

@SpringBootApplication //=@AutoConfiguration+@EnableAutoConfiguration+@Configuration
//@MapperScan("com.cnkeep.zhuyun.web.mapper")
@ComponentScan("com.cnkeep.zhiyun.web")//配置扫描包路径
public class StartServer {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(StartServer.class);
		Set<Object> sources = new  LinkedHashSet<Object>();
		//sources.add("classpath:application.xml"); //添加其他的自定义bean配置
		app.setSources(sources );				
		ConfigurableApplicationContext run = app.run(args);
		ShiroConfig bean = run.getBean(ShiroConfig.class);
		System.out.println(bean.getFilters());
		
		
	}
}
