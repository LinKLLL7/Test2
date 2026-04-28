@echo off
chcp 65001 >nul
echo ========================================
echo   校园热点社区系统 - 服务启动脚本
echo ========================================
echo.

echo [1/5] 正在启动用户服务 (端口8081)...
start "User-Service" cmd /k "cd /d e:\Computer\School\Vs code\Distributed System\Test2\user-service && mvn spring-boot:run"
timeout /t 8 >nul

echo [2/5] 正在启动内容服务 (端口8082)...
start "Content-Service" cmd /k "cd /d e:\Computer\School\Vs code\Distributed System\Test2\content-service && mvn spring-boot:run"
timeout /t 8 >nul

echo [3/5] 正在启动推荐/排序服务 (端口8083)...
start "Rank-Service" cmd /k "cd /d e:\Computer\School\Vs code\Distributed System\Test2\rank-service && mvn spring-boot:run"
timeout /t 8 >nul

echo [4/5] 正在启动审核服务 (端口8084)...
start "Audit-Service" cmd /k "cd /d e:\Computer\School\Vs code\Distributed System\Test2\audit-service && mvn spring-boot:run"
timeout /t 8 >nul

echo [5/5] 正在启动API网关 (端口8080)...
start "API-Gateway" cmd /k "cd /d e:\Computer\School\Vs code\Distributed System\Test2\api-gateway && mvn spring-boot:run"
timeout /t 5 >nul

echo.
echo ========================================
echo   所有服务已启动！
echo ========================================
echo.
echo 服务地址：
echo   - 用户服务Swagger UI:     http://localhost:8081/swagger-ui/
echo   - 内容服务Swagger UI:     http://localhost:8082/swagger-ui/
echo   - 推荐/排序服务Swagger UI: http://localhost:8083/swagger-ui/
echo   - 审核服务Swagger UI:     http://localhost:8084/swagger-ui/
echo   - API网关:                http://localhost:8080
echo.
echo API网关测试地址：
echo   - 用户信息:   http://localhost:8080/api/v1/users/1
echo   - 帖子详情:   http://localhost:8080/api/v1/posts/1
echo   - 热度榜单:   http://localhost:8080/api/v1/rank/posts?page=1^&size=10
echo   - 举报查询:   http://localhost:8080/api/v1/audit/reports/1
echo.
echo ========================================
echo   提示：请使用"关闭所有服务.bat"脚本来停止所有服务
echo ========================================
pause