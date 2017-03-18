/*
MySQL Data Transfer
Source Host: localhost
Source Database: equipdistribution
Target Host: localhost
Target Database: equipdistribution
Date: 2017/3/18 15:16:34
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `DISTRICT` decimal(18,0) DEFAULT NULL,
  `REGION_ID` decimal(18,0) DEFAULT NULL,
  `ROOM_ID` decimal(18,0) NOT NULL,
  `ROOM_NO` varchar(100) DEFAULT NULL,
  `FLOOR` decimal(3,0) DEFAULT NULL,
  `USAGE` varchar(1000) DEFAULT NULL,
  `PROPERTY_RIGHT` varchar(100) DEFAULT NULL,
  `SQUARE_MEASURE` decimal(18,0) DEFAULT NULL,
  `STATE` varchar(100) DEFAULT NULL,
  `CATEGORY` varchar(100) DEFAULT NULL,
  `CAPACITY` decimal(18,0) DEFAULT NULL,
  `QUALIFIED_LEVEL` varchar(10) DEFAULT NULL,
  `MANNED` varchar(10) DEFAULT NULL,
  `PIC` varchar(100) DEFAULT NULL,
  `PIC_CONTACT` varchar(100) DEFAULT NULL,
  `STRUCTURE` varchar(1000) DEFAULT NULL,
  `ROOM_STATE` varchar(100) DEFAULT NULL,
  `ROOMDAIWEI` varchar(2) DEFAULT NULL,
  `ROOMLEIXING` varchar(1000) DEFAULT NULL,
  `ISZHONGXIN` varchar(1) DEFAULT NULL,
  `ISDELETE` varchar(1) DEFAULT NULL,
  `SUB_DISTRICT` decimal(10,0) DEFAULT NULL,
  `DOOR_NO` varchar(50) DEFAULT NULL,
  `FILE_ID` decimal(18,0) DEFAULT NULL,
  PRIMARY KEY (`ROOM_ID`),
  KEY `FK_ROOM_REFERENCE_REGION` (`REGION_ID`),
  CONSTRAINT `FK_ROOM_REFERENCE_REGION` FOREIGN KEY (`REGION_ID`) REFERENCES `region` (`REGION_ID`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `room` VALUES ('20', '1665', '6155', '移动徐工3楼北机房', '6', '6', '租借', '389', '在用', 'A', null, '0', '有人值守', '吴庆', '20130501', null, null, 'N', null, null, 'N', null, null, '116038');
