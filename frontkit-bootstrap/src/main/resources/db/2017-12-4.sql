/*更新用户的状态类型，初始化为未激活状态*/
ALTER TABLE USER MODIFY state TINYINT DEFAULT 0 COMMENT'账号状态，0：未激活（默认），1：正常，2：锁定，3：禁用';
/*权限id改为字符串*/
ALTER TABLE role_permission MODIFY permission_id VARCHAR(14) COMMENT'权限id';

/*权限依赖表更新*/
DROP TABLE permission_depends;
CREATE TABLE permission_depends(
	permission_id VARCHAR(14),
	depend_id VARCHAR(14),
	PRIMARY KEY(permission_id,depend_id)
)COMMENT'权限依赖关系表';

INSERT INTO role_permission(role_id,permission_id) SELECT 1,id FROM permission;
UPDATE role_permission SET scope=0 WHERE role_id=1;
INSERT INTO role_permission(role_id,permission_id) SELECT 2,id FROM permission WHERE id LIKE '002%';

/*前后台权限url更换*/
UPDATE permission SET url='/learning-console' WHERE id='001-000-000-00';
UPDATE permission SET url='/learning-web' WHERE id='002-000-000-00';

/*主键修改*/
ALTER TABLE role_permission DROP PRIMARY KEY, ADD PRIMARY KEY (role_id, permission_id, scope); 

/*增加用户文章中间表的主键*/
ALTER TABLE user_article ADD PRIMARY KEY(user_id,article_id);

/*增加部门层级关系*/
ALTER TABLE USER ADD COLUMN TYPE TINYINT DEFAULT 0 COMMENT'账号类型，0：个人(默认)，1：部门，2：企业';
ALTER TABLE USER ADD COLUMN parent_id VARCHAR(100) COMMENT '上级账号id';
ALTER TABLE USER ADD COLUMN path TEXT COMMENT'账号层级关系路径，中间以英文点分隔，减少递归';

SELECT SUBSTRING('/learning-web/download',LENGTH("/learning-web/")+1);

UPDATE permission SET url= CONCAT("/learning-console",SUBSTRING(url,LENGTH("/learning-web/")+1)) WHERE url LIKE '/learning-web/%';