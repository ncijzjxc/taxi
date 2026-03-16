@echo off
setlocal

cd /d %~dp0

start "backend" cmd /k "cd admin-backend && mvn spring-boot:run"
start "admin-frontend" cmd /k "cd admin-frontend && npm run dev"
start "passenger-frontend" cmd /k "cd passenger-frontend && npm run dev"

echo Backend, admin-frontend and passenger-frontend are starting...
