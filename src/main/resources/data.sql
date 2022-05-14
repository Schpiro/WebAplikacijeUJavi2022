delete from hardware;
delete from review;
delete from user;
delete from authority;
delete from user_authority;


insert into hardware (name, code, price, type, available_stock)
    values
    ('ryzen 9', 123, 352, 'CPU', 45),
    ('intel i7', 1253, 3512, 'CPU', 45),
    ('gtx1050', 1231, 354312, 'GPU', 45);

insert into review (title,text,grade,hardware_code)
    values
    ('odlicno','ovo je super',5, 123),
    ('solid','uzeo bi opet',4, 123),
    ('meh','ovo je lose',1, 1253),
    ('uzas','ovo je strasno lose!',1, 1253),
    ('doodoo','ovo je grozno!',2, 1231),
    ('kriticno','neopisivo!',1, 1231);

insert into authority (authority_name)
    values
    ('ROLE_ADMIN'),
    ('ROLE_USER');

insert into user (username,password)
    values
    ('admin','$2a$12$idvVNoEhmDFHg9/my0c.juxD77hEID6AX1bc0YqRJXdpuNvuJE9b2'),
    ('user','$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq');

insert into user_authority (user_id,authority_id)
    values
    (1,1),
    (2,2);
