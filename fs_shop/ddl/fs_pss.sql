DROP TABLE IF EXISTS `pss_goods_temp`;
CREATE TABLE `pss_goods_temp` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `fieldset` varchar(255) DEFAULT NULL COMMENT '自定义字段',
	`update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类型表';

DROP TABLE IF EXISTS `pss_goods`;
CREATE TABLE `pss_goods` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '名称',
	`code` varchar(100) NOT NULL COMMENT '标识码（如型号、条形码）',
	`type` int(11) NOT NULL COMMENT '类型',
	`temp_id` int(11) NOT NULL COMMENT '模板id',
  `brand` varchar(30) DEFAULT NULL COMMENT '品牌',
  `price` varchar(20) NULL DEFAULT '0' COMMENT '价格',
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

DROP TABLE IF EXISTS `pss_supplier_temp`;
CREATE TABLE `pss_supplier_temp` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `fieldset` varchar(255) DEFAULT NULL COMMENT '自定义字段',
	`update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商模板表';

DROP TABLE IF EXISTS `pss_supplier`;
CREATE TABLE `pss_supplier` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '名称',
	`code` varchar(100) NOT NULL COMMENT '标识码（如型号、条形码）',
	`type` int(11) DEFAULT NULL COMMENT '类型',
	`temp_id` int(11) DEFAULT NULL COMMENT '模板id',
  `contacts` varchar(20) DEFAULT NULL COMMENT '联系人',
  `contact_way` varchar(30) DEFAULT NULL COMMENT '联系方式',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
	`update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `fieldset` TEXT DEFAULT NULL COMMENT '自定义字段',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商表';

DROP TABLE IF EXISTS `pss_purchasing_plan`;
CREATE TABLE `pss_purchasing_plan` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `number` varchar(50) NOT NULL COMMENT '单号',
	`cycle` int(11) DEFAULT NULL COMMENT '周期',
	`type` int(11) DEFAULT NULL COMMENT '类型',
  `plan_date` datetime DEFAULT NULL COMMENT '计划日期',
	`status` tinyint(2) DEFAULT '0' COMMENT '状态',
	`create_by` int(11) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
	`update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购计划表';

DROP TABLE IF EXISTS `pss_purchasing_plan_detail`;
CREATE TABLE `pss_purchasing_plan_detail` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
	`parent_id` int(11) NOT NULL COMMENT '采购计划id',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `price` varchar(20) DEFAULT NULL COMMENT '金额',
	`count` int(11) DEFAULT NULL COMMENT '数量',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购计划详情表';

DROP TABLE IF EXISTS `pss_purchasing_order`;
CREATE TABLE `pss_purchasing_order` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `plan_id` int(11) DEFAULT NULL COMMENT '计划id',
	`number` varchar(50) NOT NULL COMMENT '单号',
	`type` tinyint(2) DEFAULT '0' COMMENT '类型',
	`supplier_id` int(11) DEFAULT NULL COMMENT '供应商id',
	`invoice_type` int(11) DEFAULT NULL COMMENT '发票类型',
	`business_id` int(11) DEFAULT NULL COMMENT '业务员',
  `order_date` varchar(20) DEFAULT NULL COMMENT '下单日期',
	`status` tinyint(2) DEFAULT '0' COMMENT '状态',
	`create_by` int(11) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
	`update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购订单表';

DROP TABLE IF EXISTS `pss_purchasing_order_detail`;
CREATE TABLE `pss_purchasing_order_detail` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
	`parent_id` int(11) NOT NULL COMMENT '采购订单id',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
	`count` int(11) NOT NULL COMMENT '数量',
  `price` varchar(20) NULL DEFAULT '0' COMMENT '价格',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购订单详情表';

DROP TABLE IF EXISTS `pss_purchasing_plan_order_mapping`;
CREATE TABLE `pss_purchasing_plan_order_mapping` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
	`plan_id` int(11) NOT NULL COMMENT '计划id',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购_计划_订单_关联表';

DROP TABLE IF EXISTS `pss_sales_order`;
CREATE TABLE `pss_sales_order` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
	`number` varchar(50) NOT NULL COMMENT '单号',
  `delivery_date` datetime DEFAULT NULL COMMENT '发货日期',
	`customer_id` int(11) DEFAULT NULL COMMENT '客户',
	`merchandiser_id` int(11) DEFAULT NULL COMMENT '跟单员',
	`pay_method` int(11) DEFAULT NULL COMMENT '付款方式',
  `transport_method` int(11) DEFAULT NULL COMMENT '运输方式',
  `delivery_address` varchar(100) DEFAULT NULL COMMENT '收货地址',
	`status` tinyint(2) DEFAULT '0' COMMENT '状态',
	`create_by` int(11) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
	`update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='销售订单表';

DROP TABLE IF EXISTS `pss_sales_order_detail`;
CREATE TABLE `pss_sales_order_detail` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
	`parent_id` int(11) NOT NULL COMMENT '销售订单id',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
	`count` int(11) NOT NULL COMMENT '数量',
  `price` varchar(20) NULL DEFAULT '0' COMMENT '价格',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='销售订单详情表';

DROP TABLE IF EXISTS `pss_store_house`;
CREATE TABLE `pss_store_house` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
	`name` varchar(20) NOT NULL COMMENT '名称',
	`storekeeper_id` int(11) DEFAULT NULL COMMENT '仓管员',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仓库表';

DROP TABLE IF EXISTS `pss_stock`;
CREATE TABLE `pss_stock` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
	`storehouse_id` int(11) NOT NULL COMMENT '仓库id',
	`goods_id` int(11) NOT NULL COMMENT '商品id',
  `count` int(11) DEFAULT NULL COMMENT '数量',
	`position` varchar(50) DEFAULT NULL COMMENT '仓位',
	`update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存表';

DROP TABLE IF EXISTS `pss_stock_log`;
CREATE TABLE `pss_stock_log` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
	`number` varchar(50) NOT NULL COMMENT '单号',
  `type` int(11) DEFAULT NULL COMMENT '类型',
	`business_date` datetime DEFAULT NULL COMMENT '业务日期',
	`storehouse_id` int(11) NOT NULL COMMENT '仓库id',
	`business_id` int(11) DEFAULT NULL COMMENT '业务员',
	`audit_id` int(11) DEFAULT NULL COMMENT '审核人',
	`status` tinyint(2) DEFAULT '0' COMMENT '状态',
	`create_by` int(11) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
	`update_by` int(11) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存日志表';

DROP TABLE IF EXISTS `pss_stock_log_detail`;
CREATE TABLE `pss_stock_log_detail` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
	`parent_id` int(11) NOT NULL COMMENT '库存日志id',
	`goods_id` int(11) NOT NULL COMMENT '商品id',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存日志详情表';


