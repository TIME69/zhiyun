/**智云系统数据库**/
CREATE DATABASE zhiyun CHARACTER SET utf8 COLLATE utf8_general_ci; 
USE zhiyun;

CREATE TABLE zy_sys_config(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	config_key VARCHAR(30) COMMENT '配置字段名称',
	congfig_value VARCHAR(100) COMMENT '配置字段值'
)COMMENT '系统配置表';

CREATE TABLE zy_user(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	user_name VARCHAR(20),
	state INT DEFAULT 0 COMMENT '账号状态，0：正常，1：锁定，2：禁用',
	age INT DEFAULT 0
) COMMENT '用户表';

/**添加字段**/
ALTER TABLE zy_user ADD COLUMN state INT DEFAULT 0;
ALTER TABLE zy_user ADD COLUMN PASSWORD VARCHAR(50) NOT NULL;
/**添加注释
alter table zy_user modify column  integer add comment '';
**/

/**权限表**/
CREATE TABLE zy_permission(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	NAME VARCHAR(20) NOT NULL COMMENT '权限名称',
	parent_id INTEGER COMMENT '父级权限id',
	url VARCHAR(100) COMMENT '权限对应url'
)COMMENT '用户权限表';
/**重命名**/
RENAME TABLE zy_permission TO zy_permission;

/**角色表**/
CREATE TABLE zy_role(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	role_name VARCHAR(30) NOT NULL COMMENT '角色名称',
	comments VARCHAR(100) COMMENT '角色备注',
	is_default INT NOT NULL DEFAULT 0 COMMENT '是否为默认角色，0：否，1：是'
)COMMENT '角色表';

CREATE TABLE zy_user_role(
	user_id INTEGER NOT NULL COMMENT '用户id',
	role_id INTEGER NOT NULL COMMENT '角色id',
	data_scope INT NOT NULL DEFAULT 0 COMMENT '数据权限，0：全局，1：个人，2:部门及子部门，3：仅当前部门，4：不限制'
)COMMENT '用户、角色关联表';

/**删除user_role 中的data_scope 字段**/
ALTER TABLE zy_user_role DROP COLUMN data_scope;
ALTER TABLE zy_user_role ADD PRIMARY KEY(user_id,role_id);

/**角色权限表**/
CREATE TABLE zy_role_permission(
	role_id INTEGER NOT NULL COMMENT '角色id',
	permission_id INTEGER NOT NULL COMMENT '权限Id',
	data_scope INT NOT NULL DEFAULT 0 COMMENT '数据权限，0：全局，1：个人，2:部门及子部门，3：仅当前部门，4：不限制'
)COMMENT '权限角色关联表';

/**设置角色权限关系表主键**/
ALTER TABLE zy_role_permission ADD PRIMARY KEY(role_id,permission_id);