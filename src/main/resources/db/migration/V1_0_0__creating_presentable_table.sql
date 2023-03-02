CREATE SEQUENCE if not exists users_id_seq
    START WITH 2
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

create table if not exists client
(
    id   bigint DEFAULT nextval('users_id_seq'::regclass) NOT NULL
        primary key
        unique,
    email       varchar(255) not null,
    password    varchar(255)  not null,
    firstname  varchar(255) ,
    lastname varchar(255),
    avatar varchar(255),
    role varchar(255)
);