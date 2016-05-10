
create database w_bas;
use w_bas;

create table w_bas_project(
project_id  int identity(1,1) primary key,
proNo varchar(20) unique not null ,
proName varchar(20) unique not null
)
insert into w_bas_project values('00000','通用项目');
insert into w_bas_project values('00219','合肥东陵旭');
insert into w_bas_project values('00233','乌海电业局');
insert into w_bas_project values('00275','海正药业一期');
insert into w_bas_project values('00277','湛江烟草');
insert into w_bas_project values('00279','芜湖软控');
insert into w_bas_project values('00282','温州纳白川');
insert into w_bas_project values('00290','青岛赛轮轮胎');
insert into w_bas_project values('00295','长春一汽');
insert into w_bas_project values('00304','雍州绿源');
insert into w_bas_project values('00314','313增补');
insert into w_bas_project values('00315','重庆青山');
insert into w_bas_project values('00318','山东佳士博');
insert into w_bas_project values('00319','广州交运');

create table w_bas_team(
team_id  int identity(1,1) primary key,
teamNo varchar(20) unique not null,
teamName varchar(20)unique not null
)
insert into w_bas_team values('11111','软件工程部');
insert into w_bas_team values('22222','设计工程部');
insert into w_bas_team values('33333','电器工程部');

create table w_status(
statusNo int  primary key,
statusName varchar(200)
)
insert into w_status values(0,'备注：普通用户 or 未审核');
insert into w_status values(1,'备注：管理员用户 or 已审核');

create table w_user
(
userNo  int identity(10000,1) primary key,  
userName varchar(20) not null unique,
realName varchar(20),
realIpone varchar(100),
passWord varchar(20) ,
statusNo int not null,
foreign key(statusNo)references w_status(statusNo),
team_id int not null,
foreign key(team_id)references w_bas_team(team_id)

)

insert into w_user values('test','徐先生','13771513891','test',0,1);
select * from w_user;

insert into w_user values('admin','徐先生','13898773322','admin',1,1);
select * from w_user;

create table w_bas_bx(
bx_id  int identity(1,1) primary key,
bxNo varchar(20) unique not null ,
userNo int not null,
foreign key(userNo)references w_user(userNo),
project_id int not null,
foreign key(project_id)references w_bas_project(project_id),
team_id int not null,
foreign key(team_id)references w_bas_team(team_id),
bxStart datetime,
bxBack  datetime,
bxDay int,
bxTime  datetime,
carSubsidy float,
mealSubsidy float,
sleepSubsidy float,
otherSubsidy float,
sumSubsidy float,
handler varchar(20) not null,
handlertime datetime,
statusNo int not null,
foreign key(statusNo)references w_status(statusNo)
)


insert into w_bas_bx values('G160225150027',10000,1,1,'2016-01-01 00:00:00.000','2016-04-01 00:00:00.000',3,
'2016-04-01 00:00:00.000',300,1000,2000,1500,3800,'test','2016-04-01 00:00:00.000',0);

--create view viewname as select statement   
--drop view viewname  



