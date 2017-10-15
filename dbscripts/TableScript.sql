CREATE TABLE constructionloanmanagement.`address` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `street` varchar(50) NOT NULL,
  `zip_code` varchar(20) DEFAULT NULL,
  `city` varchar(30) NOT NULL,
  `country_code` varchar(10) NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE constructionloanmanagement.`constructionphase` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `phasename` varchar(45) NOT NULL,
  `masterreferenceid` int(10) unsigned NOT NULL,
  `constructionphasenumber` int(10) unsigned NOT NULL,
  `status` varchar(20) NOT NULL,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `constructioncost` decimal(13,4) NOT NULL,
  `revisedconstructioncost` decimal(13,4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `construction_unique_index` (`projectname`,`constructionphasenumber`),
  KEY `fk_masterreferenceid` (`masterreferenceid`),
  CONSTRAINT `fk_masterreferenceid` FOREIGN KEY (`masterreferenceid`) REFERENCES `guideline` (` id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE constructionloanmanagement.`guideline` (
  ` id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `addressid` int(10) unsigned NOT NULL,
  `projectname` varchar(45) NOT NULL,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  PRIMARY KEY (` id`),
  KEY `fk_addressid` (`addressid`),
  CONSTRAINT `fk_addressid` FOREIGN KEY (`addressid`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE constructionloanmanagement.`individual` (
 `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `role_type` varchar(10) NOT NULL,
 `firstname` varchar(100) NOT NULL,
 `lastname` varchar(100) NOT NULL,
 `email` varchar(100) NOT NULL,
 `company` varchar(100) DEFAULT NULL,
 `handphone` varchar(20) DEFAULT NULL,
 `deskphone` varchar(20) DEFAULT NULL,
 `street` varchar(100) DEFAULT NULL,
 `zip_code` varchar(20) DEFAULT NULL,
 `city` varchar(30) DEFAULT NULL,
 `country_code` varchar(10) DEFAULT NULL,
 `username` varchar(30) NOT NULL,
 `password` varchar(100) NOT NULL,
 `registered_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
 `aboutme` varchar(300) DEFAULT NULL,
 PRIMARY KEY (`id`),
 UNIQUE KEY `username_ind_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE constructionloanmanagement.`inspection` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `constructionid` int(10) unsigned NOT NULL,
  `inspectionstatus` varchar(40) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `individualid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_constructionid` (`constructionid`),
  CONSTRAINT `fk_constructionid` FOREIGN KEY (`constructionid`) REFERENCES `constructionphase` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE constructionloanmanagement.`loan` (
  `loanid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `accountid` varchar(30) NOT NULL,
  `bankid` int(11) NOT NULL,
  `constructionphaseid` int(10) unsigned NOT NULL,
  `loandescription` varchar(50) NOT NULL,
  `loantype` varchar(30) NOT NULL,
  `amount` decimal(13,4) NOT NULL,
  `disbursementdate` datetime DEFAULT NULL,
  `interestrate` decimal(5,5) DEFAULT NULL,
  `loanexpirydate` datetime DEFAULT NULL,
  PRIMARY KEY (`loanid`),
  KEY `fk_constructionphaseloanid` (`constructionphaseid`),
  CONSTRAINT `fk_constructionphaseloanid` FOREIGN KEY (`constructionphaseid`) REFERENCES `constructionphase` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


