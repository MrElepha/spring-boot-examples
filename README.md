# spring-boot-examples
Spring Boot 示例，一些功能模块自我实践和验证

**Struct**

```
├── README.md
├── build.gradle
├── examples Applications demos
│   ├── build.gradle
│   ├── lombok.config
│   ├── main 主 Application
│   │   ├── build
│   │   ├── build.gradle
│   │   └── src
│   └── settings.gradle
├── gradle.properties 编译参数
├── gradlew
├── gradlew.bat
├── libs 项目依赖组件
│   ├── build.gradle
│   ├── settings.gradle
│   └── xxl-job-spring-boot-starter xxl-job 配置
│       ├── build
│       ├── build.gradle
│       └── src
└── settings.gradle
```

**功能验证**

- [x] xxl job
- [ ] rateLimit 限流器
- [ ] elasticsearch
- [ ] lock 分布式锁
- [ ] ... ...

## xxl job

**xxl-job-admin docker 本地部署(含 Mysql & phpmyadmin)**

```shell
# mysql docker 安装
docker pull mysql:latest
docker run -itd --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql:latest

# phpmyadmin 安装
docker pull phpmyadmin/phpmyadmin:latest
docker run --name phpmyadmin -d --link mysql -e PMA_HOST="mysql" -p 6061:80 phpmyadmin/phpmyadmin

# 启动 mysql 和 phpmyadmin
docker start mysql
docker start phpmyadmin

# 登录 phpmyadmin，浏览器输入localhost:6061
# user name & password: root
# 执行 xxl job db 初始化 sql，sql 地址：https://github.com/xuxueli/xxl-job/blob/master/doc/db/tables_xxl_job.sql

# xxl-job admin 部署
docker pull xuxueli/xxl-job-admin:2.4.0
docker run -e PARAMS="--spring.datasource.url=jdbc:mysql://mysql:3306/xxl_job?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai --spring.datasource.username=root --spring.datasource.password=root spring.datasource.driver-class-name=com.mysql.jdbc.Driver --xxl.job.accessToken=test" -p 2222:8080 --name xxl-job-admin -d --link mysql xuxueli/xxl-job-admin:2.4.0

# docker 启动成功后地址为 http://localhost:2222/xxl-job-admin/ 
# username: admin password:123456
```

**验证步骤**
1. 按步骤 run xxl-job-admin docker
2. 在后台「执行器管理」下新增执行器，和 `application.properties` app name 保持一致
3. 在后台「任务管理」下新增 JobHandler，和代码里 @XxlJob 注解注册的 handler 保持一致

## RateLimit

**Redis Docker 部署**
```shell
docker pull redis:7.0
docker run --name redis -p 6379:6379 -d redis:7.0
```