# 天外天失物招领

#### 技术栈

SpringBoot2.5 + MybatisPlus3.2 + Shiro1.7 + RabbitMQ + OSS + JavaMail + Swagger2



#### 项目运行环境

JDK 1.8

Maven 3.8



MySQL 8.0

```mysql
# 创建数据库
create database find;
use find

# 运行建表语句
source table.sql
```



RabbitMQ

```shell
# docker运行rabbitmq
docker run -it -d --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management

# 默认用户名密码都是guest
```

#### 配置

MySQL username+password

RabbitMQ username+password

OSS  AccessID+AccessKey+bucket

Mail username+password



#### 部署

更改配置文件

上传Dockerfile文件和target目录到服务器

```shell
docker build -t twt-swzl .
docker run -it -d --name twt-swzl
