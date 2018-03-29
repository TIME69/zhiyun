package com.cnkeep.core.token.entity.enums;
/**
 * @description token生成类型 
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2018年1月17日
 */
public enum TokenType {
	JWT(0,"JWT"),UUID(1,"UUID");
	private int index;
	private String message;
	private TokenType(int index, String message) {
		this.index = index;
		this.message = message;
	}
	
	public int getIndex() {
		return index;
	}

	public String getMessage() {
		return message;
	}

	public static TokenType getType(int index){
		for (TokenType type : TokenType.values()) {
			if(type.index == index)
				return type;
		}
		throw new IllegalArgumentException(index +" is an unsupport index!!!");
	}
}
