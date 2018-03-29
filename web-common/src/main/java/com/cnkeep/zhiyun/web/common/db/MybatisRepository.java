package com.cnkeep.zhiyun.web.common.db;

import java.io.Serializable;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnkeep.zhiyun.web.common.domain.BaseEntity;
import com.cnkeep.zhiyun.web.common.exception.RepositoryException;

public abstract class MybatisRepository<E extends BaseEntity> implements EntityRepository<E> {
	private Logger logger = LoggerFactory.getLogger(MybatisRepository.class);
	protected SqlSessionFactory sqlSessionFactory;
	private final String BASE_PACK = "com.cnkeep.zhiyun.web.mapper.";
	
	protected abstract String namespaceSqlId();

	protected abstract void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory);

	protected String fullSqlId(String sqlId) {
		return BASE_PACK + namespaceSqlId() + "." + sqlId;
	}

	@Override
	public E getById(Serializable id) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectOne(fullSqlId("getById"), id);
		}
	}

	@Override
	public int deleteById(Serializable id) throws RepositoryException{
		int count = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			count = session.delete(fullSqlId("deleteById"), id);
			session.commit(true);
		} catch (Exception e) {
			logger.error("remove Entity failed:",e);
			throw new RepositoryException(e);
		}
		return count;
	}

	@Override
	public int insert(E entity) {
		int count = 0;
		try(SqlSession session = sqlSessionFactory.openSession()){
			count = session.insert(fullSqlId("insert"), entity);
			session.commit(true);
		}catch (Exception e) {
			logger.error("insert Entity failed:",e);
			throw new RepositoryException(e);
		}
		return count;
	}

	@Override
	public int update(E entity) {
		int count = 0;
		try(SqlSession session = sqlSessionFactory.openSession()){
			count = session.update(fullSqlId("update"), entity);
			session.commit(true);
		}catch (Exception e) {
			logger.error("update Entity failed:",e);
			throw new RepositoryException(e);
		}
		return count;
	}

}
