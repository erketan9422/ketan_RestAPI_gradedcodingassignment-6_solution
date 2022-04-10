
--Login to the application with below credentials:
--Username:	admin
--Password:	123456

--***************************
insert into user values (1, '$2a$10$hHRdxJ4bJAOjrZYsn4bZGu.Co8sbtUfdnoNSWrq54X8uBoJ2uIOwO', 'admin');
insert into role values (1, 'ADMIN');
insert into users_roles values (1,1);