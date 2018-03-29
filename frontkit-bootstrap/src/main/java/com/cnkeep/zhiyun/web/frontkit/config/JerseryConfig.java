package com.cnkeep.zhiyun.web.frontkit.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.cnkeep.zhiyun.web.common.filter.AllowOriginFilter;
@Configuration
public class JerseryConfig extends ResourceConfig{

	public JerseryConfig() {
		packages("com.cnkeep.zhiyun.web.frontkit.resources");
		register(AllowOriginFilter.class);
	}
	
}
