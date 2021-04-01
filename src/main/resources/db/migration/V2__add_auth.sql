CREATE TABLE if not exists auth
(
    id       serial,
    username varchar(256) not NULL,
    password varchar(64)  not null,
    PRIMARY KEY (id)
);