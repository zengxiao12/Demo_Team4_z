/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.17-log : Database - datacockpit
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`datacockpit` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `datacockpit`;

/*Table structure for table `analysistasks` */

DROP TABLE IF EXISTS `analysistasks`;

CREATE TABLE `analysistasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `starttime` datetime DEFAULT NULL,
  `endtime` date DEFAULT NULL,
  `cid` int(11) DEFAULT NULL COMMENT '企业编号',
  `did` int(11) DEFAULT NULL COMMENT '关联关系编号',
  `taskstatus` int(11) DEFAULT NULL COMMENT '已添加:0,进行中:1，完成:2,失败:-1',
  `rule` varchar(50) DEFAULT NULL,
  `feedback` varchar(1000) DEFAULT NULL,
  `arithmeticid` int(11) DEFAULT NULL COMMENT '算法ID',
  PRIMARY KEY (`id`),
  KEY `did` (`did`),
  CONSTRAINT `analysistasks_ibfk_1` FOREIGN KEY (`did`) REFERENCES `datarelation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `analysistasks` */

/*Table structure for table `companyinfo` */

DROP TABLE IF EXISTS `companyinfo`;

CREATE TABLE `companyinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `corpName` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `job` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '状态    禁：0,不禁1',
  `approval` int(11) DEFAULT NULL COMMENT '审批状态  拒：0，过：1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `companyinfo` */

/*Table structure for table `datarelation` */

DROP TABLE IF EXISTS `datarelation`;

CREATE TABLE `datarelation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL COMMENT '企业ID',
  `tid1` int(11) DEFAULT NULL COMMENT '关联表A字段',
  `tid2` int(11) DEFAULT NULL COMMENT '关联表B字段',
  `col1` int(11) DEFAULT NULL,
  `col2` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '关联关系状态 禁：0,不禁1',
  PRIMARY KEY (`id`),
  KEY `datarelation_ibfk_1` (`tid1`),
  KEY `datarelation_ibfk_2` (`tid2`),
  KEY `datarelation_col_1` (`col1`),
  KEY `datarelation_col_2` (`col2`),
  KEY `datarelation_com_1` (`cid`),
  CONSTRAINT `datarelation_col_1` FOREIGN KEY (`col1`) REFERENCES `tablecolumninfo` (`id`),
  CONSTRAINT `datarelation_col_2` FOREIGN KEY (`col2`) REFERENCES `tablecolumninfo` (`id`),
  CONSTRAINT `datarelation_com_1` FOREIGN KEY (`cid`) REFERENCES `companyinfo` (`id`),
  CONSTRAINT `datarelation_ibfk_1` FOREIGN KEY (`tid1`) REFERENCES `tableinfo` (`id`),
  CONSTRAINT `datarelation_ibfk_2` FOREIGN KEY (`tid2`) REFERENCES `tableinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `datarelation` */

/*Table structure for table `inform` */

DROP TABLE IF EXISTS `inform`;

CREATE TABLE `inform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `publishDate` datetime DEFAULT NULL,
  `details` varchar(1000) DEFAULT NULL,
  `publisher` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `inform` */

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `percode` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `permission_ibfk_1` FOREIGN KEY (`id`) REFERENCES `role_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `role_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user_role` (`id`),
  CONSTRAINT `role_ibfk_2` FOREIGN KEY (`id`) REFERENCES `user_role` (`id`),
  CONSTRAINT `role_ibfk_3` FOREIGN KEY (`id`) REFERENCES `role_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL COMMENT '角色ID',
  `pid` int(11) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_permission` */

/*Table structure for table `tablecolumninfo` */

DROP TABLE IF EXISTS `tablecolumninfo`;

CREATE TABLE `tablecolumninfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tid` int(11) DEFAULT NULL,
  `columnName` varchar(50) DEFAULT NULL,
  `columnType` varchar(50) DEFAULT NULL,
  `physicalColumnName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tid` (`tid`),
  CONSTRAINT `tablecolumninfo_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `tableinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tablecolumninfo` */

/*Table structure for table `tableinfo` */

DROP TABLE IF EXISTS `tableinfo`;

CREATE TABLE `tableinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `physicalTableName` varchar(200) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL COMMENT '企业ID',
  `state` int(11) DEFAULT NULL COMMENT '0禁用，1启用',
  `showtype` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  CONSTRAINT `tableinfo_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `companyinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tableinfo` */

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL COMMENT '角色ID',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `job` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '状态    禁：0,不禁1',
  PRIMARY KEY (`id`),
  CONSTRAINT `userinfo_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userinfo` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
