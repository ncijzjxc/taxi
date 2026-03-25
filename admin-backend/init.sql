DROP DATABASE IF EXISTS taxi;
CREATE DATABASE taxi DEFAULT CHARSET utf8mb4;
USE taxi;
SET NAMES utf8mb4;

/* 管理员 */
CREATE TABLE admin (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 username VARCHAR(50) NOT NULL,
 password VARCHAR(100) NOT NULL,
 gender VARCHAR(10),
 birthday DATE,
 role VARCHAR(20),
 create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
 update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

/*乘客（ID 为手动生成的 6 位数） */
CREATE TABLE passenger (
 id BIGINT PRIMARY KEY,
 username VARCHAR(50) NOT NULL UNIQUE,
 name VARCHAR(50),
 phone VARCHAR(20),
 password VARCHAR(100) NOT NULL,
 gender VARCHAR(10),
 birthday DATE,
 status VARCHAR(20),
 create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
 update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

/*司机（ID 为手动生成的 6 位数） */
CREATE TABLE driver (
 id BIGINT PRIMARY KEY,
 username VARCHAR(50) NOT NULL UNIQUE,
 name VARCHAR(50),
 phone VARCHAR(20),
 password VARCHAR(100) NOT NULL,
 gender VARCHAR(10),
 birthday DATE,
 license_no VARCHAR(50),
 car_type VARCHAR(20),
 audit_status VARCHAR(20),
 online_status VARCHAR(20),
 city_id BIGINT,
 create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
 update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

/*车辆 */
CREATE TABLE vehicle (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 plate_no VARCHAR(30),
 model VARCHAR(50),
 status VARCHAR(20),
 driver_id BIGINT
);

/*订单 */
CREATE TABLE orders (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 passenger_id BIGINT,
 driver_id BIGINT,
 vehicle_id BIGINT,
 city_id BIGINT,
 start_addr VARCHAR(255),
 end_addr VARCHAR(255),
 pickup_lat DECIMAL(10,6),
 pickup_lng DECIMAL(10,6),
 dropoff_lat DECIMAL(10,6),
 dropoff_lng DECIMAL(10,6),
 car_type VARCHAR(20),
 distance_km DECIMAL(10,2),
 duration_min INT,
 estimated_fare DECIMAL(10,2),
 order_status INT,
 amount DECIMAL(10,2),
 create_time DATETIME
);

/* 城市 */
CREATE TABLE city (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(50),
 open_status VARCHAR(20),
 operate_status VARCHAR(20),
 create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
 update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

/*反馈 */
CREATE TABLE feedback (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 user_type VARCHAR(20),
 user_id BIGINT,
 type VARCHAR(20),
 content VARCHAR(255),
 status VARCHAR(20),
 create_time DATETIME
);

/* 登录日志 */
CREATE TABLE login_log (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 user_type VARCHAR(20) NOT NULL,
 user_id BIGINT,
 username VARCHAR(50),
 success TINYINT NOT NULL,
 message VARCHAR(255),
 ip VARCHAR(64),
 login_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

/* 乘客/司机「我的订单」按用户隐藏展示，不删除 orders 表 */
CREATE TABLE user_hidden_order (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 user_type VARCHAR(20) NOT NULL COMMENT 'passenger | driver',
 user_id BIGINT NOT NULL,
 order_id BIGINT NOT NULL,
 create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
 UNIQUE KEY uk_user_order (user_type, user_id, order_id)
);

/*计价规则 */
CREATE TABLE price_rule (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 city_id BIGINT NOT NULL,
 car_type VARCHAR(20) NOT NULL,
 start_price DECIMAL(10,2),
 start_km INT,
 price_per_km DECIMAL(10,2),
 price_per_min DECIMAL(10,2),
 version VARCHAR(20),
 status VARCHAR(20) DEFAULT 'enabled',
 effective_time DATETIME,
 create_time DATETIME
);

CREATE TABLE IF NOT EXISTS user_hidden_order (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_type VARCHAR(20) NOT NULL COMMENT 'passenger | driver',
    user_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_user_order (user_type, user_id, order_id)
);

/* admin */
INSERT INTO admin (username,password,gender,birthday,role,create_time,update_time) VALUES
('admin','123456','男','1990-01-01','SUPER',NOW(),NOW()),
('admin2','123456','女','1992-03-12','NORMAL',NOW(),NOW()),
('manager','123456','男','1988-07-18','NORMAL',NOW(),NOW()),
('ops','123456','女','1993-09-08','NORMAL',NOW(),NOW()),
('auditor','123456','男','1991-11-20','NORMAL',NOW(),NOW());

/* passenger（演示数据使用 6 位 ID） */
INSERT INTO passenger (id,username,name,phone,password,gender,birthday,status,create_time,update_time) VALUES
(100001,'p100001','张三','13800000001','123456','男','1998-01-02','normal',NOW(),NOW()),
(100002,'p100002','李四','13800000002','123456','女','1999-03-12','normal',NOW(),NOW()),
(100003,'p100003','王五','13800000003','123456','男','1997-06-20','normal',NOW(),NOW()),
(100004,'p100004','赵六','13800000004','123456','女','1996-09-18','frozen',NOW(),NOW()),
(100005,'p100005','周七','13800000005','123456','男','2000-12-05','normal',NOW(),NOW());

/* driver（演示数据使用 6 位 ID） */
INSERT INTO driver (id,username,name,phone,password,gender,birthday,license_no,car_type,audit_status,online_status,city_id,create_time,update_time) VALUES
(200001,'d200001','司机A','13900000001','123456','男','1992-02-11','A11111','economy','approved','online',1,NOW(),NOW()),
(200002,'d200002','司机B','13900000002','123456','女','1994-04-09','A22222','premium','approved','offline',1,NOW(),NOW()),
(200003,'d200003','司机C','13900000003','123456','男','1991-08-17','A33333','economy','approved','offline',3,NOW(),NOW()),
(200004,'d200004','司机D','13900000004','123456','男','1989-10-23','A44444','luxury','approved','online',6,NOW(),NOW()),
(200005,'d200005','司机E','13900000005','123456','女','1993-05-15','A55555','premium','rejected','offline',7,NOW(),NOW());

/* vehicle */
INSERT INTO vehicle (plate_no,model,status,driver_id) VALUES
('粤B10001','大众','normal',200001),
('粤B10002','丰田','normal',200002),
('粤B10003','本田','maintenance',200003),
('粤B10004','比亚迪','normal',200004),
('粤B10005','特斯拉','normal',200005);

/* orders */
INSERT INTO orders (passenger_id,driver_id,vehicle_id,city_id,start_addr,end_addr,car_type,distance_km,duration_min,estimated_fare,order_status,amount,create_time) VALUES
(100001,200001,1,1,'南山','福田','economy',12.30,28,35.20,4,25.50,NOW()),
(100002,200002,2,1,'宝安','南山','premium',9.10,22,34.10,1,18.00,NOW()),
(100003,200003,3,1,'罗湖','福田','economy',0.00,0,0.00,4,0.00,NOW()),
(100004,200004,4,1,'南山','龙华','luxury',18.50,35,77.30,4,32.00,NOW()),
(100005,200005,5,1,'福田','罗湖','premium',10.20,25,40.50,2,22.50,NOW()),
-- 以下为“待接单”样例，覆盖乘客端选择的城市（3/6/7/8/9），用于验证司机切城不跨城
(100001,NULL,NULL,3,'天安门','望京','economy',8.20,20,28.60,1,28.60,NOW()),
(100002,NULL,NULL,6,'大雁塔','钟楼','premium',6.50,18,31.80,1,31.80,NOW()),
(100003,NULL,NULL,7,'星海广场','大连北站','economy',12.00,28,34.00,1,34.00,NOW()),
(100004,NULL,NULL,8,'中街','沈阳站','premium',7.40,19,30.50,1,30.50,NOW()),
(100005,NULL,NULL,9,'春熙路','天府广场','luxury',5.60,16,45.20,1,45.20,NOW());

/* city */
INSERT INTO city (name,open_status,operate_status,create_time,update_time) VALUES
('深圳','open','running',NOW(),NOW()),
('广州','open','running',NOW(),NOW()),
('北京','open','running',NOW(),NOW()),
('上海','open','paused',NOW(),NOW()),
('杭州','close','paused',NOW(),NOW()),
('西安','open','running',NOW(),NOW()),
('大连','open','running',NOW(),NOW()),
('沈阳','open','running',NOW(),NOW()),
('成都','open','running',NOW(),NOW());

/* feedback */
INSERT INTO feedback (user_type,user_id,type,content,status,create_time) VALUES
('passenger',100001,'suggestion','希望增加优惠券','pending',NOW()),
('passenger',100002,'complaint','司机迟到','processed',NOW()),
('driver',200001,'praise','平台派单效率高','processed',NOW()),
('driver',200002,'suggestion','希望增加油补','pending',NOW()),
('passenger',100003,'praise','服务很好','processed',NOW());

/* price rule */
INSERT INTO price_rule (city_id, car_type, start_price, start_km, price_per_km, price_per_min, version, status, effective_time, create_time) VALUES
(1,'economy',10.00,3,2.00,0.50,'v1','enabled',NOW(),NOW()),
(1,'premium',15.00,3,3.00,0.80,'v1','enabled',NOW(),NOW()),
(1,'luxury',25.00,3,5.00,1.20,'v1','enabled',NOW(),NOW()),
(3,'economy',10.00,3,2.00,0.50,'v1','enabled',NOW(),NOW()),
(3,'premium',15.00,3,3.00,0.80,'v1','enabled',NOW(),NOW()),
(3,'luxury',25.00,3,5.00,1.20,'v1','enabled',NOW(),NOW()),
(6,'economy',10.00,3,2.00,0.50,'v1','enabled',NOW(),NOW()),
(6,'premium',15.00,3,3.00,0.80,'v1','enabled',NOW(),NOW()),
(6,'luxury',25.00,3,5.00,1.20,'v1','enabled',NOW(),NOW()),
(7,'economy',10.00,3,2.00,0.50,'v1','enabled',NOW(),NOW()),
(7,'premium',15.00,3,3.00,0.80,'v1','enabled',NOW(),NOW()),
(7,'luxury',25.00,3,5.00,1.20,'v1','enabled',NOW(),NOW()),
(8,'economy',10.00,3,2.00,0.50,'v1','enabled',NOW(),NOW()),
(8,'premium',15.00,3,3.00,0.80,'v1','enabled',NOW(),NOW()),
(8,'luxury',25.00,3,5.00,1.20,'v1','enabled',NOW(),NOW()),
(9,'economy',10.00,3,2.00,0.50,'v1','enabled',NOW(),NOW()),
(9,'premium',15.00,3,3.00,0.80,'v1','enabled',NOW(),NOW()),
(9,'luxury',25.00,3,5.00,1.20,'v1','enabled',NOW(),NOW());
