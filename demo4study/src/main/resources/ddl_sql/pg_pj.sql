/*
 Navicat Premium Data Transfer

 Source Server         : zcy
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : zcy

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 28/01/2020 15:30:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pg_pj
-- ----------------------------
DROP TABLE IF EXISTS `pg_pj`;
CREATE TABLE `pg_pj`  (
  `pid` int(10) NOT NULL,
  `jid` int(10) NOT NULL,
  PRIMARY KEY (`pid`, `jid`) USING BTREE,
  INDEX `fk_jid`(`jid`) USING BTREE,
  CONSTRAINT `fk_jid` FOREIGN KEY (`jid`) REFERENCES `project` (`jid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pid` FOREIGN KEY (`pid`) REFERENCES `programmer` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for programmer
-- ----------------------------
DROP TABLE IF EXISTS `programmer`;
CREATE TABLE `programmer`  (
  `pid` int(10) NOT NULL,
  `name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `jid` int(10) NOT NULL,
  `pname` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`jid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
