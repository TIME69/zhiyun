package com.cnkeep.zhiyun.web.common.db.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class ZhiYunDataSourceConfig {

	@Value("${mybatis.config-location}")
	private String mybatisConfigLocation;
	
	@Value("${mybatis.mapper-locations}")
	private String mapperLocations;
	
	@Autowired
	private ZhiYunConfig dataSourceConfig;
	
	@Bean("zhiyunSessionFactory")
	public SqlSessionFactory sessionFactory(@Qualifier("zhiyunDataSource") DataSource dataSource) throws Exception{
		ClassPathResource resource = new ClassPathResource(mybatisConfigLocation);
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setConfigLocation(resource);
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		System.out.println("init mapper xml:"+mapperLocations);
		factory.setMapperLocations(resolver.getResources(mapperLocations));
		//factory.setMapperLocations(resolver.getResources("classpath*:com/cnkeep/zhiyun/web/mapper/*.xml"));
		return factory.getObject();
	}
	
	@Primary
	@Bean("zhiyunDataSource")
	@ConfigurationProperties(prefix="datasource")
	public DataSource dataSource(){
		DataSource dataSource = DataSourceBuilder.create().type(BasicDataSource.class).build();
		System.out.println(dataSource);
		return dataSource;
	}
	
	public static void main(String[] args) throws IOException {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath*:com/cnkeep/zhiyun/web/mapper/*.xml");
		for (Resource resource : resources) {
			System.out.println(resource.toString());
		}
	}
}
