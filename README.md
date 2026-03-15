# Taxi 管理系统（前后端分离）

##目录结构
- admin-backend：Spring Boot 后端
- admin-frontend：Vue3 + Element Plus 管理端

## 后端启动
1. 创建数据库 taxi
2. 修改 admin-backend/src/main/resources/application.properties 中的数据库账号密码
3.运行：
```bash
cd admin-backend
mvn spring-boot:run
```
默认端口：8080

默认管理员：admin /123456

## 前端启动
```bash
cd admin-frontend
npm install
npm run dev
```
访问：http://localhost:5173

##主要接口
- POST /api/auth/login
- GET /api/passengers
- GET /api/drivers
- GET /api/vehicles
- GET /api/orders?status=all|ongoing|completed|canceled
- GET /api/cities
- GET /api/feedback
- GET /api/stats/overview
- GET /api/stats/cities
