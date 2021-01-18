## Spring

* demo-kafka-client
  ``普通kafka 客户端``
* demo-thread
  ``线程安全练习项目``
* demo-tool-util
  `项目常用工具`
* springboot-annotation
  ``注解练习``
* springboot-easy-captcha
  ``验证码插件``
* springboot-elasticsearch
  ``基于 7.x 版本的搜搜``
* springboot-elasticsearch-6.x
  ``基于 6.x 版本的搜索``
* springboot-insert-batch
  ``批量插入数据``
* springboot-kafka-consumer
  ``spring boot kafka 消费端``
* springboot-kafka-producer
  ``spring boot kafka 生产者``
* springboot-redis
    ``集成redis``
* springboot-schedule-job.
    ``定时任务``
* springboot-solr
    ``基于 solr 搜索``
* springboot-start-web
    ``web 初始化项目``
* springboot-stl-demo
    ``stl 项目案例``
* springboot-time-task
    ``定时任务测试``
* springboot-tx-cos-image
    ``基于腾讯云对象存储服务图片上传``
* springboot-websocket
    ``独立 websocket 服务``
* springboot-websocket-kafka
  ``kafka -> websocket 整合``
  
项目地址: [https://github.com/byebai95/spring](https://github.com/byebai95/spring) 
>  客官, 如果有帮到你,不如送个star 再走吧

##项目模块
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.baizhuang</groupId>
    <artifactId>spring</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>demo-tool-util</module>
        <module>springboot-time-task</module>
        <module>springboot-elasticsearch</module>
        <module>springboot-redis</module>
        <module>springboot-annotation</module>
        <module>springboot-websocket</module>
        <module>springboot-tx-cos-image</module>
        <module>springboot-easy-captcha</module>
        <module>springboot-insert-batch</module>
        <module>springboot-solr</module>
        <module>demo-kafka-client</module>
        <module>springboot-stl-demo</module>
        <module>demo-thread</module>
        <module>springboot-websocket-kafka</module>
        <module>springboot-kafka-producer</module>
        <module>springboot-kafka-consumer</module>
        <module>springboot-elasticsearch-6.x</module>
        <module>springboot-start-web</module>
        <module>springboot-schedule-job</module>
    </modules>
</project>
```



