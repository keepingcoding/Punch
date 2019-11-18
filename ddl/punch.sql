/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : punch

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 18/11/2019 11:22:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `config_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置名称',
  `config_value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置的值',
  `status` tinyint(2) NOT NULL COMMENT '是否启用',
  `is_del` tinyint(2) NOT NULL COMMENT '是否删除',
  `created_id` int(11) NOT NULL,
  `created_time` bigint(20) NOT NULL,
  `updated_id` int(11) NULL DEFAULT NULL,
  `updated_time` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, 'ON_WORK_TIME', '08:30', 1, 0, 1, 1574047182000, NULL, NULL);
INSERT INTO `sys_config` VALUES (2, 'OFF_WORK_TIME', '17:30', 1, 0, 1, 1574047182000, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL,
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户真实姓名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '用户状态',
  `is_del` tinyint(2) NULL DEFAULT NULL COMMENT '是否删除',
  `created_id` int(11) NULL DEFAULT NULL,
  `created_time` bigint(20) NULL DEFAULT NULL,
  `updated_id` int(11) NULL DEFAULT NULL,
  `updated_time` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'root', '超级管理员', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, 1, 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_punch_record
-- ----------------------------
DROP TABLE IF EXISTS `t_punch_record`;
CREATE TABLE `t_punch_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `punch_day` date NOT NULL COMMENT '打卡日期',
  `punch_on_time` datetime(0) NULL DEFAULT NULL COMMENT '上班打卡时间',
  `punch_off_time` datetime(0) NULL DEFAULT NULL COMMENT '下班打卡时间',
  `punch_status` tinyint(2) NULL DEFAULT NULL COMMENT '打卡状态: 0 迟到 1 早退',
  `punch_on_addr` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '打卡地点',
  `punch_off_addr` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '打卡地点',
  `on_remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `off_remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `created_id` int(11) NOT NULL,
  `created_time` bigint(20) NOT NULL,
  `updated_id` int(11) NULL DEFAULT NULL,
  `updated_time` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_punch_record
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
