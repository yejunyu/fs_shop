DROP TABLE IF EXISTS `shop_dict`;
CREATE TABLE `shop_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `type` varchar(50) NOT NULL COMMENT '类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

DROP TABLE IF EXISTS `shop_member`;
CREATE TABLE `shop_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `wx_id` varchar(100) DEFAULT NULL COMMENT '微信id',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登录名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
	`score` int(11) DEFAULT 0 COMMENT '积分',
  `contact_way` varchar(30) DEFAULT NULL COMMENT '联系方式',
  `status` tinyint(2) DEFAULT '0' COMMENT '状态',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='顾客基础信息表';

DROP TABLE IF EXISTS `shop_goods_temp`;
CREATE TABLE `shop_goods_temp` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `fieldset` varchar(255) DEFAULT NULL COMMENT '自定义字段',
	`update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类型表';

DROP TABLE IF EXISTS `shop_goods`;
CREATE TABLE `shop_goods` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '名称',
	`type` int(11) DEFAULT NULL COMMENT '类型',
	`temp_id` int(11) DEFAULT NULL COMMENT '模板id',
  `price` varchar(20) NULL DEFAULT '0' COMMENT '价格',
	`score` int(11) DEFAULT -1 COMMENT '积分',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `min` int(11) NULL DEFAULT '-1' COMMENT '最小值',
	`max` int(11) NULL DEFAULT '0' COMMENT '最大值',
	`update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `fieldset` TEXT DEFAULT NULL COMMENT '自定义字段',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

DROP TABLE IF EXISTS `shop_goods_img`;
CREATE TABLE `shop_goods_img` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
	`path` varchar(100) DEFAULT NULL COMMENT '路径',
  `bytes` MEDIUMBLOB DEFAULT NULL COMMENT '文件二进制存储',
	`first_show` char(1) NOT NULL DEFAULT '0' COMMENT '优先展示',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品图片表';

DROP TABLE IF EXISTS `shop_order`;
CREATE TABLE `shop_order` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
	`number` varchar(100) NOT NULL COMMENT '订单号',
	`table_no` varchar(30) DEFAULT NULL COMMENT '台号',
	`persons` int(11) DEFAULT NULL COMMENT '人数',
	`pay_method` int(11) DEFAULT NULL COMMENT '支付方式',
  `order_time` varchar(20) NOT NULL COMMENT '下单时间',
  `delivery_date` varchar(20) DEFAULT NULL COMMENT '配送日期',
	`status` tinyint(2) DEFAULT '0' COMMENT '状态',
  `total` varchar(20) DEFAULT '0' COMMENT '总计',
	`create_by` int(11) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

DROP TABLE IF EXISTS `shop_order_delivery`;
CREATE TABLE `shop_order_delivery` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
	`order_id` int(11) NOT NULL COMMENT '订单id',
  `delivery_time` varchar(30) DEFAULT NULL COMMENT '配送时间',
	`delivery_address` varchar(255) DEFAULT NULL COMMENT '配送地址',
	`customer` varchar(100) DEFAULT NULL COMMENT '收货人',
	`contact_way` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单-配送表';

DROP TABLE IF EXISTS `shop_order_detail`;
CREATE TABLE `shop_order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `count` int(11) NOT NULL COMMENT '数量',
  `price` varchar(20) DEFAULT '0' COMMENT '价格',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单-详情表';

DROP TABLE IF EXISTS `shop_report`;
CREATE TABLE `shop_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `count_date` varchar(30) NOT NULL COMMENT '统计日期',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品id',
  `type` tinyint(2) NOT NULL COMMENT '类型',
  `total` varchar(20) DEFAULT '0' COMMENT '总计',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报表表';

DROP TABLE IF EXISTS `shop_member_address`;
CREATE TABLE `shop_member_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
	`detail_address` varchar(50) DEFAULT NULL COMMENT '详细地址',
  `default_flag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否默认使用',
	`create_by` int(11) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='顾客收餐地址信息表';

