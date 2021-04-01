CREATE TABLE if not exists items
(
    id      serial,
    item_id bigint        not NULL,
    status  varchar(256)  not NULL,
    owner   varchar(64)   not null default '',
    content varchar(1024) not null default '',
    PRIMARY KEY (id)
);
