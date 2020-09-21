use ssafydb;
drop table profile;
drop table notice;
drop table qna;
drop table account;
create table if not exists account (
	userid varchar(80) not null,
    passwd varchar(80) not null,
    name varchar(80) not null,
    addr1 varchar(255) not null,
    addr2 varchar(255),
    email varchar(80) not null,
    phone varchar(80) not null,
    status varchar(10) not null default 'off',
	joindate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	primary key (userid)
)
comment='사용자 기본 정보를 담고 있음, status -> on/off';

create table if not exists profile (
    userid varchar(80) not null,
    fav_dong1 varchar(20) DEFAULT NULL,
	fav_dong2 varchar(20) DEFAULT NULL,
	fav_dong3 varchar(20) DEFAULT NULL,
    mailing varchar(10) not null default 'off',
	PRIMARY KEY (userid),
	CONSTRAINT profile_fk FOREIGN KEY (userid) REFERENCES account (userid)
)
comment='사용자의 관심지역 등 정보를 담고 있음, mailing -> on/off';

create table if not exists `shop` (
  `no` int NOT NULL,
  `shopname` varchar(255) DEFAULT NULL,
  `localname` varchar(255) DEFAULT NULL,
  `code1` varchar(255) DEFAULT NULL,
  `codename1` varchar(255) DEFAULT NULL,
  `code2` varchar(255) DEFAULT NULL,
  `codename2` varchar(255) DEFAULT NULL,
  `code3` varchar(255) DEFAULT NULL,
  `codename3` varchar(255) DEFAULT NULL,
  `code4` varchar(255) DEFAULT NULL,
  `codename4` varchar(255) DEFAULT NULL,
  `citycode` int DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `gucode` int DEFAULT NULL,
  `gu` varchar(255) DEFAULT NULL,
  `dongcode` int DEFAULT NULL,
  `dong` varchar(255) DEFAULT NULL,
  `jibuaddress` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `oldpostcode` int DEFAULT NULL,
  `postcode` int DEFAULT NULL,
  `lng` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`no`)
)
comment='상권정보 테이블';

create table if not exists `houseinfo` (
  `no` int NOT NULL,
  `dong` varchar(20) NOT NULL,
  `AptName` varchar(80) NOT NULL,
  `code` varchar(20) NOT NULL,
  `buildYear` int NOT NULL,
  `jibun` varchar(20) NOT NULL,
  `lat` double NOT NULL,
  `lng` double NOT NULL,
  `img` varchar(40),
  primary key(no)
)  comment = '맵 마커 표시용';

create table if not exists `baseaddress` (
  `num` int NOT NULL,
  `city` varchar(20),
  `code` varchar(20) NOT NULL,
  `dongcode` varchar(20) NOT NULL,
  `gugun` varchar(20),
  `dong` varchar(20),
  `lat` double,
  `lng` double,
  primary key(num)
) comment = '시, 구군, 동';

create table if not exists `housedeal` (
  `no` int NOT NULL AUTO_INCREMENT,
  `dong` varchar(30) NOT NULL,
  `AptName` varchar(50) NOT NULL,
  `code` varchar(30) NOT NULL,
  `dealAmount` varchar(50) NOT NULL,
  `buildYear` varchar(30) DEFAULT NULL,
  `dealYear` varchar(30) DEFAULT NULL,
  `dealMonth` varchar(30) DEFAULT NULL,
  `dealDay` varchar(30) DEFAULT NULL,
  `area` varchar(30) DEFAULT NULL,
  `floor` varchar(30) DEFAULT NULL,
  `jibun` varchar(30) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `rentMoney` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`no`)
);

create table if not exists qna (
       id	INT auto_increment,
       title VARCHAR(300) not null,
       content VARCHAR(4000) not null,
       userid VARCHAR(20) not null,
       datetime timestamp DEFAULT CURRENT_TIMESTAMP,
       reply_content VARCHAR(4000),
       reply_userid VARCHAR(20),
       reply_datetime timestamp,
       PRIMARY KEY (`id`),
       CONSTRAINT `qna_fk1` FOREIGN KEY (`userid`) REFERENCES `account` (`userid`),
       CONSTRAINT `qna_fk2` FOREIGN KEY (`reply_userid`) REFERENCES `account` (`userid`)
   ) comment = 'Q&A 게시물';
   

create table if not exists  notice
(
`id` int NOT NULL AUTO_INCREMENT,
userid VARCHAR(80) not null default 'admin',
title VARCHAR(100) not null,
content VARCHAR(255),
`datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (id),
FOREIGN KEY (userid) REFERENCES account(userid)
)
comment='공지사항 게시글';

CREATE TABLE `news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `originallink` varchar(255) NOT NULL,
  `link` varchar(255) NOT NULL,
  `description` varchar(400) NOT NULL,
  `pubdate` varchar(255) NOT NULL,
  `savetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) COMMENT='news data 저장';