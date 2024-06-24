/*
 Navicat MySQL Data Transfer

 Source Server         : 1761
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : bm2

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 20/11/2022 15:30:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_out
-- ----------------------------
DROP TABLE IF EXISTS `book_out`;
CREATE TABLE `book_out`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '顺序码',
  `ISBN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ISBN码',
  `bookname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名\n',
  `out_num` int(11) NULL DEFAULT NULL COMMENT '售出数量',
  `markprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '售价',
  `zhekou` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '折扣',
  `sholdpay` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应付',
  `receive` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '找零',
  `return` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收钱额\n',
  `time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book_out
-- ----------------------------
INSERT INTO `book_out` VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for book_stack
-- ----------------------------
DROP TABLE IF EXISTS `book_stack`;
CREATE TABLE `book_stack`  (
  `ISBN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ISBN码',
  `bookname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `markprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '标价',
  PRIMARY KEY (`ISBN`) USING BTREE,
  INDEX `bookname`(`bookname`) USING BTREE,
  INDEX `num`(`num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book_stack
-- ----------------------------
INSERT INTO `book_stack` VALUES ('11', '11', NULL, NULL, NULL);
INSERT INTO `book_stack` VALUES ('2', '2', '2', 2, 2.00);

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES (1, '1', '1');

-- ----------------------------
-- Table structure for new_book_in
-- ----------------------------
DROP TABLE IF EXISTS `new_book_in`;
CREATE TABLE `new_book_in`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ISBN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bookname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of new_book_in
-- ----------------------------
INSERT INTO `new_book_in` VALUES (1, '1', NULL, NULL, NULL, 1, NULL);
INSERT INTO `new_book_in` VALUES (2, '2', '2', '2', 2.00, 2, '2022-11-07 11:15:45');

SET FOREIGN_KEY_CHECKS = 1;
