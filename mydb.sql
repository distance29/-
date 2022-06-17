/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : mydb

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 17/06/2022 16:12:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students`  (
  `num` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `zy` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('2019112001', '安安', '英语', '新疆乌鲁木齐', 22);
INSERT INTO `students` VALUES ('2019116002', '依依', '电子信息工程', '内蒙古赤峰', 21);
INSERT INTO `students` VALUES ('2019116026', '啊涂', '武器与工程', '重庆黔江', 22);
INSERT INTO `students` VALUES ('2019116051', '小红', '物联网工程', '重庆黔江', 23);
INSERT INTO `students` VALUES ('2019116066', '小黑', '电子信息工程', '四川成都', 15);
INSERT INTO `students` VALUES ('2019116099', '梅西', '汉语言文学', '四川成都', 21);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `name` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('12', '122');
INSERT INTO `users` VALUES ('12', '123');
INSERT INTO `users` VALUES ('1111', '1111');
INSERT INTO `users` VALUES ('666', '123');
INSERT INTO `users` VALUES ('1321', '11111');
INSERT INTO `users` VALUES ('12312', '123456');

SET FOREIGN_KEY_CHECKS = 1;
