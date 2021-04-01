CREATE TABLE if not exists session
(
    id          int          NOT NULL auto_increment COMMENT '主键ID',
    username    varchar(256) not NULL COMMENT '用户名',
    create_time datetime              default null comment '创建时间',
    PRIMARY KEY (id),
    key idx_username (username)
) DEFAULT CHARSET = utf8mb4 COMMENT 'session';