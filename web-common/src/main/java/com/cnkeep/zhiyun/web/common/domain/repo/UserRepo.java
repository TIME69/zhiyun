package com.cnkeep.zhiyun.web.common.domain.repo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cnkeep.zhiyun.web.common.db.ZhiYunRepository;
import com.cnkeep.zhiyun.web.common.domain.entity.User;

@Repository
public class UserRepo extends ZhiYunRepository<User> {
	private final String nameSpace = "UserMapper";
	
	@Override
	protected String namespaceSqlId() {
		return nameSpace;
	}
	
	public User getUserByIdentify(User user){
		try(SqlSession session = sqlSessionFactory.openSession()){
			return session.selectOne(fullSqlId("getUserByIdentify"), user);
		}
	}
}
