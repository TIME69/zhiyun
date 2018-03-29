package com.cnkeep.zhiyun.web.common.db.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="dataSource")
public class ZhiYunConfig extends DataSourceConfig{
	
	
	
}
