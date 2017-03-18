/*
MySQL Data Transfer
Source Host: localhost
Source Database: equipdistribution
Target Host: localhost
Target Database: equipdistribution
Date: 2017/3/18 15:16:05
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for jfzs_board_card_manage
-- ----------------------------
DROP TABLE IF EXISTS `jfzs_board_card_manage`;
CREATE TABLE `jfzs_board_card_manage` (
  `CARD_ID` decimal(18,0) NOT NULL,
  `SUB_RACK_ID` decimal(18,0) DEFAULT NULL,
  `OCCUPY_SLOT_NUM` decimal(18,0) DEFAULT NULL,
  `MANUFACTURER` varchar(200) DEFAULT NULL,
  `PURPOSE` varchar(300) DEFAULT NULL,
  `CATEGORY` varchar(300) DEFAULT NULL,
  `MODEL` varchar(300) DEFAULT NULL,
  `ASSET_NO` varchar(50) DEFAULT NULL,
  `POS_IDX` decimal(18,0) DEFAULT NULL,
  `CHANGE_DATE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CARD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for jfzs_spec_manage
-- ----------------------------
DROP TABLE IF EXISTS `jfzs_spec_manage`;
CREATE TABLE `jfzs_spec_manage` (
  `SPEC_ID` decimal(18,0) NOT NULL DEFAULT '0',
  `SPEC_NAME` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`SPEC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for region
-- ----------------------------
DROP TABLE IF EXISTS `region`;
CREATE TABLE `region` (
  `DISTRICT` decimal(3,0) NOT NULL COMMENT '15个区局（字典）',
  `REGION_ID` decimal(18,0) NOT NULL DEFAULT '0' COMMENT '局站ID',
  `REGION_NAME` varchar(200) NOT NULL COMMENT '局站名称',
  `ADDRESS` varchar(1000) DEFAULT NULL COMMENT '局站地址详细信息',
  `PROPERTY_RIGHT` varchar(100) DEFAULT NULL COMMENT '自有或租借',
  `FLOOR` decimal(3,0) DEFAULT NULL,
  `START_DATE` date DEFAULT NULL COMMENT '局站启用时间',
  `CATEGORY` varchar(100) DEFAULT NULL,
  `USAGE` varchar(100) DEFAULT NULL,
  `GEOGRAPHIC_LOCATION` varchar(100) DEFAULT NULL,
  `QUALIFIED_LEVEL` varchar(10) DEFAULT NULL,
  `MANNED` varchar(10) DEFAULT NULL,
  `APPERANCE` varchar(1000) DEFAULT NULL,
  `PIC` varchar(100) DEFAULT NULL,
  `PIC_CONTACT` varchar(100) DEFAULT NULL,
  `STATE` varchar(100) DEFAULT NULL,
  `ISDELETE` varchar(1) DEFAULT NULL,
  `DOOR_NAME` varchar(255) DEFAULT NULL,
  `GPS_X` varchar(20) DEFAULT NULL,
  `GPS_Y` varchar(20) DEFAULT NULL,
  `SUB_DISTRICT` decimal(10,0) DEFAULT NULL,
  `RE_ADDRRESS` varchar(4000) DEFAULT NULL,
  `REGION_NM` varchar(100) DEFAULT NULL,
  `GRID_NUM` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`REGION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='查询局站';

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
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) DEFAULT NULL,
  `WORK_NO` varchar(20) DEFAULT NULL,
  `PASSWORD` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `jfzs_cabinet_manage` VALUES ('1', '6155', '0', '上海NOC', 'A01', 'A01-直流配电屏1', 'A面,B面', '1', '暂无', null, null, '8', '1', '1', 'A01');
INSERT INTO `jfzs_cabinet_manage` VALUES ('2', '6155', '0', '上海NOC', 'A02', '测试机', 'B面', '1', '57', '5', '7', '6', '1', '2', 'A02');
INSERT INTO `jfzs_cabinet_manage` VALUES ('3', '6155', '0', '上海NOC', 'A03', '测试机kgd ', 'B面', '2', '57', '5', '7', '6', '1', '12', 'A03');
INSERT INTO `jfzs_cabinet_manage` VALUES ('4', '6155', '0', '上海NOC', 'A04', '测试机kgd 2', 'B面', '1', '57', '5', '7', '6', '11', '12', 'A04');
INSERT INTO `jfzs_equipment_manage` VALUES ('1', '1', '3', '8', '华为OSN057-11057', '11047-上海移动民生', '华为Huawei', 'A面', '1', 'OTN', 'OSN 5700', '暂无', '4', '暂无');
INSERT INTO `jfzs_equipment_manage` VALUES ('2', '3', '2', '1', 'nu0001', '11', '耐克', 'B面', '2', '一类', 'e01', '暂无', '2', '1.0版本');
INSERT INTO `jfzs_spec_manage` VALUES ('0', '其他');
INSERT INTO `jfzs_spec_manage` VALUES ('1', '传输');
INSERT INTO `jfzs_spec_manage` VALUES ('2', '数据');
INSERT INTO `jfzs_spec_manage` VALUES ('3', '电源');
INSERT INTO `jfzs_sub_rack_manage` VALUES ('1', '2', '2', '1', 'A');
INSERT INTO `jfzs_sub_rack_manage` VALUES ('2', '2', '1', '1', 'B');
INSERT INTO `region` VALUES ('20', '1665', '徐工机房', '徐工&306', '租借', '3', null, '', '1', null, null, null, '暂无', null, null, '在用', 'N', null, '121.557864', '31.229213', null, '富春路570号', null, null);
INSERT INTO `room` VALUES ('20', '1665', '6155', '移动徐工3楼北机房', '6', '6', '租借', '389', '在用', 'A', null, '0', '有人值守', '吴庆', '20130501', null, null, 'N', null, null, 'N', null, null, '116038');
INSERT INTO `system_variable` VALUES ('1', '1', '东区电信局', '东区电信局');
INSERT INTO `system_variable` VALUES ('1', '2', '南区电信局', '南区电信局');
INSERT INTO `system_variable` VALUES ('1', '3', '西区电信局', '西区电信局');
INSERT INTO `system_variable` VALUES ('1', '4', '北区电信局', '北区电信局');
INSERT INTO `system_variable` VALUES ('1', '20', '上海NOC', '上海NOC');
INSERT INTO `user_info` VALUES ('1', 'wq', '20130501403', '12');
