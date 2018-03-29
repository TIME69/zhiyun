package com.cnkeep.zhiyun.web.common.domain;

/**
 * @description 包含可扩充附件信息的实体接口
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年12月1日
 */
public abstract class BaseExtraEntity implements BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 附加信息
	 */
	private String extra;

	public void setExtra(String extra) {
		this.extra = extra;
	}

	/**
	 * @description 设置附加信息
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public String getExtra() {
		return this.extra;
	}
}
