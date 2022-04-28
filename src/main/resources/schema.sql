create table if not exists hardware (
    name varchar(10) not null,
    code number not null primary key,
    price number not null,
    type_of_hardware varchar(5) not null,
    available_stock number not null
);