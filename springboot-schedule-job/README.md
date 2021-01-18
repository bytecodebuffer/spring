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

## 项目依赖
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.5.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>schedule-job</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <java.version>1.8</java.version>
        <cn.hutool.hutool-all.version>5.0.7</cn.hutool.hutool-all.version>
        <tk.mybatis.version>2.1.5</tk.mybatis.version>
        <mybatis.version>1.3.2</mybatis.version>
        <pagehelper.version>1.2.10</pagehelper.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- freemarker -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-freemarker</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>
        <!-- quartz -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-quartz</artifactId>
        </dependency>
        <!-- mybatis -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>${mybatis.version}</version>
        </dependency>
        <!-- tk.mybatis -->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper-spring-boot-starter</artifactId>
            <version>${tk.mybatis.version}</version>
        </dependency>
        <!-- 分页插件 -->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>${pagehelper.version}</version>
        </dependency>
        <!-- mysql -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!-- lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <!-- hutool -->
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>${cn.hutool.hutool-all.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

    <build>
        <finalName>schedule-job</finalName>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <!-- freemarker热启动 -->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                </configuration>
            </plugin>
            <!-- 解决前端部分font无法显示问题 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
                <configuration>
                    <nonFilteredFileExtensions>
                        <nonFilteredFileExtension>ttf</nonFilteredFileExtension>
                        <nonFilteredFileExtension>woff</nonFilteredFileExtension>
                        <nonFilteredFileExtension>woff2</nonFilteredFileExtension>
                    </nonFilteredFileExtensions>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```
## 登录
![https://raw.githubusercontent.com/byebai95/image/main/springboot-schedule-job-image/1.png](https://raw.githubusercontent.com/byebai95/image/main/springboot-schedule-job-image/1.png)
## 任务管理首页
![https://raw.githubusercontent.com/byebai95/image/main/springboot-schedule-job-image/2.png](https://raw.githubusercontent.com/byebai95/image/main/springboot-schedule-job-image/2.png)
## 任务列表
![https://raw.githubusercontent.com/byebai95/image/main/springboot-schedule-job-image/3.png](https://raw.githubusercontent.com/byebai95/image/main/springboot-schedule-job-image/3.png)
## 任务详情
![https://raw.githubusercontent.com/byebai95/image/main/springboot-schedule-job-image/4.png](https://raw.githubusercontent.com/byebai95/image/main/springboot-schedule-job-image/4.png)
