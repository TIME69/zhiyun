package com.cnkeep.zhiyun.web.common.domain.entity;

import java.util.List;

public class Role {
	private Integer id;
	private String name;
	private int defalut;
	private List<Permission> permissions;
	private int dataScope;
}
