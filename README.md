# JavaService

Spring Boot 3 + MySQL 的电商示例后端，提供用户认证、商品、订单、Banner 等接口，供 `IosAppFlutter` 客户端调用。

## 项目定位

- 技术栈：Java 17、Spring Boot 3.1、Spring Web、Spring Data JPA、Spring Security、JWT、MySQL
- 主要模块：认证、用户、商品、订单、Banner
- 运行端口：`8080`
- 代码入口：`src/main/java/com/example/crud/CrudApplication.java`

## 当前能力

- `POST /api/auth/login` 登录
- `POST /api/auth/register` 注册
- `GET /api/products` 商品分页
- `GET /api/products/all` 商品全量列表
- `GET /api/products/search` 商品搜索
- `GET /api/products/categories` 商品分类
- `GET/POST/PUT/DELETE /api/orders/**` 用户订单
- `GET/POST/PUT/DELETE /api/banners/**` Banner 管理
- `GET/PUT/DELETE /api/users/**` 用户管理
- 统一错误响应：`timestamp/status/error/message/path`

## 本地运行

### 1. 准备环境

- JDK 17
- Maven 3.9+
- MySQL 8.x

### 2. 初始化数据库

默认配置位于 `src/main/resources/application.properties`：

- 数据库：`crud_db`
- 用户名：`root`
- 端口：`3306`
- 可用环境变量覆盖：`DB_URL`、`DB_USERNAME`、`DB_PASSWORD`、`JWT_SECRET`、`JWT_EXPIRATION_MS`

可参考以下文件：

- `database/init.sql`
- `database/import_data.sh`
- `database/export_data.sh`
- `database/data/dump.sql`

### 3. 启动项目

```bash
mvn spring-boot:run
```

或：

```bash
./run.sh
```

如果你希望加载开发种子数据，请显式使用 `dev` profile：

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

`run.sh` 和 `run_home.sh` 当前都会默认以 `dev` profile 启动。

### 4. 默认种子数据

仅在 `dev` profile 下会自动补种数据：

- 默认管理员：`admin / admin123`
- 默认 Banner：3 条
- 默认商品：不足 2000 条时会清空并重新写入

对应实现见 `src/main/java/com/example/crud/config/DataSeeder.java`。

## 与 Flutter 客户端联调

- 客户端默认请求地址配置在 `IosAppFlutter/lib/config/api_config.dart`
- 当前写死为一个局域网 IP，联调前通常需要改成你当前机器可访问的地址

## 快速体检结论

这套后端适合作为学习和原型验证项目，目前核心 CRUD 与 JWT 登录流程已经接起来了，但安全性和生产可用性还差一层，尤其是配置暴露、权限边界和数据初始化策略。

## 已发现的明显风险

### 1. 明文敏感配置直接提交在仓库

- `application.properties` 中直接保存了数据库账号密码和 JWT secret
- 风险：泄露后可直接连库或伪造令牌
- 建议：改为环境变量或 `application-local.properties` 本地覆盖

### 2. CORS 过宽

- 当前配置允许 `*`
- 风险：前端来源不受限制，不适合上线
- 建议：按环境限制为明确域名列表

### 3. 用户管理和内容管理接口只做“已登录”校验，没有做“管理员”校验

- `/api/users/**`、商品写接口、Banner 写接口都没有基于角色做服务端授权
- 风险：任意普通登录用户都可能调用这些管理接口
- 建议：基于 `role=ADMIN` 增加方法级或路由级鉴权

### 4. 开发环境下仍会自动重建商品数据

- 当商品数量小于 2000 时会先 `deleteAll()` 再重新灌入
- 当前已限制为仅在 `dev` profile 生效，但开发库里手工录入的数据仍可能被覆盖
- 建议：后续再改成幂等补种，避免开发环境误删数据

### 5. 缺少自动化测试

- 当前 `mvn test` 可通过，但项目里没有实际测试用例
- 风险：接口行为回归没有保护网
- 建议：至少补登录、订单、权限边界和商品搜索的集成测试

## 本轮验证结果

- `mvn test`：通过
- 当前已补最小权限回归测试，覆盖登录、匿名访问被拒绝、普通用户访问管理员接口被拒绝、管理员访问管理员接口通过
- 说明：仍然缺少订单、商品搜索、异常分支等更完整的集成测试

## 推荐下一步

- 先处理配置外置和管理员权限控制
- 再补最基础的接口测试
- 最后把开发种子逻辑改成幂等补种
