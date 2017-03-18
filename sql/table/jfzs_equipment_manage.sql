/*
MySQL Data Transfer
Source Host: localhost
Source Database: equipdistribution
Target Host: localhost
Target Database: equipdistribution
Date: 2017/3/18 15:15:57
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for jfzs_equipment_manage
-- ----------------------------
DROP TABLE IF EXISTS `jfzs_equipment_manage`;
CREATE TABLE `jfzs_equipment_manage` (
  `EQUIP_ID` bigint(18) NOT NULL AUTO_INCREMENT,
  `CABINET_ID` decimal(18,0) DEFAULT NULL,
  `CABINET_LAYER_NUM` decimal(18,0) DEFAULT NULL,
  `OCCUPY_LAYER_NUM` decimal(18,0) DEFAULT NULL,
  `NU_NUM` varchar(200) DEFAULT NULL,
  `EQUIP_NAME` varchar(500) DEFAULT NULL,
  `MANUFACTURER` varchar(200) DEFAULT NULL,
  `CABINET_SURFACE` varchar(100) DEFAULT NULL,
  `SPEC_ID` decimal(18,0) DEFAULT NULL,
  `CATEGORY` varchar(300) DEFAULT NULL,
  `MODEL` varchar(300) DEFAULT NULL,
  `ASSET_NO` varchar(50) DEFAULT NULL,
  `SUB_RACK_COUNT` decimal(18,0) DEFAULT NULL,
  `VERSION_INFO` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`EQUIP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `jfzs_equipment_manage` VALUES ('1', '1', '3', '8', '华为OSN057-11057', '11047-上海移动民生', '华为Huawei', 'A面', '1', 'OTN', 'OSN 5700', '暂无', '4', '暂无');
INSERT INTO `jfzs_equipment_manage` VALUES ('2', '3', '2', '1', 'nu0001', '11', '耐克', 'B面', '2', '一类', 'e01', '暂无', '2', '1.0版本');
