# 数据库用户管理系统

一个基于Java Web技术栈开发的用户管理系统，实现了用户注册、登录等基本功能。

## 项目简介

本项目是一个简单的Web应用程序，使用Java Servlet + JSP + MySQL技术栈开发，主要功能包括：

- 用户注册
- 用户登录
- 用户信息管理
- 数据库连接管理

## 技术栈

- **后端**: Java 8, Jakarta Servlet API 6.0, JSP 3.1
- **数据库**: MySQL 8.0
- **构建工具**: Maven 3.x
- **服务器**: 支持Jakarta EE的Web容器（如Tomcat 10+）

## 项目结构

```
DBKS/
├── src/main/
│   ├── java/com/dbks/
│   │   ├── servlet/          # Servlet控制器
│   │   │   ├── LoginServlet.java
│   │   │   ├── RegisterServlet.java
│   │   │   └── SecServlet.java
│   │   └── util/             # 工具类
│   │       └── DbConn.java   # 数据库连接工具
│   └── webapp/               # Web资源
│       ├── Login.jsp         # 登录页面
│       ├── Register.jsp      # 注册页面
│       ├── Success.jsp       # 成功页面
│       ├── css/style.css     # 样式文件
│       └── WEB-INF/web.xml   # Web配置
├── database.sql              # 数据库初始化脚本
└── pom.xml                   # Maven配置文件
```

## 环境要求

- JDK 8 或更高版本
- Maven 3.6 或更高版本
- MySQL 8.0 或更高版本
- 支持Jakarta EE的Web容器（推荐Tomcat 10+）

## 安装与运行

### 1. 克隆项目

```bash
git clone <your-repository-url>
cd DBKS
```

### 2. 数据库配置

1. 启动MySQL服务
2. 执行数据库初始化脚本：

```sql
source database.sql
```

3. 修改数据库连接配置（如需要）：
   编辑 `src/main/java/com/dbks/util/DbConn.java` 文件中的数据库连接参数：

```java
private static final String URL = "jdbc:mysql://localhost:3306/userdb";
private static final String USERNAME = "root";
private static final String PASSWORD = "your_password";
```

### 3. 编译项目

```bash
mvn clean compile
```

### 4. 打包部署

```bash
mvn clean package
```

生成的WAR文件位于 `target/database-assignment-1.0.0.war`

### 5. 部署到Web容器

将生成的WAR文件部署到Tomcat等Web容器中，然后启动服务器。

### 6. 访问应用

打开浏览器访问：`http://localhost:8080/database-assignment-1.0.0/Login.jsp`

## 功能说明

### 用户登录
- 访问路径：`/Login.jsp`
- 支持用户名和密码验证
- 登录成功后跳转到成功页面

### 用户注册
- 访问路径：`/Register.jsp`
- 支持新用户注册
- 自动验证用户名唯一性

### 测试账户

系统预置了以下测试账户：

| 用户名 | 密码 | 邮箱 |
|--------|------|------|
| admin | 123456 | admin@example.com |
| test | test123 | test@example.com |

## 开发说明

### 主要类说明

- `LoginServlet`: 处理用户登录请求
- `RegisterServlet`: 处理用户注册请求
- `SecServlet`: 安全相关的Servlet
- `DbConn`: 数据库连接和操作工具类

### 数据库表结构

```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## 注意事项

1. **安全性**: 当前版本密码以明文存储，生产环境建议使用加密存储
2. **数据库配置**: 请根据实际环境修改数据库连接参数
3. **字符编码**: 项目使用UTF-8编码，确保数据库也使用相同编码

## 贡献

欢迎提交Issue和Pull Request来改进这个项目。

## 许可证

本项目采用MIT许可证，详情请查看LICENSE文件。
