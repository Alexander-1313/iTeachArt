insert into User_Role (id, role) values(1, 'ROLE_USER'), (2, 'ROlE_ADMIN');
insert into Subscribe (id, type, cost) values (1, 'PRO', 100), (2, 'PREMIUM', 200), (3, 'PREMIUM+', 250);
insert into User (first_name, second_name, email, password, id_role, is_blocked) values('Alexander', 'Rybak', 'alexander.rybak2020@mail.ru', '123456', 2, false);
insert into User (first_name, second_name, email, password, id_role, is_blocked) values('Kirill', 'Kohno', 'kohnik1101@gmail.com', '9876123', 1, true);