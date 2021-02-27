--订单表
CREATE TABLE `t_order` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_no` varbinary(64) NOT NULL COMMENT '订单号',
  `product_id` bigint(11) NOT NULL COMMENT '商品id',
  `product_name` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '商品名称',
  `product_image` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '商品图片',
  `quantity` int(11) NOT NULL COMMENT '购买数量',
  `origin_price` int(11) NOT NULL COMMENT '原始单价，单位：分',
  `buy_price` int(11) NOT NULL COMMENT '购买单价，单位：分',
  `payment_time` datetime NOT NULL COMMENT '付款时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receiver_time` datetime DEFAULT NULL COMMENT '收货时间',
  `user_id` bigint(11) NOT NULL COMMENT '用户id',
  `status` tinyint(1) NOT NULL COMMENT '状态：0、代发货 1、已发货 2、已收货 20、换货中 21、换货成功 40、退货中 41、已退货',
  `deleted` tinyint(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`id`),
  KEY `USER_ID_INDEX` (`user_id`),
  KEY `USER_PRODUCT_NAME_INDEX` (`user_id`,`product_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin

--收货信息表
CREATE TABLE `t_order_recipient` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_id` bigint(11) NOT NULL COMMENT '订单id',
  `area_no` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '区域编号',
  `name` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '收件人名称',
  `mobile` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '手机号',
  `type` tinyint(1) NOT NULL COMMENT '快递方式',
  `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `address` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '详细地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `ORDER_ID` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin

--商品表
CREATE TABLE `t_product` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `shop_id` bigint(11) NOT NULL COMMENT '商品id',
  `name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '商品名称',
  `description` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '商品描述',
  `price` int(11) NOT NULL COMMENT '商品价格',
  `score` int(11) NOT NULL COMMENT '商品评分',
  `month_transaction` int(11) NOT NULL COMMENT '月交易数',
  `total_transaction` int(11) NOT NULL COMMENT '总交易数',
  `commnet_num` int(11) NOT NULL COMMENT '评论数',
  `quantity` int(11) NOT NULL COMMENT '库存',
  `image` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '图片',
  `status` tinyint(1) NOT NULL COMMENT '0:禁用 1:开启',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL COMMENT '是否删除 0:否 1:是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin


--店铺表
CREATE TABLE `t_shop` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `name` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '店铺名称',
  `total sales` bigint(11) NOT NULL COMMENT '总销量',
  `total_score` int(5) NOT NULL COMMENT '店铺总评分',
  `service_score` int(5) NOT NULL COMMENT '服务评分',
  `logistics_score` int(5) NOT NULL COMMENT '物流评分',
  `fans_num` int(11) NOT NULL COMMENT '粉丝数',
  `grade` tinyint(1) NOT NULL COMMENT '店铺等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin

--交易表
CREATE TABLE `t_transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_id` bigint(11) NOT NULL COMMENT '订单编号',
  `order_name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '订单商品名',
  `order_description` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '订单商品描述',
  `order_remarkes` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单备注',
  `price` int(11) NOT NULL COMMENT '支付金额，单位：分',
  `status` tinyint(1) NOT NULL COMMENT '订单状态',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `extension_id` int(11) NOT NULL COMMENT '成功支付的交易拓展编号',
  `create_time` datetime NOT NULL COMMENT '退款总金额',
  `update_time` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `ORDER_ID_INDEX` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin


--用户表

CREATE TABLE `t_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(11) NOT NULL COMMENT '用户id',
  `name` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `phone_number` int(11) NOT NULL COMMENT '手机号',
  `address` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '地址',
  `status` tinyint(1) NOT NULL COMMENT '0:禁用 1:开启',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL COMMENT '是否删除 0:否 1:是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `USER_ID_UNIQUE_INDEX` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin


