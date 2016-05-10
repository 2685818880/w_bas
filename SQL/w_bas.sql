
create database w_bas;
use w_bas;

create table w_bas_project(
project_id  int identity(1,1) primary key,
proNo varchar(20) unique not null ,
proName varchar(20) unique not null
)
insert into w_bas_project values('00000','ͨ����Ŀ');
insert into w_bas_project values('00219','�Ϸʶ�����');
insert into w_bas_project values('00233','�ں���ҵ��');
insert into w_bas_project values('00275','����ҩҵһ��');
insert into w_bas_project values('00277','տ���̲�');
insert into w_bas_project values('00279','�ߺ����');
insert into w_bas_project values('00282','�����ɰ״�');
insert into w_bas_project values('00290','�ൺ������̥');
insert into w_bas_project values('00295','����һ��');
insert into w_bas_project values('00304','Ӻ����Դ');
insert into w_bas_project values('00314','313����');
insert into w_bas_project values('00315','������ɽ');
insert into w_bas_project values('00318','ɽ����ʿ��');
insert into w_bas_project values('00319','���ݽ���');

create table w_bas_team(
team_id  int identity(1,1) primary key,
teamNo varchar(20) unique not null,
teamName varchar(20)unique not null
)
insert into w_bas_team values('11111','������̲�');
insert into w_bas_team values('22222','��ƹ��̲�');
insert into w_bas_team values('33333','�������̲�');

create table w_status(
statusNo int  primary key,
statusName varchar(200)
)
insert into w_status values(0,'��ע����ͨ�û� or δ���');
insert into w_status values(1,'��ע������Ա�û� or �����');

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

insert into w_user values('test','������','13771513891','test',0,1);
select * from w_user;

insert into w_user values('admin','������','13898773322','admin',1,1);
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



