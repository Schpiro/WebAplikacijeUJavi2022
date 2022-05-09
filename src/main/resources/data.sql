delete from hardware;

insert into hardware (name, code, price, type_of_hardware, available_stock)
values ('ryzen 9', 123, 354312, 'CPU', 45);

insert into hardware (name, code, price, type_of_hardware, available_stock)
values ('intel i7', 1253, 354312, 'CPU', 45);

insert into hardware (name, code, price, type_of_hardware, available_stock)
values ('gtx1050', 1231, 354312, 'GPU', 45);

insert into review (title,text,grade,hardwareCode)
values ('odlicno','ovo je super',5, 123);

insert into review (title,text,grade,hardwareCode)
values ('solid','uzeo bi opet',4, 123);

insert into review (title,text,grade,hardwareCode)
values ('meh','ovo je lose',1, 1253);

insert into review (title,text,grade,hardwareCode)
values ('uzas','ovo je strasno lose!',1, 1253);

insert into review (title,text,grade,hardwareCode)
values ('doodoo','ovo je grozno!',2, 1231);

insert into review (title,text,grade,hardwareCode)
values ('kriticno','neopisivo!',1, 1231);
