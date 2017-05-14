/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : uml

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2017-05-07 22:14:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `jisuanjiziyuan`
-- ----------------------------
DROP TABLE IF EXISTS `jisuanjiziyuan`;
CREATE TABLE `jisuanjiziyuan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proid` varchar(255) DEFAULT NULL,
  `objid` varchar(255) DEFAULT NULL,
  `mingcheng` varchar(255) DEFAULT NULL,
  `bianhao` varchar(255) DEFAULT NULL,
  `leixing` varchar(255) DEFAULT NULL,
  `laiyuan` varchar(255) DEFAULT NULL,
  `cunfangweizhi` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jisuanjiziyuan
-- ----------------------------
INSERT INTO `jisuanjiziyuan` VALUES ('1', 'proid', 'objid', 'mingcheng', 'biaohao', null, null, null);

-- ----------------------------
-- Table structure for `knowledgeunit`
-- ----------------------------
DROP TABLE IF EXISTS `knowledgeunit`;
CREATE TABLE `knowledgeunit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proid` varchar(255) DEFAULT NULL,
  `objid` varchar(255) DEFAULT NULL,
  `mingcheng` varchar(255) DEFAULT NULL,
  `bianhao` varchar(255) DEFAULT NULL,
  `lingyu` varchar(255) DEFAULT NULL,
  `zhishineirong` varchar(255) DEFAULT NULL,
  `suoshuzaiti` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of knowledgeunit
-- ----------------------------

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) DEFAULT NULL,
  `proid` varchar(255) DEFAULT NULL,
  `objid` varchar(255) DEFAULT NULL,
  `xingming` varchar(255) DEFAULT NULL,
  `gonghao` varchar(255) DEFAULT NULL,
  `lingyu` varchar(255) DEFAULT NULL,
  `bumen` varchar(255) DEFAULT NULL,
  `zhishineirong` varchar(255) DEFAULT NULL,
  `suoshuzaiti` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------

-- ----------------------------
-- Table structure for `ruanjianshebei`
-- ----------------------------
DROP TABLE IF EXISTS `ruanjianshebei`;
CREATE TABLE `ruanjianshebei` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proid` varchar(255) DEFAULT NULL,
  `objid` varchar(255) DEFAULT NULL,
  `mingcheng` varchar(255) DEFAULT NULL,
  `banben` varchar(255) DEFAULT NULL,
  `yingyongfanwei` varchar(255) DEFAULT NULL,
  `yingyongjiaocheng` varchar(255) DEFAULT NULL,
  `wuliweizhi` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ruanjianshebei
-- ----------------------------

-- ----------------------------
-- Table structure for `solutionbuild`
-- ----------------------------
DROP TABLE IF EXISTS `solutionbuild`;
CREATE TABLE `solutionbuild` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proid` varchar(255) DEFAULT NULL,
  `objid` varchar(255) DEFAULT NULL,
  `qingjingmingcheng` varchar(255) DEFAULT NULL,
  `zhixingzhe` varchar(255) DEFAULT NULL,
  `shuchuchengguo` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `standard` varchar(255) DEFAULT NULL,
  `fuzhusheshi` varchar(255) DEFAULT NULL,
  `kongqihuanjing` varchar(255) DEFAULT NULL,
  `kongjian` varchar(255) DEFAULT NULL,
  `zuzhijili` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of solutionbuild
-- ----------------------------
INSERT INTO `solutionbuild` VALUES ('1', 'null', 'null', 'null', 'null', 'null', '132465', 'null', 'null', '45645', 'null', 'null', 'null');
INSERT INTO `solutionbuild` VALUES ('2', '78f8f70c-dc82-4794-abf7-680e043645f1', '2', '情景名称', '执行者', '输出陈国', '时间', '地点', '标准', '辅助设施', '空间', '空间', '组织激励');
INSERT INTO `solutionbuild` VALUES ('3', 'ef735795-f964-4c16-b554-e6f15282010d', '2', 'dug', 'king', '', '', '', '', '', '', '', '');
INSERT INTO `solutionbuild` VALUES ('4', 'ef735795-f964-4c16-b554-e6f15282010d', '3', 'dug', 'king', '7567567', '', '', '', '', '', '', '');
INSERT INTO `solutionbuild` VALUES ('5', 'e1e4f2f9-f492-4433-af8b-49481ec2ed37', '2', '度过', '', '', '', '', '', '', '', '', '');
INSERT INTO `solutionbuild` VALUES ('6', '244c53de-26f0-4587-acf4-cce4656754ad', '2', '大苏打', '', '', '', '', '', '', '', '', '');
INSERT INTO `solutionbuild` VALUES ('7', '244c53de-26f0-4587-acf4-cce4656754ad', '3', '软件步骤', '', '', '', '', '', '', '', '', '');
INSERT INTO `solutionbuild` VALUES ('8', '69424f60-2d39-482e-9138-72dfa78c7f2e', '2', '恶趣味去', '恶趣味', '特退热', '', '', '', '', '', '', '');
INSERT INTO `solutionbuild` VALUES ('9', '69424f60-2d39-482e-9138-72dfa78c7f2e', '3', '恶趣味请问', '恶趣味去', '', '', '', '', '', '', '', '');

-- ----------------------------
-- Table structure for `tuwenziliao`
-- ----------------------------
DROP TABLE IF EXISTS `tuwenziliao`;
CREATE TABLE `tuwenziliao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proid` varchar(255) DEFAULT NULL,
  `objid` varchar(255) DEFAULT NULL,
  `mingcheng` varchar(255) DEFAULT NULL,
  `zuozhe` varchar(255) DEFAULT NULL,
  `banbenhao` varchar(255) DEFAULT NULL,
  `leixing` varchar(255) DEFAULT NULL,
  `keliyonglv` varchar(255) DEFAULT NULL,
  `wuliweizhi` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tuwenziliao
-- ----------------------------

-- ----------------------------
-- Table structure for `yewu`
-- ----------------------------
DROP TABLE IF EXISTS `yewu`;
CREATE TABLE `yewu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proid` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `zhixingzhe` varchar(255) DEFAULT NULL,
  `zhizingzheone` varchar(255) DEFAULT NULL,
  `zhixingzhetwo` varchar(255) DEFAULT NULL,
  `mubiao` varchar(255) DEFAULT NULL,
  `mubiaoone` varchar(255) DEFAULT NULL,
  `mubiaotwo` varchar(255) DEFAULT NULL,
  `zuzhi` varchar(255) DEFAULT NULL,
  `zuzhione` varchar(255) DEFAULT NULL,
  `zuzhitwo` varchar(255) DEFAULT NULL,
  `shijian` varchar(255) DEFAULT NULL,
  `shijianone` varchar(255) DEFAULT NULL,
  `shijiantwo` varchar(255) DEFAULT NULL,
  `five` varchar(255) DEFAULT NULL,
  `fiveone` varchar(255) DEFAULT NULL,
  `fivetwo` varchar(255) DEFAULT NULL,
  `ziyuan` varchar(255) DEFAULT NULL,
  `ziyuanone` varchar(255) DEFAULT NULL,
  `ziyuantwo` varchar(255) DEFAULT NULL,
  `weizhi` varchar(255) DEFAULT NULL,
  `weizhione` varchar(255) DEFAULT NULL,
  `weizhitwo` varchar(255) DEFAULT NULL,
  `qiyefenwei` varchar(255) DEFAULT NULL,
  `qiyefenweione` varchar(255) DEFAULT NULL,
  `qiyefenweitwo` varchar(255) DEFAULT NULL,
  `yuanyin` varchar(255) DEFAULT NULL,
  `yuanyinone` varchar(255) DEFAULT NULL,
  `yuanyintwo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yewu
-- ----------------------------

-- ----------------------------
-- Table structure for `yingjianshebei`
-- ----------------------------
DROP TABLE IF EXISTS `yingjianshebei`;
CREATE TABLE `yingjianshebei` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proid` varchar(255) DEFAULT NULL,
  `objid` varchar(255) DEFAULT NULL,
  `mingcheng` varchar(255) DEFAULT NULL,
  `xinghao` varchar(255) DEFAULT NULL,
  `yingjianyaoqiu` varchar(255) DEFAULT NULL,
  `yingyongjiaocheng` varchar(255) DEFAULT NULL,
  `wuliweizhi` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yingjianshebei
-- ----------------------------
INSERT INTO `yingjianshebei` VALUES ('1', '12', '12', '12', '12', '', '', '');
INSERT INTO `yingjianshebei` VALUES ('2', '1212', '1212', null, null, null, null, null);

-- ----------------------------
-- Table structure for `yuhd`
-- ----------------------------
DROP TABLE IF EXISTS `yuhd`;
CREATE TABLE `yuhd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proid` varchar(255) DEFAULT NULL,
  `objid` varchar(255) DEFAULT NULL,
  `huodongmingcheng` varchar(255) DEFAULT NULL,
  `ywuhuodongbianhao` varchar(255) DEFAULT NULL,
  `fuguocheng` varchar(255) DEFAULT NULL,
  `huodongmiaoshu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yuhd
-- ----------------------------
INSERT INTO `yuhd` VALUES ('1', '12321', '123', '名称', '阿娇卡迪夫', '234', '234234');
