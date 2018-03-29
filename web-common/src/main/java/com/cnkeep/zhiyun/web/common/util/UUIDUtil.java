package com.cnkeep.zhiyun.web.common.util;

import java.util.UUID;

/**
 * @description UUID操作类 
 * @author <a href="zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @date 2017年12月1日
 */
public class UUIDUtil {
	/**
	 * @description 生成全局唯一标识UUID 
	 * @author <a href="mailto:zhangleili@wxchian.com">LeiLi.Zhang</a>
	 * @return
	 */
	public static String uuid(){
		return UUID.randomUUID().toString();
	}
}
