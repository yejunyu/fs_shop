DROP TABLE IF EXISTS `u_dict`;
CREATE TABLE `u_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `type` varchar(50) NOT NULL COMMENT '类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='字典表';

DROP TABLE IF EXISTS `u_auth`;
CREATE TABLE `u_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='权限表';

DROP TABLE IF EXISTS `u_office`;
CREATE TABLE `u_office` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` int(11) NOT NULL COMMENT '父级id',
  `name` varchar(30) NOT NULL COMMENT '名称',
  `master_id` int(11) DEFAULT NULL COMMENT '负责人id',
  `update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='机构表';

DROP TABLE IF EXISTS `u_office_user`;
CREATE TABLE `u_office_user` (
  `office_id` int(11) NOT NULL COMMENT '机构id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`office_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构-用户表';

DROP TABLE IF EXISTS `u_role`;
CREATE TABLE `u_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `ename` varchar(50) NOT NULL COMMENT '英文名称',
  `update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='角色表';

DROP TABLE IF EXISTS `u_role_auth`;
CREATE TABLE `u_role_auth` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `auth_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`role_id`,`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-权限表';

DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `code` varchar(100) DEFAULT NULL COMMENT '编码（工号）',
  `login_name` varchar(64) NOT NULL COMMENT '登录名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `contact_way` varchar(30) DEFAULT NULL COMMENT '联系方式',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(2) DEFAULT '0' COMMENT '状态',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='用户基础信息表';

DROP TABLE IF EXISTS `u_user_role`;
CREATE TABLE `u_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色表';












