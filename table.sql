create table if not exists tb_ask
(
    id          int auto_increment comment '主键'
    primary key,
    campus      int          null comment '0北洋园 1卫津路',
    category_id int          null comment '种类id',
    tags        varchar(100) null comment '标签信息',
    created     datetime     null comment '创建时间',
    user_id     int          null comment '用户id'
    );

create table if not exists tb_category
(
    id            int auto_increment comment '主键'
    primary key,
    category_name varchar(20) null comment '种类名字',
    up_id         int         null comment '上级id'
    );

create table if not exists tb_category_image
(
    id          int auto_increment comment '主键'
    primary key,
    category_id int          null comment '种类id',
    image_url   varchar(100) null comment '图片地址'
    );

create table if not exists tb_post
(
    id            int auto_increment comment '主键'
    primary key,
    campus        int          null comment '0北洋园1卫津路',
    category_id   int          null comment '种类id',
    tags          varchar(100) null comment '标签详情',
    images        varchar(100) null comment '图片',
    find_location varchar(30)  null comment '发现位置',
    description   varchar(50)  null comment '描述信息',
    contact       varchar(50)  null comment '联系方式',
    created       datetime     null comment '发帖时间',
    user_id       int          null,
    now_location  varchar(30)  null
    );

create table if not exists tb_post_user
(
    post_id int null comment '帖子的id',
    user_id int null comment '用户的id'
);

create table if not exists tb_tags
(
    id          int auto_increment comment '主键'
    primary key,
    tag_name    varchar(10)  null comment '标签名',
    tag_content varchar(100) null comment '标签选项',
    category_id int          null
    );

create table if not exists tb_user
(
    id       bigint auto_increment comment '学号'
    primary key,
    username varchar(20)  null comment '用户名',
    password varchar(100) null comment '密码',
    avatar   varchar(100) null comment '头像'
    );

