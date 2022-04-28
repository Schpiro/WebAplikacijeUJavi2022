delete from hardware;

insert into hardware (name, code, price, type_of_hardware, available_stock)
values ('ryzen 9', 123, 354312, 'CPU', 45);

insert into hardware (name, code, price, type_of_hardware, available_stock)
values ('intel i7', 1253, 354312, 'CPU', 45);

insert into hardware (name, code, price, type_of_hardware, available_stock)
values ('gtx1050', 1231, 354312, 'GPU', 45);
/*
 name varchar(10) not null,
    code number not null primary key,
    price number not null,
    type_of_hardware varchar(5) not null,
    available_stock number not null
 */