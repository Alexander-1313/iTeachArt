insert into User_Role (id, role) values(1, 'ROLE_USER'), (2, 'ROLE_ADMIN');
insert into Subscribe (id, type, cost) values (1, 'PRO', 100), (2, 'PREMIUM', 200), (3, 'PREMIUM+', 250);
insert into User (id, first_name, second_name, email, password, id_role, is_blocked, subscribe_expire) values(1, 'Alexander', 'Rybak', 'alexander.rybak2020@mail.ru', '$2y$12$BBQP2SnY0y..oidNkb.iyOvADyM/HlmmDWqkGrppOEzgijeabLhnK', 2, false, '2020-12-12');
insert into User (id, first_name, second_name, email, password, id_role, is_blocked, subscribe_expire) values(2, 'Kirill', 'Kohno', 'kohnik1101@gmail.com', '$2y$12$lEgZ8L3z.KLwVUptYam1oO63WVucmkuyxaj6LdMwGtmBxB/p/VKcK', 1, true, '2021-12-12');