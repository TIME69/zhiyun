package com.cnkeep.zhiyun.web.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnkeep.zhiyun.web.common.domain.dto.UserDTO;
import com.cnkeep.zhiyun.web.common.domain.entity.User;
import com.cnkeep.zhiyun.web.common.domain.repo.UserRepo;

/**
 * 
 * @description 账户相关操作的service
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年9月27日
 */
@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepo userRepo;

	public User getById(int id) {
		return userRepo.getById(id);
	}

	public User getLoginUser(String identify) {
		User user = new User();
		user.setIdentify(identify);

		return userRepo.getUserByIdentify(user);
	}

	public boolean verfiyToken(UserDTO user) {
		String identity = user.getIdentity();
		String token = user.getToken();
		if(logger.isDebugEnabled()){
			logger.debug("active account {} with token {}",identity,token);
		}
		
		return true;
	}
}
