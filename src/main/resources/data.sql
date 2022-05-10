delete from hardware;
delete from review;

insert into hardware (name, code, price, type, available_stock)
    values ('ryzen 9', 123, 354312, 'CPU', 45),
        ('intel i7', 1253, 354312, 'CPU', 45),
        ('gtx1050', 1231, 354312, 'GPU', 45);

insert into review (title,text,grade,hardware_code)
    values ('odlicno','ovo je super',5, 1),
    ('solid','uzeo bi opet',4, 1),
    ('meh','ovo je lose',1, 2),
    ('uzas','ovo je strasno lose!',1, 2),
    ('doodoo','ovo je grozno!',2, 3),
    ('kriticno','neopisivo!',1, 3);
