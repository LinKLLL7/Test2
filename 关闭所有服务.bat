@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion
echo ========================================
echo   校园热点社区系统 - 服务关闭脚本
echo ========================================
echo.

echo 正在关闭所有服务...
echo.

:: 关闭占用端口8081的Java进程（用户服务）
echo [1/5] 关闭用户服务 (端口8081)...
set killed1=0
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8081 ^| findstr LISTENING') do (
    taskkill /f /pid %%a >nul 2>&1
    set killed1=1
    echo   用户服务已关闭 (PID: %%a)
)
if !killed1! equ 0 (
    echo   用户服务未在运行
)

:: 关闭占用端口8082的Java进程（内容服务）
echo [2/5] 关闭内容服务 (端口8082)...
set killed2=0
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8082 ^| findstr LISTENING') do (
    taskkill /f /pid %%a >nul 2>&1
    set killed2=1
    echo   内容服务已关闭 (PID: %%a)
)
if !killed2! equ 0 (
    echo   内容服务未在运行
)

:: 关闭占用端口8083的Java进程（推荐/排序服务）
echo [3/5] 关闭推荐/排序服务 (端口8083)...
set killed3=0
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8083 ^| findstr LISTENING') do (
    taskkill /f /pid %%a >nul 2>&1
    set killed3=1
    echo   推荐/排序服务已关闭 (PID: %%a)
)
if !killed3! equ 0 (
    echo   推荐/排序服务未在运行
)

:: 关闭占用端口8084的Java进程（审核服务）
echo [4/5] 关闭审核服务 (端口8084)...
set killed4=0
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8084 ^| findstr LISTENING') do (
    taskkill /f /pid %%a >nul 2>&1
    set killed4=1
    echo   审核服务已关闭 (PID: %%a)
)
if !killed4! equ 0 (
    echo   审核服务未在运行
)

:: 关闭占用端口8080的Java进程（API网关）
echo [5/5] 关闭API网关 (端口8080)...
set killed5=0
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8080 ^| findstr LISTENING') do (
    taskkill /f /pid %%a >nul 2>&1
    set killed5=1
    echo   API网关已关闭 (PID: %%a)
)
if !killed5! equ 0 (
    echo   API网关未在运行
)

echo.
echo ========================================
echo   所有服务已关闭！
echo ========================================
echo.
echo 验证：以下地址应该无法访问
echo   - http://localhost:8081/swagger-ui/
echo   - http://localhost:8082/swagger-ui/
echo   - http://localhost:8083/swagger-ui/
echo   - http://localhost:8084/swagger-ui/
echo   - http://localhost:8080/api/v1/users/1
echo.
pause