package com.cnkeep.zhiyun.web.common.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cnkeep.zhiyun.web.common.domain.BaseEntity;

public abstract class ZhiYunRepository<E extends BaseEntity> extends MybatisRepository<E> {
	@Autowired
	@Qualifier("zhiyunSessionFactory")
	@Override
	protected void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

}
