/*
MySQL Data Transfer
Source Host: localhost
Source Database: equipdistribution
Target Host: localhost
Target Database: equipdistribution
Date: 2017/3/18 15:15:43
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
-- Records 
-- ----------------------------
