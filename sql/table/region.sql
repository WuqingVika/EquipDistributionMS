/*
MySQL Data Transfer
Source Host: localhost
Source Database: equipdistribution
Target Host: localhost
Target Database: equipdistribution
Date: 2017/3/18 15:16:30
*/

SET FOREIGN_KEY_CHECKS=0;
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
-- Records 
-- ----------------------------
INSERT INTO `region` VALUES ('20', '1665', '徐工机房', '徐工&306', '租借', '3', null, '', '1', null, null, null, '暂无', null, null, '在用', 'N', null, '121.557864', '31.229213', null, '富春路570号', null, null);
