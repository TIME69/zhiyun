/*
SQLyog v10.2 
MySQL - 5.7.20 : Database - zhiyun_os
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zhiyun_os` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zhiyun_os`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` varchar(100) NOT NULL,
  `title` varchar(30) DEFAULT NULL COMMENT '文章标题，不大于30字符',
  `_abstract` varchar(50) DEFAULT NULL COMMENT '文章标题，不大于50字符',
  `content` text COMMENT '文章内容',
  `create_time` datetime NOT NULL COMMENT '文章创建日期',
  `last_edit_time` datetime DEFAULT NULL COMMENT '文章最近一次修改时间',
  `view_count` int(11) DEFAULT '0' COMMENT '文章被访问的次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表，记录文章的信息';

/*Data for the table `article` */

insert  into `article`(`id`,`title`,`_abstract`,`content`,`create_time`,`last_edit_time`,`view_count`) values ('ca8a390c-37a4-497d-b085-fdc2d420d461','Jenkins入门系列之—PDF文档下载','Jenkins是一个开源软件项目，旨在提供一个开放易用的软件平台，使软件的持续集成变成可能。','哈哈哈哈哈','2017-11-27 16:57:47','2017-11-27 16:57:49',100);

/*Table structure for table `attachment` */

DROP TABLE IF EXISTS `attachment`;

CREATE TABLE `attachment` (
  `id` varchar(100) NOT NULL,
  `state` smallint(6) NOT NULL DEFAULT '0' COMMENT '文件状态，0：正常，1：待审核，2：审核不通过，3：删除',
  `type` smallint(6) NOT NULL COMMENT '文件类型，0:文本，1:音频，2:视频',
  `path` varchar(300) NOT NULL COMMENT '文件的存储路径',
  `remark` varchar(30) DEFAULT NULL COMMENT '文件的说明',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '文件的创建时间',
  `create_user` varchar(100) NOT NULL DEFAULT '0' COMMENT '文件创建人，0代表当前系统管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统的文件表';

/*Data for the table `attachment` */

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` varchar(14) NOT NULL COMMENT '系统权限id,初始化为14位，组成为：AAA-BBB-CCC-DD，其中AAA为子系统标识，BBB为模块标识，CCC为子模块标识，',
  `parent_id` varchar(14) DEFAULT NULL COMMENT '父级权限',
  `nav_menu` bit(1) DEFAULT b'0' COMMENT '是否是菜单，0：否，1：是',
  `modal_name` varchar(30) DEFAULT NULL COMMENT '模块名称，用于页面展示',
  `button_name` varchar(30) DEFAULT NULL,
  `url` text COMMENT '权限访问url',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表,控制所有的访问链接\r\n';

/*Data for the table `permission` */

insert  into `permission`(`id`,`parent_id`,`nav_menu`,`modal_name`,`button_name`,`url`,`remark`) values ('000-000-000-00','0','\0','智云',NULL,'/','智云所有系统的根访问路径'),('001-000-000-00','000-000-000-00','\0','智云学习平台前台系统',NULL,'/learning-web','智云子系统-学习平台前台系统根访问路径'),('002-000-000-00','000-000-000-00','\0','智云学习平台后台系统',NULL,'/learning-console','智云子系统-学习平台后台台系统根访问路径'),('002-001-000-00','001-000-000-00','','首页',NULL,'/learning-web/','学习平台->首页'),('002-002-000-00','001-000-000-00','','工具',NULL,'/learning-web/tools','学习平台->工具'),('002-003-000-00','001-000-000-00','','下载中心',NULL,'/learning-web/download','学习平台->下载中心'),('002-004-000-00','001-000-000-00','','前端',NULL,'/learning-web/frontkit','学习平台->前端');

/*Table structure for table `permission_depends` */

DROP TABLE IF EXISTS `permission_depends`;

CREATE TABLE `permission_depends` (
  `permission_id` int(11) NOT NULL COMMENT '权限id，键permission表',
  `depends_id` int(11) NOT NULL COMMENT '依赖id',
  PRIMARY KEY (`permission_id`,`depends_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限依赖关系表';

/*Data for the table `permission_depends` */

/*Table structure for table `platform` */

DROP TABLE IF EXISTS `platform`;

CREATE TABLE `platform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) NOT NULL DEFAULT '' COMMENT '平台名称',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '平台状态，0：正常（默认），1：维护中，2：停用',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `path` varchar(200) DEFAULT NULL COMMENT '平台层级关系路径，以parentId.id标识，避免递归查询',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级平台ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='平台类型表';

/*Data for the table `platform` */

insert  into `platform`(`id`,`NAME`,`state`,`remark`,`path`,`parent_id`) values (1,'智云',0,'智云系统',NULL,0),(2,'智云学习平台前台系统',0,'智云系统学习共享平台前台系统','1.2',1),(3,'智云学习平台后台系统',0,'智云系统学习共享平台后台系统','1.3',1);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `platform_id` int(11) DEFAULT '0',
  `NAME` varchar(30) NOT NULL COMMENT '角色名称',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `role` */

insert  into `role`(`id`,`platform_id`,`NAME`,`remark`) values (1,0,'系统管理员',NULL),(2,2,'系统管理员','智云学习平台管理员'),(3,2,'文章审核','智云学习平台的文章审核管理员');

/*Table structure for table `role_depends` */

DROP TABLE IF EXISTS `role_depends`;

CREATE TABLE `role_depends` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `depend_id` int(11) NOT NULL COMMENT '当前角色依赖角色的id',
  PRIMARY KEY (`role_id`,`depend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色依赖表';

/*Data for the table `role_depends` */

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  `scope` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据权限，0：不限制，1：当前系统（默认），2：当前部门及子部门，3：当前部门，4：个人',
  PRIMARY KEY (`role_id`,`permission_id`) COMMENT '角色和权限id共同作为主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和权限的中间表';

/*Data for the table `role_permission` */

insert  into `role_permission`(`role_id`,`permission_id`,`scope`) values (1,1,1),(1,2,1),(1,3,1);

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `conf_key` varchar(30) DEFAULT NULL COMMENT '配置键值',
  `conf_value` varchar(30) DEFAULT NULL COMMENT '配置属性值',
  `remark` varchar(100) DEFAULT NULL COMMENT '配置项说明'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置项';

/*Data for the table `sys_config` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(100) NOT NULL COMMENT 'UUID生成',
  `NAME` varchar(30) DEFAULT '' COMMENT '用户名',
  `state` tinyint(4) DEFAULT '0' COMMENT '账号状态，0:正常（默认），1:锁定，2:禁用',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `last_login_ip` varchar(40) DEFAULT NULL COMMENT '上次登录ip',
  `login_type` smallint(6) DEFAULT '0' COMMENT '登录类型，0：前台，1：后台，2：http接口,3:sdk接口，4：webservice接口，9：其他',
  `trust_ips` text COMMENT '信任IP列表，ip之间使用英文逗号分隔',
  `platform_ids` varchar(100) DEFAULT '' COMMENT '用户可登录系统id列表，id之间以英文逗号分隔',
  `theme` tinyint(4) DEFAULT NULL COMMENT '主题，0：默认，1：经典灰色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

/*Data for the table `user` */

insert  into `user`(`id`,`NAME`,`state`,`last_login_time`,`last_login_ip`,`login_type`,`trust_ips`,`platform_ids`,`theme`) values ('4f98966b-f7a9-401c-b327-aa0e401d47b4','张雷立',0,'2017-12-03 15:24:11',NULL,0,NULL,'1',NULL),('5fac3ddd-e01e-49fe-a354-6b23032072cc','root',0,'2017-11-17 17:44:20',NULL,0,NULL,'1',NULL);

/*Table structure for table `user_article` */

DROP TABLE IF EXISTS `user_article`;

CREATE TABLE `user_article` (
  `user_id` varchar(100) DEFAULT NULL,
  `article_id` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和文章的中间表';

/*Data for the table `user_article` */

insert  into `user_article`(`user_id`,`article_id`) values ('1','1');

/*Table structure for table `user_auth` */

DROP TABLE IF EXISTS `user_auth`;

CREATE TABLE `user_auth` (
  `id` varchar(200) NOT NULL COMMENT 'UUID生成',
  `identify_type` smallint(6) DEFAULT NULL COMMENT '登陆账号第三方类型，0：系统，1：qq,2:webchat,3:手机,4:邮箱,9:其他',
  `user_id` varchar(200) NOT NULL COMMENT '用户id，uuid生成',
  `platform_id` int(4) DEFAULT NULL COMMENT '用户登录账号的所属平台id,参见platform表',
  `identify` varchar(200) NOT NULL COMMENT '用户登录标识，对应登录名，qq账号，手机号等',
  `credential` varchar(200) NOT NULL COMMENT '用户登录凭证，对应密码',
  PRIMARY KEY (`id`),
  KEY `auth_user_foreign_key` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录授权表，用于扩展第三方登录';

/*Data for the table `user_auth` */

insert  into `user_auth`(`id`,`identify_type`,`user_id`,`platform_id`,`identify`,`credential`) values ('1',0,'4f98966b-f7a9-401c-b327-aa0e401d47b4',2,'admin','123456'),('2',4,'4f98966b-f7a9-401c-b327-aa0e401d47b4',2,'1348555156@qq.com','123456');

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `sex` varchar(4) DEFAULT '' COMMENT '性别',
  `head_url` varchar(40) DEFAULT NULL COMMENT '头像url',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `extra` text COMMENT '其他扩展信息，json格式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户详细信息';

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`user_id`,`sex`,`head_url`,`address`,`extra`) values (1,1,'',NULL,NULL,NULL);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_id` varchar(100) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色的中间表';

/*Data for the table `user_role` */

insert  into `user_role`(`user_id`,`role_id`) values ('4f98966b-f7a9-401c-b327-aa0e401d47b4',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
