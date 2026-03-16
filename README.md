# Taxi 管理系统（前后端分离）

##目录结构
- admin-backend：Spring Boot 后端
- admin-frontend：Vue3 + Element Plus 管理端
- passenger-frontend：Vue3 + Leaflet 乘客端

## 一键启动（后端 + 两个前端）
在项目根目录执行：
```bash
start-all.bat
```
会自动启动：
- 后端（端口：8083）
- 管理端前端（端口：5173）
- 乘客端前端（端口：5174）

默认管理员：admin /123456

## 单独启动后端
1. 创建数据库 taxi
2. 修改 admin-backend/src/main/resources/application.properties 中的数据库账号密码
3.运行：
```bash
cd admin-backend
mvn spring-boot:run
```
默认端口：8083

## 单独启动管理端前端
```bash
cd admin-frontend
npm install
npm run dev
```
访问：http://localhost:5173

## 单独启动乘客端前端
```bash
cd passenger-frontend
npm install
npm run dev
```
访问：http://localhost:5174

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
