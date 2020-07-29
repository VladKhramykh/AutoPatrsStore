-- drop database AutoPartsStore;
-- create database AutoPartsStore;
-- use AutoPartsStore;
create sequence hibernate_sequence start with 1 increment by 1;

create table car_marks
(
    id   bigint              not null,
    name varchar(255) unique not null,
    logo varbinary(max),
    primary key (id)
)

create table car_types
(
    id        bigint              not null,
    type_name varchar(255) unique not null,
    primary key (id)
)

create table cars
(
    id             bigint not null,
    model          varchar(255) unique,
    count_of_doors int    not null,
    year           int    not null,
    mark           bigint,
    type           bigint,
    primary key (id)
)

create table orders
(
    id         bigint not null,
    order_date date,
    user_id    bigint,
    primary key (id)
)

create table orders_parts
(
    order_id bigint not null,
    parts_id bigint not null
)

create table part_types
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
)

create table part_subtypes
(
    id           bigint not null,
    name         varchar(255),
    part_type_id bigint,
    primary key (id)
)

create table parts
(
    id        bigint not null,
    part_type bigint,
    part_subtype bigint,
    primary key (id)
)

create table parts_cars
(
    part_id bigint not null,
    cars_id bigint not null
)

create table parts_warehouses
(
    part_id      bigint not null,
    warehouse_id bigint not null
)

create table suppliers
(
    id       bigint not null,
    address  varchar(255),
    org_name varchar(255),
    primary key (id)
)

create table user_role
(
    user_id bigint not null,
    roles   varchar(255)
)

create table users
(
    id              bigint not null,
    activation_code varchar(255),
    address         varchar(255),
    email           varchar(255),
    password        varchar(255),
    first_name      varchar(255),
    last_name       varchar(255),
    tel_number      varchar(255),
    primary key (id)
)

create table company
(
    id             bigint       not null,
    name           varchar(255) not null,
    director_name  varchar(255) not null,
    office_address varchar(255) not null,
    tel_number     varchar(255) not null,
    description    varchar(255)
        primary key (id)
);

create table warehouses
(
    id         bigint not null,
    address    varchar(255),
    name       varchar(255),
    tel_number varchar(255),
    company_id bigint not null,
    primary key (id)
)


alter table orders_parts
    add constraint UK_orderparts_parts unique (parts_id);

alter table cars
    add constraint FK_car_carmark foreign key (mark) references car_marks;

alter table cars
    add constraint FK_car_cartype foreign key (type) references car_types;

alter table orders
    add constraint FK_order_userid foreign key (user_id) references users

alter table orders_parts
    add constraint FK_orderpart_part foreign key (parts_id) references parts;

alter table orders_parts
    add constraint FK_orderpart_order foreign key (order_id) references orders;

alter table parts
    add constraint FK_part_parttype foreign key (part_type) references part_types;

alter table parts
    add constraint FK_part_partsubtype foreign key (part_subtype) references part_subtypes;

alter table parts_cars
    add constraint FK_partcar_car foreign key (cars_id) references cars;

alter table parts_cars
    add constraint FK_partcar_part foreign key (part_id) references parts;

alter table parts_warehouses
    add constraint FK_partwarehouse_part foreign key (part_id) references parts;

alter table user_role
    add constraint FK_user_role foreign key (user_id) references users;

alter table warehouses
    add constraint FK_warehouse_company foreign key (company_id) references company;


