CREATE TABLE if not exists session
(
    id          serial,
    username    varchar(256) not NULL,
    create_time timestamp default null,
    PRIMARY KEY (id)
);