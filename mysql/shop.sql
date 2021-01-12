/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 12/01/2021 20:27:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) NOT NULL,
  `rid` int(0) NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 0, 'sheng', 'cs044215', '17345059739', '3312444574@qq.com');
INSERT INTO `admin` VALUES (500, 0, 'admin', '123456', '123', '123@qq.com');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `describe` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('普通会员', '拥有该管理系统最高权限');
INSERT INTO `role` VALUES ('测试', '测四hi');
INSERT INTO `role` VALUES ('测试用户', '31651');
INSERT INTO `role` VALUES ('测试角色', 'ahahahh');
INSERT INTO `role` VALUES ('添加会员', '5466531312');
INSERT INTO `role` VALUES ('添加角色', '添加测试');
INSERT INTO `role` VALUES ('添加角色5555', '465465');
INSERT INTO `role` VALUES ('超级管理员', '拥有该管理系统最高权限');
INSERT INTO `role` VALUES ('钻石会员', '拥有用户最大优惠');
INSERT INTO `role` VALUES ('黄金会员', '拥有高于普通用户20%的优惠');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(0) UNSIGNED NULL DEFAULT 0,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `mg_state` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0,
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '普通用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (79, '项目测试', '123456', '666666', 1, '6666@qq.com', '2021-01-09 10:40:25', 1, '超级管理员');
INSERT INTO `user` VALUES (91, '项目测试', '123456', '17345059739', 1, '1215555@qq.com', '2021-01-09 11:24:09', 0, '普通会员');
INSERT INTO `user` VALUES (93, '项目测试', '123456', '17355554444', 1, '1215555@qq.com', '2021-01-09 11:24:10', 0, '黄金会员');
INSERT INTO `user` VALUES (95, '项目测试', '123456', '17345059739', 1, '1215555@qq.com', '2021-01-09 11:24:10', 0, '钻石会员');
INSERT INTO `user` VALUES (102, '项目测试', '123456', '17345059739', 0, '1215555@qq.com', '2021-01-09 11:34:34', 1, '钻石会员');
INSERT INTO `user` VALUES (103, '项目测试', '123456', '17345059739', 0, '1215555@qq.com', '2021-01-09 11:34:35', 1, '黄金会员');
INSERT INTO `user` VALUES (104, '项目测试', '123456', '17345059739', 0, '1215555@qq.com', '2021-01-09 11:34:35', 0, '钻石会员');
INSERT INTO `user` VALUES (105, '项目测试', '123456', '17345059739', 0, '1215555@qq.com', '2021-01-09 11:34:36', 0, '黄金会员');
INSERT INTO `user` VALUES (106, '欧阳娜娜', '123456', '17754858585', 0, '121515@qq.com', '2021-01-09 14:40:20', 0, NULL);
INSERT INTO `user` VALUES (107, '欧阳娜娜', '123456', '17754858585', 0, '121515@qq.com', '2021-01-09 14:40:57', 0, NULL);
INSERT INTO `user` VALUES (111, 'admin', '123456', '17345059739', 0, 'admin@qq.com', '2021-01-09 20:35:38', 0, NULL);
INSERT INTO `user` VALUES (112, 'admin', '123456', '17345053939', 0, 'admin@qq.com', '2021-01-09 20:37:19', 1, '黄金会员');
INSERT INTO `user` VALUES (116, '添加', '123456', '17355554444', 0, '11111@qq.com', '2021-01-11 11:31:30', 0, '普通用户');
INSERT INTO `user` VALUES (118, '添加测试1111', '123456', '17755554444', 0, '1525252@qq.com', '2021-01-12 14:56:26', 0, '普通用户');

SET FOREIGN_KEY_CHECKS = 1;
