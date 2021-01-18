## spingboot-schedule-job

Spring boot 基于 Quartz 整合定时调度,通过 HTTP 的方式定时调度 API

##项目环境
* mysql 8.x
* spring boot 2.2.x
* jdk 1.8
* mybatis 1.3.2

## 使用
+  克隆项目，执行 sql （resource/sql/schedule_job_table.sql）
+  修改 application-dev.yml 数据库配置
+  启动项，访问 [http://localhost:8080/login](http://localhost:8080/login) 
+  默认用户名 admin 密码 123456

## 登录
![https://raw.githubusercontent.com/byebai95/image/main/springboot-schedule-job-image/1.png](https://raw.githubusercontent.com/byebai95/image/main/springboot-schedule-job-image/1.png)
## 任务管理首页
![https://raw.githubusercontent.com/byebai95/image/main/springboot-schedule-job-image/2.png](https://raw.githubusercontent.com/byebai95/image/main/springboot-schedule-job-image/2.png)
## 任务列表
![https://raw.githubusercontent.com/byebai95/image/main/springboot-schedule-job-image/3.png](https://raw.githubusercontent.com/byebai95/image/main/springboot-schedule-job-image/3.png)
## 任务详情
![https://raw.githubusercontent.com/byebai95/image/main/springboot-schedule-job-image/4.png](https://raw.githubusercontent.com/byebai95/image/main/springboot-schedule-job-image/4.png)
