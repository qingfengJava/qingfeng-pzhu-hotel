/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : pzhu-hotel

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 11/12/2021 12:43:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dinner_table
-- ----------------------------
DROP TABLE IF EXISTS `t_dinner_table`;
CREATE TABLE `t_dinner_table`  (
  `table_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '餐桌ID',
  `table_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '餐桌名称',
  `table_status` tinyint(0) NOT NULL COMMENT '状态(是否预定) 0:否 1:是',
  `reservation_time` datetime(0) NULL DEFAULT NULL COMMENT '预定时间',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '餐桌表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dinner_table
-- ----------------------------
INSERT INTO `t_dinner_table` VALUES (1, '高档餐桌1号', 0, NULL);
INSERT INTO `t_dinner_table` VALUES (2, '中档餐桌1号', 0, NULL);
INSERT INTO `t_dinner_table` VALUES (3, '低档餐桌1号', 0, NULL);
INSERT INTO `t_dinner_table` VALUES (4, '低档餐桌2号', 0, NULL);
INSERT INTO `t_dinner_table` VALUES (5, '高档餐桌2号', 0, NULL);
INSERT INTO `t_dinner_table` VALUES (6, '抵挡餐桌3号', 0, NULL);
INSERT INTO `t_dinner_table` VALUES (7, '中档餐桌2号', 0, NULL);
INSERT INTO `t_dinner_table` VALUES (8, '高档餐桌3号', 0, NULL);
INSERT INTO `t_dinner_table` VALUES (9, '中档餐桌3号', 0, NULL);

-- ----------------------------
-- Table structure for t_food
-- ----------------------------
DROP TABLE IF EXISTS `t_food`;
CREATE TABLE `t_food`  (
  `food_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '菜品ID',
  `type_id` int(0) NULL DEFAULT NULL COMMENT '菜系ID  作为一个外键',
  `food_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品名称',
  `food_price` decimal(8, 2) NULL DEFAULT NULL COMMENT '价格',
  `food_mprice` decimal(8, 2) NULL DEFAULT NULL COMMENT '会员价格',
  `food_image` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `food_desc` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '简介',
  PRIMARY KEY (`food_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_food
-- ----------------------------
INSERT INTO `t_food` VALUES (3, 1, '爆炒牛肚', 88.00, 80.00, '/files/images/886255880745442ca225ecaa5ede52ca.jpg', '爆炒牛肚爆炒牛肚爆炒牛肚');
INSERT INTO `t_food` VALUES (7, 6, '火腿白菜', 20.00, 16.00, '/files/images/7e5182007a134eca8be9906ddc3cb9de.jpg', '火腿白菜火腿白菜火腿白菜');
INSERT INTO `t_food` VALUES (8, 1, '东坡肘子', 88.00, 80.00, '/files/images/e1dbff3e18414ea0aad4172f0a624680.jpg', '四川人最爱，东坡肘子，yyds！！！');
INSERT INTO `t_food` VALUES (11, 15, '北京烤鸭', 108.00, 105.00, '/files/images/1e620b39fe414edbafb0b1eb68536b2a.jpg', '北京人的最爱');
INSERT INTO `t_food` VALUES (12, 1, '爆炒龙虾', 190.00, 180.00, '/files/images/c37b3a2888f047cd8811dcf49dc36375.jpg', '四川人的最爱！');
INSERT INTO `t_food` VALUES (16, 6, '荷兰豆', 25.00, 23.00, '/files/images/9f0029428fbd44bb9cb4113bcf88fa75.jpg', '荷兰豆，养生！！！');
INSERT INTO `t_food` VALUES (17, 6, '西湖醋鱼', 67.00, 65.00, '/files/images/f223f75d1db64ec98c1345f2ad6f6af0.jpg', '西湖醋鱼，真难吃！！！');
INSERT INTO `t_food` VALUES (21, 18, '青椒鸡丁', 28.00, 25.00, '/files/images/27a12516aea24d26b11d1325619b83ff.jpg', '又香又辣的青椒抄鸡丁！！！');
INSERT INTO `t_food` VALUES (22, 5, '水煮白菜', 20.00, 18.00, '/files/images/a406095fd3614aafafc2bcf09a06110d.jpg', '水煮白菜清香味美！！！');
INSERT INTO `t_food` VALUES (23, 15, '卤鸭肉', 50.00, 47.00, '/files/images/c24c5cdc764441088fe27f1a69aded53.jpg', '肥而不腻！');
INSERT INTO `t_food` VALUES (24, 1, '肉圆子', 40.00, 37.00, '/files/images/41dbc428439b4dd2b555d8a2f2f4fb1a.jpg', '一口一个，别提多爽了！！！');
INSERT INTO `t_food` VALUES (25, 1, '海鲜大咖', 190.00, 188.00, '/files/images/c4d9dc6efc634c4b9838dd4c3d4825cc.jpg', '满满的海鲜，包你吃到爽！！！');
INSERT INTO `t_food` VALUES (26, 1, '小炒肉', 24.00, 23.00, '/files/images/31c7343ea6774a7992d91e5f818ea28e.jpg', '小炒肉，香而不腻！！！');
INSERT INTO `t_food` VALUES (27, 5, '烘蛋', 12.00, 11.00, '/files/images/abe507c9fbf942b79e8432a2face2bba.jpg', '看着就有食欲！！！');
INSERT INTO `t_food` VALUES (28, 1, '清蒸鱼', 44.00, 43.00, '/files/images/0d8bf3eccc514ca19c10a5104a7dc3dc.jpg', '鲜嫩清香！！！');
INSERT INTO `t_food` VALUES (29, 1, '水煮虾', 30.00, 28.00, '/files/images/8f6fac8a7ef04302bcd18c3cf14b25c0.jpg', '保证原生原味，才是正宗！！！');
INSERT INTO `t_food` VALUES (30, 1, '酸菜鱼', 53.00, 51.00, '/files/images/71f776c5e59642e6ad521577a9816342.jpg', '清香味儿美，汤都可以喝哟！！！');
INSERT INTO `t_food` VALUES (31, 5, '粉丝生蚝', 79.00, 70.00, '/files/images/4247f27d81d0492da3ba339b532e95b3.jpg', '保证让你满意！！！');
INSERT INTO `t_food` VALUES (32, 5, '酥排骨', 35.00, 33.00, '/files/images/247b754541ff49e79da7863b6a0f596e.jpg', '外焦里嫩，这不是最爱吗！！！');
INSERT INTO `t_food` VALUES (33, 5, '大坨肉', 40.00, 38.00, '/files/images/0a8793d5742848a8863437e520e8e80e.jpg', '肥而不腻，味道可口，还不来一道！！！');
INSERT INTO `t_food` VALUES (34, 5, '美味甜品', 20.00, 18.00, '/files/images/0155aa949e3c4566b50e2803773aae9e.jpg', '饭前一甜品，快活似神仙！！！');
INSERT INTO `t_food` VALUES (35, 6, '干煸脆骨', 50.00, 48.00, '/files/images/0b8f944d80174698a6d3bb898f6a8bdf.jpg', '麻辣香，一口一个正好！');
INSERT INTO `t_food` VALUES (36, 6, '冷锅串串儿', 45.00, 43.00, '/files/images/8326998255f847c6a4e06d6f809ef948.jpg', '这就不用多解释了吧，直接点就完事儿！！！');
INSERT INTO `t_food` VALUES (37, 15, '鲜辣小龙虾', 69.00, 67.00, '/files/images/86fb4438fb93416fa3e751b9edae987e.jpg', '全是小龙虾的香味儿！！！');
INSERT INTO `t_food` VALUES (38, 18, '小盘肉', 45.00, 43.00, '/files/images/f917ea02add14d60ada2501f8194a4bc.jpg', '看着就不错，吃起来就更不错了！！！');
INSERT INTO `t_food` VALUES (44, 15, '大鲍鱼', 200.00, 190.00, '/files/images/87bf5912a57445b7bcf869b4010a6235.jpg', '这菜就不用介绍了吧！！！');

-- ----------------------------
-- Table structure for t_food_type
-- ----------------------------
DROP TABLE IF EXISTS `t_food_type`;
CREATE TABLE `t_food_type`  (
  `type_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '菜系ID',
  `type_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜系名称',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_food_type
-- ----------------------------
INSERT INTO `t_food_type` VALUES (1, '川菜');
INSERT INTO `t_food_type` VALUES (5, '东北菜');
INSERT INTO `t_food_type` VALUES (6, '粤菜');
INSERT INTO `t_food_type` VALUES (15, '北京菜');
INSERT INTO `t_food_type` VALUES (18, '云南菜');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `menu_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `menu_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单URL',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '这是一个菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '菜系管理', '/foodtype?method=search');
INSERT INTO `t_menu` VALUES (2, '餐桌管理', '/dinnertable?method=search');
INSERT INTO `t_menu` VALUES (5, '菜品管理', '/food?method=search');
INSERT INTO `t_menu` VALUES (6, '订单管理', '/order?method=search');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `order_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单ID',
  `table_id` int(0) NULL DEFAULT NULL COMMENT '餐桌ID',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '主键',
  `total_num` int(0) NOT NULL COMMENT '总数量',
  `order_total_price` decimal(20, 2) NOT NULL COMMENT '订单总金额',
  `order_create_time` datetime(0) NOT NULL COMMENT '下单时间',
  `order_status` tinyint(0) NOT NULL COMMENT '状态',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1639127127698', 3, 2, 2, 96.00, '2021-12-10 17:05:27', 1);
INSERT INTO `t_order` VALUES ('1639127212512', 4, 2, 2, 105.00, '2021-12-10 17:06:52', 1);
INSERT INTO `t_order` VALUES ('1639128484818', 3, 2, 1, 80.00, '2021-12-10 17:28:04', 1);
INSERT INTO `t_order` VALUES ('1639145591289', 3, 2, 1, 18.00, '2021-12-10 22:13:11', 1);

-- ----------------------------
-- Table structure for t_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_order_detail`;
CREATE TABLE `t_order_detail`  (
  `order_detail_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '详情ID',
  `order_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单ID',
  `food_id` int(0) NULL DEFAULT NULL COMMENT '菜品ID',
  `num` int(0) NULL DEFAULT NULL COMMENT '菜数量',
  `food_total_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '小计',
  `order_detail_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `order_detail_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_detail_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单详情实体' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_detail
-- ----------------------------
INSERT INTO `t_order_detail` VALUES ('1639127127815', '1639127127698', 3, 1, 80.00, '2021-12-10 17:05:27', '2021-12-10 17:05:27');
INSERT INTO `t_order_detail` VALUES ('1639127127916', '1639127127698', 7, 1, 16.00, '2021-12-10 17:05:27', '2021-12-10 17:05:27');
INSERT INTO `t_order_detail` VALUES ('1639127212972', '1639127212512', 21, 1, 25.00, '2021-12-10 17:06:52', '2021-12-10 17:06:52');
INSERT INTO `t_order_detail` VALUES ('1639127213088', '1639127212512', 3, 1, 80.00, '2021-12-10 17:06:53', '2021-12-10 17:06:53');
INSERT INTO `t_order_detail` VALUES ('1639128484944', '1639128484818', 3, 1, 80.00, '2021-12-10 17:28:04', '2021-12-10 17:28:04');
INSERT INTO `t_order_detail` VALUES ('1639145591525', '1639145591289', 22, 1, 18.00, '2021-12-10 22:13:11', '2021-12-10 22:13:11');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `is_admin` tinyint(0) NOT NULL COMMENT '是否管理员 0:否  1:是',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `gender` tinyint(0) NOT NULL COMMENT '性别 0:保密 1:男 2:女',
  `user_status` tinyint(0) NOT NULL COMMENT '状态(是否激活) 0:否 1:是',
  `user_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `user_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(0) NOT NULL COMMENT '是否删除  0:否  1:是',
  `is_member` tinyint(0) NOT NULL COMMENT '是否会员 0:否 1:是',
  `balance` decimal(20, 2) NULL DEFAULT NULL COMMENT '账户余额',
  `loginNum` int(0) NULL DEFAULT NULL COMMENT '成功登录的次数',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '这是一个用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', 1, '15387637024', 0, 1, '2021-11-19 16:03:24', '2021-11-19 16:03:48', 0, 0, 9000.00, 3);
INSERT INTO `t_user` VALUES (2, 'qingfeng', 'e10adc3949ba59abbe56e057f20f883e', '清风', 0, '13923876543', 1, 1, '2021-11-19 16:05:01', '2021-11-19 16:05:04', 0, 1, 119701.00, 33);
INSERT INTO `t_user` VALUES (3, 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '张三', 0, '13583597041', 1, 1, '2021-12-06 14:29:42', '2021-12-06 14:29:42', 0, 0, 2000.00, NULL);
INSERT INTO `t_user` VALUES (4, 'meimei', '25f9e794323b453885f5181f1b624d0b', '美美', 0, '13583697042', 0, 1, '2021-12-06 14:33:31', '2021-12-06 14:33:31', 0, 0, 5000.00, NULL);

SET FOREIGN_KEY_CHECKS = 1;
