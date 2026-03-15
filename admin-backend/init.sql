CREATE DATABASE IF NOT EXISTS taxi DEFAULT CHARSET utf8mb4;
USE taxi;

-- 管理员
CREATE TABLE admin (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 username VARCHAR(50) NOT NULL,
 password VARCHAR(100) NOT NULL,
 role VARCHAR(20)
);

--乘客
CREATE TABLE passenger (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(50),
 phone VARCHAR(20),
 register_time DATETIME,
 status VARCHAR(20)
);

--司机
CREATE TABLE driver (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(50),
 phone VARCHAR(20),
 license_no VARCHAR(50),
 audit_status VARCHAR(20),
 online_status VARCHAR(20)
);

--车辆
CREATE TABLE vehicle (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 plate_no VARCHAR(30),
 model VARCHAR(50),
 status VARCHAR(20),
 driver_id BIGINT
);

--订单
CREATE TABLE orders (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 passenger_id BIGINT,
 driver_id BIGINT,
 vehicle_id BIGINT,
 start_addr VARCHAR(255),
 end_addr VARCHAR(255),
 order_status VARCHAR(20),
 amount DECIMAL(10,2),
 create_time DATETIME
);

-- 城市
CREATE TABLE city (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(50),
 open_status VARCHAR(20),
 operate_status VARCHAR(20)
);

--反馈
CREATE TABLE feedback (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 user_type VARCHAR(20),
 user_id BIGINT,
 type VARCHAR(20),
 content VARCHAR(255),
 status VARCHAR(20),
 create_time DATETIME
);

-- admin
INSERT INTO admin (username,password,role) VALUES
('admin','123456','SUPER'),
('admin2','123456','NORMAL'),
('manager','123456','NORMAL'),
('ops','123456','NORMAL'),
('auditor','123456','NORMAL');

-- passenger
INSERT INTO passenger (name,phone,register_time,status) VALUES
('张三','13800000001',NOW(),'normal'),
('李四','13800000002',NOW(),'normal'),
('王五','13800000003',NOW(),'normal'),
('赵六','13800000004',NOW(),'frozen'),
('周七','13800000005',NOW(),'normal');

-- driver
INSERT INTO driver (name,phone,license_no,audit_status,online_status) VALUES
('司机A','13900000001','A11111','approved','online'),
('司机B','13900000002','A22222','approved','offline'),
('司机C','13900000003','A33333','pending','offline'),
('司机D','13900000004','A44444','approved','online'),
('司机E','13900000005','A55555','rejected','offline');

-- vehicle
INSERT INTO vehicle (plate_no,model,status,driver_id) VALUES
('粤B10001','大众','normal',1),
('粤B10002','丰田','normal',2),
('粤B10003','本田','maintenance',3),
('粤B10004','比亚迪','normal',4),
('粤B10005','特斯拉','normal',5);

-- orders
INSERT INTO orders (passenger_id,driver_id,vehicle_id,start_addr,end_addr,order_status,amount,create_time) VALUES
(1,1,1,'南山','福田','completed',25.50,NOW()),
(2,2,2,'宝安','南山','ongoing',18.00,NOW()),
(3,3,3,'罗湖','福田','canceled',0.00,NOW()),
(4,4,4,'南山','龙华','completed',32.00,NOW()),
(5,5,5,'福田','罗湖','ongoing',22.50,NOW());

-- city
INSERT INTO city (name,open_status,operate_status) VALUES
('深圳','open','running'),
('广州','open','running'),
('北京','open','running'),
('上海','open','paused'),
('杭州','close','paused');

-- feedback
INSERT INTO feedback (user_type,user_id,type,content,status,create_time) VALUES
('passenger',1,'suggestion','希望增加优惠券','pending',NOW()),
('passenger',2,'complaint','司机迟到','processed',NOW()),
('driver',1,'praise','平台派单效率高','processed',NOW()),
('driver',2,'suggestion','希望增加油补','pending',NOW()),
('passenger',3,'praise','服务很好','processed',NOW());
