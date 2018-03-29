package com.cnkeep.zhiyun.web.common.domain.enums;

import com.cnkeep.zhiyun.web.common.domain.handler.HasIndexValue;

/**
 * * @Description 用户状态枚举
 * <ul>
 * <li>NORMAL(0):正常</li>
 * <li>SUSPEND(1):停用</li>
 * <li>TERMINATTE(2):禁用</li>
 * </ul>
 * 
 * @author <a href="mailto:zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @Date 2017年9月16日
 * @Version 0.0.0
 */
public enum AccountState implements HasIndexValue{
	/** 正常状态 **/
	NORMAL(0,"正常"),
	/** 停用状态 **/
	SUSPEND(1,"账号已冻结"),
	/** 禁用状态 **/
	TERMINATE(2,"账号已禁用");

	private int index;
	private String desc;
	
	private AccountState(int index,String desc) {
		this.index = index;
		this.desc = desc;
	}

	public int getIndex() {
		return index;
	}

	public String getDesc(){
		return this.desc;
	}
	
	public static AccountState getType(int index){
		for(AccountState state:AccountState.values()){
			if(state.index == index){
				return state;
			}
		}
		throw new IllegalArgumentException("Unsupport state. The state only support 0 or 1 or 2");
	}
}
