-- 复合主键
DROP TABLE IF EXISTS `sample_order`;
CREATE TABLE `sample_order` (
  `id` int(32) NOT NULL DEFAULT '0' COMMENT 'id',
  `order_no` varchar(32) NOT NULL DEFAULT '' COMMENT '订单号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `gmt_create` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`,`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- 单主键
DROP TABLE IF EXISTS `sample_user`;
CREATE TABLE `sample_user` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `age` varchar(20) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 无主键
DROP TABLE IF EXISTS `sample_log`;
CREATE TABLE `sample_log`  (
  `id` bigint(0) NULL COMMENT '主键',
  `create_time` timestamp(0) NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL COMMENT '更新时间',
  `remark` varchar(255) NULL COMMENT '备注'
) COMMENT = '日志表';