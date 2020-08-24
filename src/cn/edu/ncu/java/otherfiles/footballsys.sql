-- 创建表
-- 球队基本信息表
create table team(
    name varchar(20) primary key,
    college varchar(30) default null,
    coach varchar(10),
    setTime timestamp
);
-- 球员基本信息表
create table player(
	name varchar(20),
	age int,
	team varchar(20),
	numbers varchar(20),
	gender varchar(20),
	position varchar(20) check(position in('守门员','后卫','前卫','前锋')),
	height varchar(20),
	weight varchar(20),
	birthdate timestamp,
	primary key(team,numbers)
);
-- 比赛日程表
create table schedule(
	time timestamp,
	hometeam varchar(20),
	visitingteam varchar(20),
	homescore  int not null,
  	visitingscore  int not null,
  	turnone int not null,
  	turntwo varchar(1) not null,
  	unique(hometeam,visitingteam)
);
-- 球队积分表
create table scoreofteam(
 	ranking int not null default 0,
 	changes int not null default 0,
 	teamname varchar(20) primary key,
 	matchs int not null,
 	win int not null,
 	even int not null,
 	beaten int not null,
	goal int not null,
	lost int not null,
	net int not null,
	avergoal float not null,
	averlost float not null,
	avernet float not null,
	averpoint float not null
);
-- 球员进球记录表
create table goalofplayer(
	teamname varchar(20) not null,
	numbers varchar(20) not null,
	turn int not null,
	goaltime timestamp not null,
	primary key (teamname,numbers,turn,goaltime)
);
-- 裁判惩罚记录表
create table judge(
	teamname varchar(20) not null,
	numbers varchar(20) not null,
	redcard int not null,
	yellowcard int not null,
	turn int not null,
	judgetime timestamp not null,
	primary key(teamname,numbers,redcard,yellowcard,turn,judgetime)
);
-- 射手榜
create table shooterlist(
	ranking int not null default 0,
	name varchar(20) not null,
	teamname  varchar(20),
	numbers varchar(20),
	goals int not null,
	redcard int not null,
	yellowcard int not null,
	primary key(teamname,numbers)
);
