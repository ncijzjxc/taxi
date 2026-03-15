@echo off
setlocal

cd /d %~dp0

start "backend" cmd /k "cd admin-backend && mvn spring-boot:run"
start "frontend" cmd /k "cd admin-frontend && npm run dev"

echo Backend and frontend are starting...
