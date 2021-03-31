CREATE TABLE auth
(
    id       int          NOT NULL auto_increment COMMENT '主键ID',
    username varchar(256) not NULL COMMENT '用户名',
    password varchar(64)  not null comment '密码',
    PRIMARY KEY (id),
    key idx_username (username)
) DEFAULT CHARSET = utf8mb4 COMMENT 'auth';