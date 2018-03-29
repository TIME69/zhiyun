package com.cnkeep.zhiyun.web.common.domain;

import java.io.Serializable;

/**
 * @Description实体类通用接口，所有的实体都实现该接口
 * @author <a href="mailto:zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @Date 2017年9月16日
 * @Version 0.0.0
 */
public interface BaseEntity extends Serializable{
	Serializable getId();
}
