package com.cnkeep.core.token.manager;

/**
 * @description token 管理器 
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2018年1月17日
 */
public interface TokenManager {
	public String make(String target);
	public int check(String token);
}
