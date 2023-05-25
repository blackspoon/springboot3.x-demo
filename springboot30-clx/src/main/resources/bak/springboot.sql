/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : springboot

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 25/05/2023 15:52:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for people
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people` (
  `person_id` int NOT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of people
-- ----------------------------
BEGIN;
INSERT INTO `people` VALUES (1, 'chi', 'liang');
INSERT INTO `people` VALUES (2, 'sun', 'long');
INSERT INTO `people` VALUES (10, '王', '五');
INSERT INTO `people` VALUES (12, '李', '四');
INSERT INTO `people` VALUES (15, '关', '羽');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (11, 'A');
INSERT INTO `sys_user` VALUES (22, 'B');
INSERT INTO `sys_user` VALUES (33, 'C');
INSERT INTO `sys_user` VALUES (44, 'D');
INSERT INTO `sys_user` VALUES (55, 'E');
INSERT INTO `sys_user` VALUES (66, 'F');
INSERT INTO `sys_user` VALUES (77, 'G');
COMMIT;

-- ----------------------------
-- Table structure for user_score
-- ----------------------------
DROP TABLE IF EXISTS `user_score`;
CREATE TABLE `user_score` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `user_score` bigint NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user_score
-- ----------------------------
BEGIN;
INSERT INTO `user_score` VALUES (1, 11, 109, 'A');
INSERT INTO `user_score` VALUES (2, 22, 106, 'B');
INSERT INTO `user_score` VALUES (3, 33, 110, 'C');
INSERT INTO `user_score` VALUES (4, 44, 10, 'D');
INSERT INTO `user_score` VALUES (6, 55, 124, 'E');
INSERT INTO `user_score` VALUES (7, 11, 109, 'A');
INSERT INTO `user_score` VALUES (8, 11, 200, 'A');
INSERT INTO `user_score` VALUES (9, 11, 109, 'A');
INSERT INTO `user_score` VALUES (10, 22, 90, 'B');
INSERT INTO `user_score` VALUES (11, 33, 105, 'C');
INSERT INTO `user_score` VALUES (12, 44, 70, 'D');
INSERT INTO `user_score` VALUES (13, 55, 50, 'E');
INSERT INTO `user_score` VALUES (14, 66, 200, 'F');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
