package com.cnkeep.zhiyun.web.common.domain.enums;

import com.cnkeep.zhiyun.web.common.domain.handler.HasIndexValue;

/**
 * @description 登录类型枚举类
 * <ul>
 * 	<li>1：前台</li>
 * 	<li>2：后台</li>
 * 	<li>4：接口</li>
 * </ul>
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年9月29日
 */
public enum LoginType implements HasIndexValue {
	FRONTKIT(1),BACK(2),INTERFACE(4);
	private int index;
	LoginType(int index){
		this.index = index;
	}
	public int getIndex() {
		return index;
	}
	
	public static LoginType getType(int index){
		for(LoginType type:LoginType.values()){
			if(type.index == index){
				return type;
			}
		}
		throw new IllegalArgumentException("Unsuport index. The supported index is 1 to 7.");
	}
}
