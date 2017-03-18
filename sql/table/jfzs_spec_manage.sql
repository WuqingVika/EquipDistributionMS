/*
MySQL Data Transfer
Source Host: localhost
Source Database: equipdistribution
Target Host: localhost
Target Database: equipdistribution
Date: 2017/3/18 15:16:18
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for jfzs_spec_manage
-- ----------------------------
DROP TABLE IF EXISTS `jfzs_spec_manage`;
CREATE TABLE `jfzs_spec_manage` (
  `SPEC_ID` decimal(18,0) NOT NULL DEFAULT '0',
  `SPEC_NAME` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`SPEC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `jfzs_spec_manage` VALUES ('0', '其他');
INSERT INTO `jfzs_spec_manage` VALUES ('1', '传输');
INSERT INTO `jfzs_spec_manage` VALUES ('2', '数据');
INSERT INTO `jfzs_spec_manage` VALUES ('3', '电源');
