drop database if exists turingc;
create database turingc;
use turingc;
-- 创建tb_user表
create table tb_user
(
    -- id 主键
    id int primary key auto_increment,
    -- 工号
    account varchar(8),
    -- 姓名
    name varchar(8),
    -- 性别
    sex varchar(1),
    -- 密码
    password varchar(8),
    -- 基础工资
    basic_salary int,
    -- 是否在职
    is_job varchar(2),
    -- 头像
    face varchar(15)
);
-- 添加数据
insert into tb_user(account, name, sex, password,basic_salary, is_job, face)
VALUES ('00000000','张一','女','11111111',5000,'在职','img/female.png'),
       ('00000001','张二','男','11111111',8400,'在职','img/male.png'),
       ('00000002','张三','男','11111111',7000,'在职','img/male.png'),
       ('00000003','张四','女','11111111',5000,'在职','img/female.png'),
       ('00000004','张五','女','11111111',5800,'在职','img/female.png'),
       ('00000005','李一','男','11111111',6000,'在职','img/male.png'),
       ('00000006','李二','女','11111111',5600,'在职','img/female.png'),
       ('00000007','李三','男','11111111',7300,'在职','img/male.png'),
       ('00000008','李四','女','11111111',5100,'在职','img/female.png'),
       ('00000009','李五','女','11111111',8000,'在职','img/female.png'),
       ('00000010','李六','男','11111111',5300,'在职','img/male.png'),
       ('00000011','陆一','女','11111111',8000,'在职','img/female.png'),
       ('00000012','陆二','男','11111111',9600,'在职','img/male.png'),
       ('00000013','陆三','女','11111111',5400,'在职','img/female.png'),
       ('00000014','何一','女','11111111',5300,'在职','img/female.png'),
       ('00000015','何二','女','11111111',5000,'在职','img/female.png'),
       ('00000016','何三','男','11111111',3400,'在职','img/male.png'),
       ('00000017','何四','男','11111111',7500,'在职','img/male.png'),
       ('00000018','何五','男','11111111',5000,'在职','img/male.png'),
       ('00000019','何六','女','11111111',5500,'在职','img/female.png'),
       ('00000020','何七','男','11111111',5400,'在职','img/male.png'),
       ('00000021','何八','女','11111111',5700,'在职','img/female.png'),
       ('00000022','周一','男','11111111',6700,'在职','img/male.png'),
       ('00000023','周二','男','11111111',7700,'离职','img/male.png'),
       ('00000024','周三','男','11111111',9500,'在职','img/male.png'),
       ('00000025','周四','女','11111111',10000,'在职','img/female.png'),
       ('00000026','周五','女','11111111',5400,'在职','img/female.png'),
       ('00000027','周六','男','11111111',6700,'在职','img/male.png'),
       ('00000028','周七','女','11111111',4300,'在职','img/female.png'),
       ('00000029','周八','男','11111111',5800,'在职','img/male.png'),
       ('00000030','陈一','女','11111111',3700,'离职','img/female.png'),
       ('00000031','陈二','男','11111111',4000,'在职','img/male.png'),
       ('00000032','陈三','男','11111111',7600,'在职','img/male.png'),
       ('00000033','陈四','女','11111111',5000,'在职','img/female.png'),
       ('00000034','陈五','男','11111111',2800,'在职','img/male.png'),
       ('00000035','陈六','女','11111111',8400,'在职','img/female.png'),
       ('00000036','徐一','女','11111111',8000,'在职','img/female.png'),
       ('00000037','徐二','男','11111111',6300,'在职','img/male.png'),
       ('00000038','赵一','女','11111111',9400,'离职','img/female.png'),
       ('00000039','赵二','女','11111111',5300,'在职','img/female.png'),
       ('00000040','赵二','女','11111111',4200,'在职','img/female.png'),
       ('00000041','赵三','男','11111111',6200,'在职','img/male.png'),
       ('00000042','黄一','女','11111111',5700,'在职','img/female.png'),
       ('00000043','黄二','男','11111111',5700,'在职','img/male.png'),
       ('00000044','黄三','男','11111111',7000,'在职','img/male.png'),
       ('00000045','黄四','女','11111111',7000,'在职','img/female.png'),
       ('00000046','黄五','男','11111111',9600,'在职','img/male.png'),
       ('00000047','黄六','女','11111111',5700,'在职','img/female.png'),
       ('00000048','麦一','女','11111111',5400,'在职','img/female.png'),
       ('00000049','麦二','男','11111111',4700,'在职','img/male.png'),
       ('00000050','麦三','女','11111111',8500,'在职','img/female.png'),
       ('00000051','谢一','男','11111111',8900,'在职','img/male.png'),
       ('00000052','谢二','女','11111111',4700,'在职','img/female.png'),
       ('00000053','谢三','男','11111111',5800,'在职','img/male.png');
-- 创建tb_admin表0,
create table tb_admin
(
    -- id 主键
    id  int primary key auto_increment,
    -- 工号
    account varchar(8),
    -- 姓名
    name  varchar(8),
    -- 密码
    password varchar(8)
);
-- 添加数据
insert into tb_admin(account, name, password)
VALUES ('11111111','管理张三','11111111');

-- 创建tb_notice表
create table tb_notice
(
    -- id 主键
    id  int primary key auto_increment,
    -- 标题
    title varchar(20),
    -- 内容
    text varchar(50),
    -- 状态
    state varchar(2),
    -- 日期
    date varchar(15)
);
-- 添加数据
insert into tb_notice(title, text, state, date)
VALUES ('会议通知','本周三下午两点会议室开会','普通','2024-02-03'),
       ('放假通知','放假时间截至到2月18日','普通','2024-02-03'),
       ('喜报','今年目标超额完成！！','普通','2024-01-02'),
       ('迎元旦','祝大家元旦快乐','普通','2024-01-01'),
       ('迎新春','祝大家新春快乐','普通','2024-02-10'),
       ('停电通知','2月27日下午3电停电，预计停电2小时','置顶','2024-02-21'),
       ('会议通知','明天下午两点会议室开会','普通','2024-02-28');

-- 创建tb_leave表
create table tb_leave
(
    -- id 主键
    id  int primary key auto_increment,
    -- 员工id
    user_id int,
    -- 员工姓名
    name varchar(8),
    -- 员工工号
    account varchar(8),
    -- 性别
    sex varchar(1),
    -- 原因
    reason varchar(20),
    -- 头像
    face varchar(15),
    -- 起始日期
    start_date varchar(10),
    -- 终止日期
    last_date varchar(10),
    -- 状态（离职申请，请假申请，请假拒绝，请假同意）
    state varchar(4)
);
-- 添加数据
insert into tb_leave(user_id, name, account, sex, reason, face, start_date, last_date, state)
VALUES (34,'陈四','00000033','女','家事','img/female.png','2024-01-02','2024-01-03','请假同意'),
       (34,'陈四','00000033','女','生病','img/female.png','2024-01-22','2024-01-26','请假同意'),
       (19,'何五','00000018','男','生病','img/male.png','2024-02-11','2024-02-11','请假同意'),
       (40,'赵二','00000039','女','体检','img/female.png','2024-02-03','2024-02-03','请假同意'),
       (25,'周三','00000024','男','搬家','img/male.png','2024-01-17','2024-01-17','请假同意'),
       (50,'麦二','00000049','男','复阳','img/male.png','2024-01-23','2024-01-26','请假同意'),
       (19,'何五','00000018','男','家事','img/male.png','2024-02-23','2024-02-29','请假申请'),
       (53,'谢二','00000052','女','看病','img/female.png','2024-02-18','2024-02-18','请假申请'),
       (19,'何五','00000018','男','养病','img/male.png','2024-02-28','','离职申请');

-- 创建tb_activity_date表
create table tb_activity_data
(
    -- id 主键
    id  int primary key auto_increment,
    -- 活动id
    activity_id int,
    -- 员工id
    user_id int,
    -- 头像
    face varchar(15),
    -- 员工工号
    account varchar(8),
    -- 员工姓名
    name varchar(8),
    -- 成绩
    score int
);
-- 添加数据
insert into tb_activity_data(activity_id, user_id, face, account, name, score)
VALUES (1,1,'img/female.png','00000000','张一',0),
       (1,50,'img/male.png','00000049','麦二',0),
       (1,42,'img/male.png','00000041','赵三',0),
       (1,14,'img/female.png','00000013','陆三',0),
       (1,5,'img/male.png','00000004','张五',0),
       (3,41,'img/female.png','00000040','赵二',74),
       (3,19,'img/male.png','00000018','何五',66),
       (4,15,'img/female.png','00000014','何一',88),
       (4,6,'img/male.png','00000005','李一',96),
       (4,54,'img/male.png','00000053','谢三',50);

-- 创建tb_activity表
create table tb_activity
(
    -- id 主键
    id  int primary key auto_increment,
    -- 参加人数
    number int,
    -- 活动名称
    name varchar(15),
    -- 活动简介
    brief varchar(50),
    -- 日期
    date varchar(10),
    -- 状态
    state varchar(2)
);
-- 添加数据
insert into tb_activity(number, name, brief, date, state)
VALUES (5,'团队培训','培养团队协作','2024-03-16','待办'),
       (0,'专业培训','培训专业技能','2024-03-24','待办'),
       (2,'个人培训','培训个人能力','2024-02-02','结束'),
       (3,'技能培训','培训专业技能','2024-01-23','结束');

-- 创建tb_salary表
create table tb_salary
(
    -- id 主键
    id  int primary key auto_increment,
    -- 员工id
    user_id int,
    -- 工资
    salary int,
    -- 基础工资
    basic_salary int,
    -- 迟到次数
    late_time int,
    -- 早退次数
    leave_early_time int,
    -- 缺勤次数
    absence_time int,
    -- 请假次数
    leave_time int,
    -- 日期
    date varchar(10)
);
-- 添加数据
insert into tb_salary(user_id, basic_salary, salary, late_time, leave_early_time, absence_time, leave_time, date)
VALUES (1,5000,5490,1,0,0,0,'2024-01'),
       (2,8400,8900,0,0,0,0,'2024-01'),
       (3,7000,7500,0,0,0,0,'2024-01'),
       (4,5000,5500,0,0,0,0,'2024-01'),
       (5,5800,6300,0,0,0,0,'2024-01'),
       (6,6000,6500,0,0,0,0,'2024-01'),
       (7,5600,7100,0,0,0,0,'2024-01'),
       (8,7300,7800,0,0,0,0,'2024-01'),
       (9,5100,5600,0,0,0,0,'2024-01'),
       (10,8000,8500,0,0,0,0,'2024-01'),
       (11,9600,11000,0,0,0,0,'2024-01'),
       (12,5400,5900,0,0,0,0,'2024-01'),
       (1,5000,5500,0,0,0,0,'2024-02'),
       (2,8400,8900,0,0,0,0,'2024-02'),
       (3,7000,7500,0,0,0,0,'2024-02'),
       (4,5000,5500,0,0,0,0,'2024-02'),
       (5,5800,6300,0,0,0,0,'2024-02'),
       (6,6000,6950,0,0,1,0,'2024-02'),
       (7,5600,7100,0,0,0,0,'2024-02'),
       (8,7300,7800,0,0,0,0,'2024-02'),
       (9,5100,5600,0,0,0,0,'2024-02');

-- 创建tb_work表
create table tb_work
(
    -- id 主键
    id  int primary key auto_increment,
    -- 员工id
    user_id int,
    -- 员工姓名
    name varchar(8),
    -- 头像
    face varchar(15),
    -- 性别
    sex varchar(1),
    -- 账号
    account varchar(8),
    -- 类型
    type varchar(2),
    -- 日期
    date varchar(10)
);
-- 添加数据
insert into tb_work(user_id, name, face, sex, account, type, date)
VALUES (1,'张一','img/female.png','女','00000000','迟到','2024-01-02'),
       (6,'李二','img/female.png','女','00000006','缺勤','2024-02-09'),
       (52,'谢一','img/male.png','男','00000048','迟到','2024-02-09'),
       (49,'麦一','img/female.png','女','00000048','早退','2024-02-13');
