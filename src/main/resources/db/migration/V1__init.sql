CREATE TABLE items
(
    id      int           NOT NULL auto_increment COMMENT '主键ID',
    item_id bigint        not NULL COMMENT 'item id',
    status  varchar(256)  not NULL COMMENT '状态',
    owner   varchar(64)   not null default '' comment '创建人',
    content varchar(1024) not null default '' comment '内容',
    PRIMARY KEY (id),
    key idx_item_id (item_id)
) DEFAULT CHARSET = utf8mb4 COMMENT 'todo items';
