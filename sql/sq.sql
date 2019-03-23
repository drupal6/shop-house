CREATE TABLE `t_product_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `state` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE TABLE `t_product_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `productType` int(11) DEFAULT NULL,
  `productUnit` int(11) DEFAULT NULL,
  `barCode` varchar(100) DEFAULT NULL,
  `inPrice` float(20) DEFAULT NULL,
  `outPrice` float(20) DEFAULT NULL,
  `stock` float(20) DEFAULT NULL,
  `state` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `t_product_in_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `num` float(20) DEFAULT NULL,
  `num1` float(20) DEFAULT NULL,
  `price` float(20) DEFAULT NULL,
  `opTime` datetime DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `state` int(11) DEFAULT 0,
  `inId` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `t_in_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `opTime` datetime DEFAULT NULL,
  `cash` float(20) DEFAULT NULL,
  `mark` varchar(200) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `t_out_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `opTime` datetime DEFAULT NULL,
  `cash` float(20) DEFAULT NULL,
  `mark` varchar(200) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `t_product_out_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `num` float(20) DEFAULT NULL,
  `price` float(20) DEFAULT NULL,
  `price1` float(20) DEFAULT NULL,
  `opTime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT 0,
  `returnNum` float(20) DEFAULT NULL,
  `returnTime` datetime DEFAULT NULL,
  `returnMark` varchar(200) DEFAULT NULL,
  `outId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `nickName` varchar(100) DEFAULT NULL,
  `admin` int(20) DEFAULT 0,
  `userId` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE TABLE `t_cash` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `opTime` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `num` float(20) DEFAULT NULL,
  `mark` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;