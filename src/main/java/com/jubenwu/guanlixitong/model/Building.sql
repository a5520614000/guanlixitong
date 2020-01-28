-- auto Generated on 2020-01-28
-- DROP TABLE IF EXISTS building;
CREATE TABLE building_management.building(
	id INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id 自动',
	`name` VARCHAR (50) NOT NULL COMMENT '姓名',
	card_id INT (11) NOT NULL COMMENT '身份证号',
	building_address VARCHAR (50) NOT NULL COMMENT '建筑地址',
	`floor` INT (11) NOT NULL COMMENT '层数',
	`use` VARCHAR (50) NOT NULL COMMENT '用途',
	`others` VARCHAR (50) DEFAULT '' COMMENT '备注',
	gmt_create VARCHAR (50) NOT NULL COMMENT '以下由后端处理
     * 创建时间',
	gmt_modified VARCHAR (50) NOT NULL COMMENT '以下由后端处理
     * 最后修改时间',
	parent_id INT (11) NOT NULL COMMENT '以下由后端处理
     * 所属表id',
	locker INT (11) NOT NULL DEFAULT 1 COMMENT '以下由后端处理
     * 乐观锁',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'building';
