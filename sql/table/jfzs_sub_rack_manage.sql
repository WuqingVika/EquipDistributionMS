/*
MySQL Data Transfer
Source Host: localhost
Source Database: equipdistribution
Target Host: localhost
Target Database: equipdistribution
Date: 2017/3/18 15:16:25
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for jfzs_sub_rack_manage
-- ----------------------------
DROP TABLE IF EXISTS `jfzs_sub_rack_manage`;
CREATE TABLE `jfzs_sub_rack_manage` (
  `SUB_RACK_ID` bigint(18) NOT NULL AUTO_INCREMENT,
  `EQUIP_ID` decimal(18,0) DEFAULT NULL,
  `ORDER_NO` decimal(18,0) DEFAULT NULL,
  `SLOT_COUNT` decimal(18,0) DEFAULT NULL,
  `LABEL` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SUB_RACK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `jfzs_sub_rack_manage` VALUES ('1', '2', '2', '1', 'A');
INSERT INTO `jfzs_sub_rack_manage` VALUES ('2', '2', '1', '1', 'B');
