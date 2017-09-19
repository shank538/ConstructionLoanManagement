

CREATE TABLE `constructionloanmanagement`.`address` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `street` varchar(50) NOT NULL,
  `zip_code` varchar(20) DEFAULT NULL,
  `city` varchar(30) NOT NULL,
  `country_code` varchar(10) NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `constructionloanmanagement`.`individual` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_type` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email_id` varchar(30) NOT NULL,
  `handphone` varchar(20) DEFAULT NULL,
  `deskphone` varchar(20) DEFAULT NULL,
  `street` varchar(50) DEFAULT NULL,
  `zip_code` varchar(20) DEFAULT NULL,
  `city` varchar(30) NOT NULL,
  `country_code` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `constructionloanmanagement`.`guideline` (

  ` id` int(10) unsigned NOT NULL,
  `addressid` int(10) unsigned NOT NULL,
  `projectname` varchar(45) NOT NULL,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  PRIMARY KEY (` id`),
KEY `fk_addressid` (`addressid`),
  CONSTRAINT `fk_addressid` FOREIGN KEY (`addressid`) REFERENCES `address` (`id`)
);


CREATE TABLE `constructionloanmanagement`.`constructionphase` (

 `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectname` varchar(30) NOT NULL,
  `code` varchar(30) NOT NULL,
  `masterreferenceid` int(10) unsigned NOT NULL,
  `status` varchar(10) NOT NULL,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `constructioncost` decimal(13,4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_masterreferenceid` (`masterreferenceid`),
  CONSTRAINT `fk_masterreferenceid` FOREIGN KEY (`masterreferenceid`) REFERENCES `guideline` (` id`)
);



CREATE TABLE `constructionloanmanagement`.`inspection` (

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
);


CREATE TABLE `constructionloanmanagement`.`loan` (

 `loanid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `accountid` varchar(30) NOT NULL,
  `bankid` int(11) NOT NULL,
  `constructionphaseid` int(10) unsigned DEFAULT NULL,
  `loandescription` varchar(50) NOT NULL,
  `loantype` varchar(30) NOT NULL,
  `amount` decimal(13,4) NOT NULL,
  `disbursementdate` datetime DEFAULT NULL,
  `interestrate` decimal(5,5) DEFAULT NULL,
  `loanexpirydate` datetime DEFAULT NULL,
  PRIMARY KEY (`loanid`),
  UNIQUE KEY `accountid` (`accountid`),
  UNIQUE KEY `loandescription` (`loandescription`),
  KEY `fk_constructionphaseloanid` (`constructionphaseid`),
  CONSTRAINT `fk_constructionphaseloanid` FOREIGN KEY (`constructionphaseid`) REFERENCES `constructionphase` (`id`) ON DELETE CASCADE

);

