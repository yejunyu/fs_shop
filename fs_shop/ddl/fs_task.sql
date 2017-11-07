DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `describe` varchar(255) DEFAULT NULL COMMENT '描述',
  `update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目表';

DROP TABLE IF EXISTS `task_temp`;
CREATE TABLE `task_temp` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `fieldset` varchar(255) DEFAULT NULL COMMENT '自定义字段',
  `update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务模板表';

DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `parent_id` int(11) DEFAULT NULL COMMENT '父id',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `describe` varchar(255) NOT NULL COMMENT '描述',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `priority` int(11) DEFAULT NULL COMMENT '优先级',
  `jump_data` varchar(255) DEFAULT NULL COMMENT '跳转数据',
  `tags` varchar(255) DEFAULT NULL COMMENT '标签',
  `users` varchar(255) DEFAULT NULL COMMENT '指派成员',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `temp_id` int(11) DEFAULT NULL COMMENT '模板id',
  `fieldset` TEXT DEFAULT NULL COMMENT '自定义字段',
  `create_by` int(11) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';