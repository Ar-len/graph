/*
Navicat MySQL Data Transfer

Source Server         : Ar-len
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : graph

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-05-15 09:12:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hypergraph
-- ----------------------------
DROP TABLE IF EXISTS `hypergraph`;
CREATE TABLE `hypergraph` (
  `graph_id` bigint(11) NOT NULL,
  `account_no` varchar(255) NOT NULL COMMENT '户编号',
  `layers` int(255) DEFAULT NULL COMMENT '计算层数',
  `use_type` varchar(255) DEFAULT NULL COMMENT '用途',
  `graph_name` varchar(255) DEFAULT NULL COMMENT '图形名称',
  `graph_area` decimal(30,0) DEFAULT NULL,
  `graph_areas` decimal(30,0) DEFAULT NULL,
  `point_set` varchar(3695) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `graph_type` varchar(255) DEFAULT NULL,
  `account_name` varchar(255) DEFAULT NULL,
  `tuglie` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `logical_delete` int(255) DEFAULT NULL COMMENT '1已逻辑删除,0/null正常',
  PRIMARY KEY (`graph_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hypergraph
-- ----------------------------
INSERT INTO `hypergraph` VALUES ('1260827006828806144', '22222222', '1', '1', 'polygon', '36', '36', '[{\"x\":-1635594.3700623624,\"y\":5469889.504291241,\"z\":2846043.307092633},{\"x\":-1635600.1261639625,\"y\":5469889.337167513,\"z\":2846040.3203074145},{\"x\":-1635598.0525400008,\"y\":5469891.942940896,\"z\":2846036.5038873926},{\"x\":-1635591.588691448,\"y\":5469892.454158882,\"z\":2846039.2360822195}]', '1', null, '摸底', '2020-05-14 14:59:18', '1');
INSERT INTO `hypergraph` VALUES ('1260838241192378368', '56522', '1', '1', 'polygon', '14', '14', '[{\"x\":-1635582.336926976,\"y\":5469895.772584649,\"z\":2846042.6292275414},{\"x\":-1635587.0957827931,\"y\":5469895.962249496,\"z\":2846041.1981206485},{\"x\":-1635585.3765945015,\"y\":5469897.759599763,\"z\":2846038.702611485},{\"x\":-1635581.704490632,\"y\":5469897.176408131,\"z\":2846040.435859438}]', '1', null, '摸底', '2020-05-14 15:43:57', '1');
INSERT INTO `hypergraph` VALUES ('1260840096488226816', 'string', '0', 'string', 'string', '0', '0', 'string', 'string', 'string', 'string', '2020-05-14 15:51:19', '0');
INSERT INTO `hypergraph` VALUES ('1260850160917807104', '56666666', '1', '1', 'polygon', '26', '26', '[{\"x\":-1635598.1663112363,\"y\":5469886.171631077,\"z\":2846047.6472930275},{\"x\":-1635602.6343887686,\"y\":5469885.973910904,\"z\":2846045.459527486},{\"x\":-1635601.3302537291,\"y\":5469888.021407652,\"z\":2846042.273866827},{\"x\":-1635595.4879362865,\"y\":5469889.172775857,\"z\":2846043.4185541975}]', '1', null, '摸底', '2020-05-14 16:31:18', '1');
INSERT INTO `hypergraph` VALUES ('1260869287858405376', '1101', '3', '1', 'polygon', '16', '48', '[{\"x\":-1635596.9618142515,\"y\":5469887.129200353,\"z\":2846046.258352002},{\"x\":-1635602.3493331692,\"y\":5469886.281493619,\"z\":2846044.791416826},{\"x\":-1635600.782021403,\"y\":5469887.7531058965,\"z\":2846042.863809368},{\"x\":-1635596.2057220424,\"y\":5469889.040383662,\"z\":2846043.019717986}]', '1', null, null, '2020-05-14 17:47:19', '0');
INSERT INTO `hypergraph` VALUES ('1260869288420442112', '1101', '0', null, 'gon_point', '0', '0', '{\"x\":-1635600.1355469578,\"y\":5469885.4706374165,\"z\":2846046.2365213796}', '2', 'lily', '1', '2020-05-14 17:47:19', '0');
