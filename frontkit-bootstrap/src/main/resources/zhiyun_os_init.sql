#初始化智云统一认证系统数据库
CREATE DATABASE zhiyun_os CHARSET utf8;

#系统配置表
CREATE TABLE sys_config(
    conf_key VARCHAR(30) COMMENT'配置键值',
    conf_value VARCHAR(30) COMMENT'配置属性值'
)COMMENT '系统配置项';

#系统日志表
CREATE TABLE sys_log(
	id VARCHAR(40) COMMENT'日志记录id,采用UUID',
	option_obj VARCHAR(30) COMMENT'操作对象',
	platform_id INTEGER COMMENT'系统id',
	user_id INTEGER COMMENT'用户id',
	option_time DATETIME COMMENT'操作时间戳',
	remark TEXT COMMENT'备注'
)COMMENT'系统日志表';

#平台表
CREATE TABLE IF NOT EXISTS platform(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(30) NOT NULL DEFAULT '' COMMENT'平台名称',
    VERSION VARCHAR(10) DEFAULT '0.0.0' COMMENT '平台版本',
    create_time DATETIME COMMENT '平台创建日期',
    state TINYINT NOT NULL DEFAULT 0 COMMENT'平台状态，0：正常（默认），1：维护中，2：下线，3：测试中',
    remark VARCHAR(100) COMMENT'备注'
)COMMENT'平台类型表';

#系统插件库
CREATE TABLE IF NOT EXISTS os_plugins(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	plugins_name VARCHAR(20) NOT NULL COMMENT'插件名称',
	plugins_url VARCHAR(200) NOT NULL COMMENT'插件包路径',
	plugins_version VARCHAR(10) NOT NULL DEFAULT'0.0.0' COMMENT'插件版本',
	plugins_post_time DATETIME COMMENT'插件的发布时间',
	plugins_state TINYINT DEFAULT 0 COMMENT'插件状态，0：正常（默认），2：下线',
	plugins_remark TEXT COMMENT '插件版本内容，升级修复介绍',
	is_default BIT DEFAULT 1 COMMENT'是否为系统默认安装，1：系统默认安装，不可卸载；0：自定义安装，可卸载',
	path VARCHAR(100) COMMENT'插件路径，避免递归查询',
	parent_id INTEGER COMMENT'当前插件的主干id,即当前插件或者补丁是由那个插件版本做分支开发的'
)COMMENT'插件表，类似于系统的软件库';

#TODO 用户插件中间表，用于描述用户安装了那些插件
CREATE TABLE user_plugins(
	
)COMMENT'用户插件中间表，用于描述用户安装了那些插件';

#平台和用户的中间表
CREATE TABLE platform_user(
 platform_id INTEGER COMMENT'平台id',
 user_id INTEGER COMMENT '用户id',
 PRIMARY KEY(platform_id,user_id)
)COMMENT '平台和用户之间的账号表';

#构建用户表
CREATE TABLE  IF NOT EXISTS USER(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	login_name VARCHAR(20) NOT NULL COMMENT '登录名',
	PASSWORD VARCHAR(100) NOT NULL COMMENT '登录密码，MD5加密',
	NAME VARCHAR(30) DEFAULT '' COMMENT '用户名',
	last_login_time DATETIME COMMENT '上次登录时间',
	last_login_ip VARCHAR(40) COMMENT '上次登录ip',
	theme TINYINT COMMENT '主题，0：默认，1：经典灰色',
	state TINYINT DEFAULT 0 COMMENT '账号状态，0:正常（默认），1:锁定，2:禁用'
	)COMMENT '系统用户表';
	
#用户附加信息表
CREATE TABLE IF NOT EXISTS user_info(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  user_id INTEGER NOT NULL COMMENT '用户id',
  sex BIT DEFAULT 1 COMMENT '性别，0：女，1：男（默认）',
  head_url VARCHAR(40) COMMENT '头像url',
  address VARCHAR(100) COMMENT '地址',
  extra TEXT  COMMENT '其他扩展信息，json格式'
)COMMENT '用户详细信息';

#用户登录授权表，用于扩展第三方登录
CREATE TABLE IF NOT EXISTS  user_auth(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  user_id INTEGER NOT NULL COMMENT '用户id',
  platform_type TINYINT DEFAULT 0 COMMENT '用于用户登录授权的平台类型，-1：其他，0:当前系统（默认），1:qq，2:微信，3：qq邮箱',
  identify VARCHAR(200) NOT NULL COMMENT '用户登录标识，对应登录名，qq账号，手机号等',
  credential VARCHAR(200) NOT NULL COMMENT '用户登录凭证，对应密码'
)COMMENT '用户登录授权表，用于扩展第三方登录';

#用户和角色的中间表
CREATE TABLE user_role(
	user_id INTEGER COMMENT'用户id',
	role_id INTEGER COMMENT '角色id',
	PRIMARY KEY(user_id,role_id)
)COMMENT '用户和角色的中间表';

#角色表
CREATE TABLE IF NOT EXISTS role(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	platform_id INTEGER COMMENT'平台id,针对每个平台设置角色',
	NAME VARCHAR(30) NOT NULL COMMENT'角色名称',
	remark VARCHAR(50) COMMENT '备注'
)COMMENT '角色表';

#角色依赖表
CREATE TABLE role_depends(
	role_id INTEGER COMMENT'角色id',
	depend_id INTEGER COMMENT'当前角色依赖角色的id'
)COMMENT '角色依赖表';

#角色与权限的中间表
CREATE TABLE IF NOT EXISTS role_permission(
	role_id INTEGER NOT NULL COMMENT'角色id',
	permission_id INTEGER NOT NULL COMMENT'权限id',
	scope TINYINT NOT NULL DEFAULT 1 COMMENT'数据权限，0：不限制，1：当前系统（默认），2：当前部门及子部门，3：当前部门，4：个人',
	PRIMARY KEY(role_id,permission_id) COMMENT '角色和权限id共同作为主键'
)COMMENT '角色和权限的中间表';

#权限表
CREATE TABLE IF NOT EXISTS permission(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	parent_id INTEGER COMMENT '父级权限',
	modal_name VARCHAR(30) COMMENT '模块名称，用于页面展示',
	modal VARCHAR(30) COMMENT '模块名称',
	controller VARCHAR(30) COMMENT 'controller',
	method VARCHAR(30) COMMENT 'method',
	url VARCHAR(50) COMMENT '用于页面的url',
	remark VARCHAR(50) COMMENT'备注'
)COMMENT '权限表';


SHOW TABLES;
SHOW CREATE TABLE USER;
SHOW FULL FIELDS FROM USER;

SELECT TABLE_NAME, TABLE_COMMENT FROM information_schema.tables