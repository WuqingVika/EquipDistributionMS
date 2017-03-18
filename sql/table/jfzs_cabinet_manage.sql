/*
MySQL Data Transfer
Source Host: localhost
Source Database: equipdistribution
Target Host: localhost
Target Database: equipdistribution
Date: 2017/3/18 15:15:51
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for jfzs_cabinet_manage
-- ----------------------------
DROP TABLE IF EXISTS `jfzs_cabinet_manage`;
CREATE TABLE `jfzs_cabinet_manage` (
  `CABINET_ID` bigint(18) NOT NULL AUTO_INCREMENT,
  `ROOM_ID` decimal(18,0) DEFAULT NULL,
  `TYPE` decimal(18,0) DEFAULT NULL,
  `COMPANY` varchar(200) DEFAULT NULL,
  `CABINET_NUM` varchar(200) DEFAULT NULL,
  `CABINET_NAME` varchar(500) DEFAULT NULL,
  `CABINET_SURFACE` varchar(100) DEFAULT NULL,
  `SPEC_ID` decimal(18,0) DEFAULT NULL,
  `ASSET_NO` varchar(50) DEFAULT NULL,
  `POWER_A` varchar(50) DEFAULT NULL,
  `POWER_B` varchar(50) DEFAULT NULL,
  `LAYER_COUNT` decimal(18,0) DEFAULT NULL,
  `POS_X` decimal(18,0) DEFAULT NULL,
  `POS_Y` decimal(18,0) DEFAULT NULL,
  `LABEL` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CABINET_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `jfzs_cabinet_manage` VALUES ('1', '6155', '0', '上海NOC', 'A01', 'A01-直流配电屏1', 'A面,B面', '1', '暂无', null, null, '8', '1', '1', 'A01');
INSERT INTO `jfzs_cabinet_manage` VALUES ('2', '6155', '0', '上海NOC', 'A02', '测试机', 'B面', '1', '57', '5', '7', '6', '1', '2', 'A02');
INSERT INTO `jfzs_cabinet_manage` VALUES ('3', '6155', '0', '上海NOC', 'A03', '测试机kgd ', 'B面', '2', '57', '5', '7', '6', '1', '12', 'A03');
INSERT INTO `jfzs_cabinet_manage` VALUES ('4', '6155', '0', '上海NOC', 'A04', '测试机kgd 2', 'B面', '1', '57', '5', '7', '6', '11', '12', 'A04');
