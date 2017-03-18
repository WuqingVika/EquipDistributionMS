/*
MySQL Data Transfer
Source Host: localhost
Source Database: equipdistribution
Target Host: localhost
Target Database: equipdistribution
Date: 2017/3/18 15:16:38
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for system_variable
-- ----------------------------
DROP TABLE IF EXISTS `system_variable`;
CREATE TABLE `system_variable` (
  `C_ID` decimal(10,0) NOT NULL COMMENT '字典类别ID',
  `P_VALUE` decimal(10,0) NOT NULL COMMENT '参数value',
  `P_TEXT` varchar(100) NOT NULL COMMENT '参数TEXT',
  `P_DESCRIPTION` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`C_ID`,`P_VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='查询区局';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `system_variable` VALUES ('1', '1', '东区电信局', '东区电信局');
INSERT INTO `system_variable` VALUES ('1', '2', '南区电信局', '南区电信局');
INSERT INTO `system_variable` VALUES ('1', '3', '西区电信局', '西区电信局');
INSERT INTO `system_variable` VALUES ('1', '4', '北区电信局', '北区电信局');
INSERT INTO `system_variable` VALUES ('1', '20', '上海NOC', '上海NOC');
