/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : student

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 21/03/2022 02:58:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学号',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别 1 男 2 女',
  `student_class` int(11) NULL DEFAULT NULL COMMENT '班级',
  `student_build` int(11) NULL DEFAULT NULL COMMENT '楼栋',
  `student_room` int(11) NULL DEFAULT NULL COMMENT '宿舍',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
  `valid` int(1) NULL DEFAULT NULL COMMENT '可用状态 1 可用 0 不可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'Rose', '1', 1, 2, 1, 2, '123', 'img/2022/03/d1bdbf1b-4958-48c7-9dd4-675f3bd196c3.jpg', 1);

-- ----------------------------
-- Table structure for student_build
-- ----------------------------
DROP TABLE IF EXISTS `student_build`;
CREATE TABLE `student_build`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '楼栋名称',
  `valid` int(1) NULL DEFAULT NULL COMMENT '可用状态 1 可用 0 不可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_build
-- ----------------------------
INSERT INTO `student_build` VALUES (1, 'building 1', 1);
INSERT INTO `student_build` VALUES (2, 'building 2', 1);
INSERT INTO `student_build` VALUES (3, 'building 3', 1);

-- ----------------------------
-- Table structure for student_build_room
-- ----------------------------
DROP TABLE IF EXISTS `student_build_room`;
CREATE TABLE `student_build_room`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `build_id` int(11) NULL DEFAULT NULL COMMENT '楼栋编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '宿舍名称',
  `valid` int(1) NULL DEFAULT NULL COMMENT '可用状态 1 可用 0 不可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_build_room
-- ----------------------------
INSERT INTO `student_build_room` VALUES (1, 1, '1-1', 1);
INSERT INTO `student_build_room` VALUES (2, 1, '1-2', 1);

-- ----------------------------
-- Table structure for student_class
-- ----------------------------
DROP TABLE IF EXISTS `student_class`;
CREATE TABLE `student_class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '班级名称',
  `valid` int(1) NULL DEFAULT NULL COMMENT '可用状态 1 可用 0 不可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_class
-- ----------------------------
INSERT INTO `student_class` VALUES (1, 'class 1', 1);
INSERT INTO `student_class` VALUES (2, 'class 2', 1);
INSERT INTO `student_class` VALUES (3, 'class 3', 1);

-- ----------------------------
-- Table structure for student_sign
-- ----------------------------
DROP TABLE IF EXISTS `student_sign`;
CREATE TABLE `student_sign`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NULL DEFAULT NULL,
  `sign_time` datetime(0) NULL DEFAULT NULL,
  `sign_type` int(1) NULL DEFAULT NULL COMMENT '1 出 2 入',
  `notes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '补录信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_sign
-- ----------------------------
INSERT INTO `student_sign` VALUES (1, 1, '2022-03-13 18:14:21', 1, NULL);
INSERT INTO `student_sign` VALUES (2, 1, '2022-03-13 22:00:00', 2, NULL);
INSERT INTO `student_sign` VALUES (3, 1, '2022-03-31 23:10:00', 2, '12');
INSERT INTO `student_sign` VALUES (4, 1, '2022-03-20 19:28:57', 1, NULL);
INSERT INTO `student_sign` VALUES (5, 1, '2022-03-20 19:29:11', 1, NULL);
INSERT INTO `student_sign` VALUES (6, 1, '2022-03-20 19:29:15', 1, NULL);
INSERT INTO `student_sign` VALUES (7, 1, '2022-03-20 19:29:19', 1, NULL);
INSERT INTO `student_sign` VALUES (8, 1, '2022-03-20 19:29:23', 1, NULL);
INSERT INTO `student_sign` VALUES (9, 1, '2022-03-20 19:29:27', 1, NULL);
INSERT INTO `student_sign` VALUES (10, 1, '2022-03-20 19:29:31', 1, NULL);
INSERT INTO `student_sign` VALUES (11, 1, '2022-03-20 19:29:41', 1, NULL);
INSERT INTO `student_sign` VALUES (12, 1, '2022-03-20 19:32:09', 1, NULL);
INSERT INTO `student_sign` VALUES (13, 1, '2022-03-20 19:32:11', 1, NULL);
INSERT INTO `student_sign` VALUES (14, 1, '2022-03-20 19:32:13', 1, NULL);
INSERT INTO `student_sign` VALUES (15, 1, '2022-03-20 19:32:15', 1, NULL);
INSERT INTO `student_sign` VALUES (16, 1, '2022-03-20 19:32:17', 1, NULL);
INSERT INTO `student_sign` VALUES (17, 1, '2022-03-20 19:32:47', 1, NULL);
INSERT INTO `student_sign` VALUES (18, 1, '2022-03-20 19:32:58', 1, NULL);
INSERT INTO `student_sign` VALUES (19, 1, '2022-03-20 19:33:02', 2, NULL);
INSERT INTO `student_sign` VALUES (20, 1, '2022-03-20 19:33:04', 2, NULL);
INSERT INTO `student_sign` VALUES (21, 1, '2022-03-20 19:33:14', 2, NULL);
INSERT INTO `student_sign` VALUES (22, 1, '2022-03-21 02:20:47', 1, NULL);
INSERT INTO `student_sign` VALUES (23, 1, '2022-03-21 02:20:49', 1, NULL);
INSERT INTO `student_sign` VALUES (24, 1, '2022-03-21 02:21:07', 1, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `access` int(255) NULL DEFAULT NULL COMMENT '1 宿舍管理员 2 辅导员 3 学工处',
  `valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'manager', '123', '15888888888', 3, 1);
INSERT INTO `user` VALUES (2, 'Instructor 1', '123', '15999999999', 2, 1);
INSERT INTO `user` VALUES (3, 'Houseparent 1', '123', '15777777777', 1, 1);
INSERT INTO `user` VALUES (4, 'Instructor 2', '123', '15999999999', 2, 1);
INSERT INTO `user` VALUES (5, 'Houseparent 2', '123', '15777777777', 1, 1);
INSERT INTO `user` VALUES (6, '1234', '14', '14', 3, 1);
INSERT INTO `user` VALUES (7, '123', '123', '123', 2, 1);

-- ----------------------------
-- Table structure for user_build
-- ----------------------------
DROP TABLE IF EXISTS `user_build`;
CREATE TABLE `user_build`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `build_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_build
-- ----------------------------
INSERT INTO `user_build` VALUES (1, 3, 1);

-- ----------------------------
-- Table structure for user_class
-- ----------------------------
DROP TABLE IF EXISTS `user_class`;
CREATE TABLE `user_class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `class_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_class
-- ----------------------------
INSERT INTO `user_class` VALUES (1, 2, 2);
INSERT INTO `user_class` VALUES (2, 4, 1);

SET FOREIGN_KEY_CHECKS = 1;
