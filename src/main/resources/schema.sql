create table if not exists hardware
(
    id              identity,
    name            varchar(10)   not null,
    code            number unique not null,
    price           number        not null,
    type            nvarchar      not null,
    available_stock number        not null
);

create table if not exists review
(
    id            identity,
    title         varchar(10) not null,
    text          nvarchar    not null,
    grade         number      not null,
    hardware_code number      not null,
    constraint fk_hardware foreign key (hardware_code) references hardware (code)
);

create table if not exists user
(
    id       identity,
    username varchar(10) not null,
    password nvarchar    not null
);

create table if not exists authority
(
    id             identity,
    authority_name varchar(10) not null
);

create table if not exists user_authority
(
    user_id      number not null,
    authority_id number not null,
    constraint fk_user foreign key (user_id) references user (id),
    constraint fk_authority foreign key (authority_id) references authority (id)
);
