create table if not exists hardware (
    id identity,
    name varchar(10) not null,
    code number unique not null,
    price number not null,
    type nvarchar not null,
    available_stock number not null
);

create table if not exists review (
    id identity,
    title varchar(10) not null,
    text nvarchar not null,
    grade number not null,
    hardware_code number not null,
    constraint fk_hardware foreign key (hardware_code) references hardware(code)
);