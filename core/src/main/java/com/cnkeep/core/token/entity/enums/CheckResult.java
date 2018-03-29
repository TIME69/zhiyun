package com.cnkeep.core.token.entity.enums;
/**
 * @description token验证结果枚举类 
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2018年1月17日
 */
public enum CheckResult {
	NORMAL(0, "合法"), ILLEGAL(-1, "非法"), expired(-2, "已过期");
	private String message;
	private int index;

	private CheckResult(int index, String message) {
		this.index = index;
		this.message = message;
	}

	
	public String getMessage() {
		return message;
	}


	public int getIndex() {
		return index;
	}

	public static CheckResult getType(int index) {
		for (CheckResult result : CheckResult.values()) {
			if (index == result.index)
				return result;
		}
		throw new IllegalArgumentException("unsupported index!!!");
	}
}
