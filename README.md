# 校园热点社区微服务系统

基于 Spring Boot + Spring Cloud 构建的校园热点社区微服务系统，采用微服务架构设计，包含用户服务、内容服务、推荐排序服务、审核服务及 API 网关。

## 技术栈

- **语言**: Java 8
- **框架**: Spring Boot 2.7.15
- **微服务**: Spring Cloud 2021.0.8
- **网关**: Spring Cloud Gateway
- **API文档**: Springfox Swagger 3.0.0
- **契约测试**: Spring Cloud Contract

## 项目结构

```
campus-community/
├── api-gateway/          # API网关服务 (端口: 8080)
├── user-service/         # 用户服务 (端口: 8081)
├── content-service/      # 内容服务 (端口: 8082)
├── rank-service/         # 推荐/排序服务 (端口: 8083)
├── audit-service/        # 审核服务 (端口: 8084)
├── 启动所有服务.bat      # Windows一键启动脚本
├── 关闭所有服务.bat      # Windows一键关闭脚本
└── pom.xml               # Maven父工程配置
```

## 服务职责

| 服务 | 端口 | 职责描述 |
|:---|:---|:---|
| **api-gateway** | 8080 | 统一入口、路由转发、负载均衡 |
| **user-service** | 8081 | 用户注册、登录、信息管理 |
| **content-service** | 8082 | 发帖、删帖、评论、点赞、举报 |
| **rank-service** | 8083 | 热度榜单、推荐排序 |
| **audit-service** | 8084 | 举报处理、内容审核 |

## 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.6+

### 编译项目

编译命令用于将项目代码打包成可执行的 JAR 文件。当你修改了代码后，需要重新编译才能让修改生效。

```bash
cd campus-community
mvn clean package -DskipTests
```

### 启动服务

**方式一：使用批处理脚本（推荐）**

直接双击运行脚本，一键启动/关闭所有服务，无需手动输入命令：

```bash
# 启动所有服务
双击运行 "启动所有服务.bat"

# 关闭所有服务
双击运行 "关闭所有服务.bat"
```

**方式二：使用 Maven 直接运行（开发调试用）**

无需提前编译，Maven 会自动编译并运行：

```bash
# 启动 API网关
cd api-gateway
mvn spring-boot:run

# 启动用户服务
cd user-service
mvn spring-boot:run

# 启动内容服务
cd content-service
mvn spring-boot:run

# 启动推荐服务
cd rank-service
mvn spring-boot:run

# 启动审核服务
cd audit-service
mvn spring-boot:run
```

## API 接口

### 访问地址

| 服务 | Swagger文档 | 接口前缀 |
|:---|:---|:---|
| API网关 | http://localhost:8080/swagger-ui/ | /api/v1/** |
| 用户服务 | http://localhost:8081/swagger-ui/ | /api/v1/users/** |
| 内容服务 | http://localhost:8082/swagger-ui/ | /api/v1/posts/** |
| 推荐服务 | http://localhost:8083/swagger-ui/ | /api/v1/rank/** |
| 审核服务 | http://localhost:8084/swagger-ui/ | /api/v1/audit/** |

### 核心接口示例

#### 用户服务
- `POST /api/v1/users/register` - 用户注册
- `POST /api/v1/users/login` - 用户登录
- `GET /api/v1/users/{id}` - 获取用户信息

#### 内容服务
- `POST /api/v1/posts` - 发帖
- `GET /api/v1/posts/{id}` - 获取帖子详情
- `DELETE /api/v1/posts/{id}` - 删帖
- `POST /api/v1/posts/{id}/like` - 点赞
- `POST /api/v1/posts/{id}/comments` - 评论
- `POST /api/v1/reports` - 举报

#### 推荐服务
- `GET /api/v1/rank/posts` - 热度榜单

#### 审核服务
- `GET /api/v1/audit/reports/{id}` - 查询举报状态
- `POST /api/v1/audit/handle` - 处理举报

## API 网关路由配置

网关将请求路由至对应服务：

| 路径 | 目标服务 |
|:---|:---|
| `/api/v1/users/**` | user-service (8081) |
| `/api/v1/posts/**` | content-service (8082) |
| `/api/v1/reports` | content-service (8082) |
| `/api/v1/rank/**` | rank-service (8083) |
| `/api/v1/audit/**` | audit-service (8084) |

## 测试

### 契约测试

用户服务包含 Spring Cloud Contract 契约测试：

```bash
cd user-service
mvn test
```

### 接口测试

通过 Swagger UI 访问各服务文档，进行在线接口测试。

## 架构特点

- **服务拆分**: 按业务边界拆分，职责单一
- **RESTful API**: 接口设计符合 REST 规范
- **内存存储**: 使用 ConcurrentHashMap 实现数据存储
- **服务隔离**: 单个服务故障不影响其他服务
- **独立部署**: 支持各服务独立开发、部署、升级

## 目录结构

```
├── api-gateway/
│   └── src/main/
│       ├── java/com/campus/gateway/ApiGatewayApplication.java
│       └── resources/application.yml
├── user-service/
│   └── src/main/
│       ├── java/com/campus/user/
│       │   ├── UserServiceApplication.java
│       │   └── controller/UserController.java
│       └── resources/application.yml
├── content-service/
│   └── src/main/
│       ├── java/com/campus/content/
│       │   ├── ContentServiceApplication.java
│       │   └── controller/ContentController.java
│       └── resources/application.yml
├── rank-service/
│   └── src/main/
│       ├── java/com/campus/rank/
│       │   ├── RankServiceApplication.java
│       │   └── controller/RankController.java
│       └── resources/application.yml
└── audit-service/
    └── src/main/
        ├── java/com/campus/audit/
        │   ├── AuditServiceApplication.java
        │   └── controller/AuditController.java
        └── resources/application.yml
```

## 许可证

MIT License