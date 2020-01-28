
-- auto Generated on 2020-01-28
-- DROP TABLE IF EXISTS building_form;
CREATE TABLE building_management.building_form(
	id INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
	user_id INT (11) NOT NULL COMMENT '用户ID',
	child_form_id VARCHAR (50) NOT NULL COMMENT '每一条建筑信息的集合，用,来分割',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'building_form';
-- auto Generated on 2020-01-28
-- DROP TABLE IF EXISTS building_form;
CREATE TABLE building_management.building_form(
	id INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
	user_id INT (11) NOT NULL COMMENT '用户ID',
	child_form_id VARCHAR (50) NOT NULL COMMENT '每一条建筑信息的集合，用,来分割',
	gmt_create VARCHAR (50) NOT NULL COMMENT '创建时间',
	gmt_modified VARCHAR (50) NOT NULL COMMENT '修改时间',
	locker INT (11) NOT NULL DEFAULT -1 COMMENT '乐观锁',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'building_form';
