package com.cnkeep.zhiyun.web.common.db;

import java.io.Serializable;

import com.cnkeep.zhiyun.web.common.domain.BaseEntity;

public interface EntityRepository<E extends BaseEntity> {
	E getById(Serializable id);
	int deleteById(Serializable id);
	int insert(E entity);
	int update(E entity);
}
