package com.cnkeep.core.token.entity.enums;
/**
 * @description 加密方式 
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2018年1月17日
 */
public enum EncryType {
	MD5(0,"MD5"),BASE64(1,"BASE64");
	private int index;
	private String message;
	private EncryType(int index, String message) {
		this.index = index;
		this.message = message;
	}
	
	public int getIndex() {
		return index;
	}

	public String getMessage() {
		return message;
	}

	public static EncryType getType(int index){
		for (EncryType type : EncryType.values()) {
			if(type.index == index)
				return type;
		}
		throw new IllegalArgumentException(index +" is an unsupport index!!!");
	}
}
