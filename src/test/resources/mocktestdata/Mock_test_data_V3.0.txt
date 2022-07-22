insert into clients (id, name)
values (1, 'ТОВ "Пекарня"');
insert into clients (id, name)
values (2, 'ТОВ "Кондитерська"');
insert into clients (id, name)
values (3, 'ТОВ "Хлібзавод"');

insert into addresses (id, address, client_id)
values (1, 'м. Київ, вул. Металістів, 8, оф. 4-24', 1);
insert into addresses (id, address, client_id)
values (2, 'м. Київ, вул. Хрещатик, 1', 1);
insert into addresses (id, address, client_id)
values (3, 'м. Київ, вул. Молодогвардійська, 22, оф. 35', 1);
insert into addresses (id, address, client_id)
values (4, 'м. Київ, вул. Глибочицька, 12Б, оф. 3', 2);
insert into addresses (id, address, client_id)
values (5, 'м. Київ, вул. Межигірська, 22, оф. 3', 2);
insert into addresses (id, address, client_id)
values (6, 'м. Київ, вул. Політехнічна, 1, оф. 65', 3);

insert into managers (id, first_name, last_name, phone_number, email)
values (1, 'Петро', 'Коваленко', '+380671111111', 'p.kovalenko@goodspartner.com');
insert into managers (id, first_name, last_name, phone_number, email)
values (2, 'Іван', 'Шугай', '+380672222222', 'i.shuhai@goodspartner.com');
insert into managers (id, first_name, last_name, phone_number, email)
values (3, 'Андрій', 'Бублик', '+380673333333', 'a.bublyk@goodspartner.com');


insert into routes (id, status, date, distance, estimated_time, route_link)
values (1, 'new', '2022-07-09', 100, now() + interval '5 hour', 'http');

insert into routes (id, status, date, distance, estimated_time, start_time, route_link)
values (2, 'in progress', '2022-07-09', 200, now() + interval '6 hour', now(), 'http');

insert into routes (id, status, date, distance, estimated_time, start_time, finish_time, spent_time, route_link)
values (3, 'finished', '2022-07-09', 300, now() + interval '7 hour', now(), now() + interval '6 hour', '06:00:00',
        'http');

insert into orders (id, number, created_date, shipping_date, address_id, manager_id, route_id)
values (1, 12345, NOW() - interval '72 hour', NOW() + interval '720 hour', 1, 1, 1);
insert into orders (id, number, created_date, shipping_date, address_id, manager_id, route_id)
values (2, 43532, NOW() - interval '72 hour', NOW() - interval '48 hour', 1, 2, 1);
insert into orders (id, number, created_date, shipping_date, address_id, manager_id, route_id)
values (3, 45463, NOW() - interval '72 hour', NOW() - interval '24 hour', 1, 3, 1);
insert into orders (id, number, created_date, shipping_date, address_id, manager_id, route_id)
values (4, 97342, NOW() - interval '48 hour', NOW() + interval '720 hour', 2, 1, 2);
insert into orders (id, number, created_date, shipping_date, address_id, manager_id, route_id)
values (5, 432565, NOW() - interval '48 hour', NOW() - interval '24 hour', 2, 3, 2);
insert into orders (id, number, created_date, shipping_date, address_id, manager_id, route_id)
values (6, 356325, NOW() - interval '48 hour', NOW(), 2, 2, 2);
insert into orders (id, number, created_date, shipping_date, address_id, manager_id, route_id)
values (7, 479324, NOW() - interval '24 hour', NOW() + interval '720 hour', 3, 1, 3);
insert into orders (id, number, created_date, shipping_date, address_id, manager_id, route_id)
values (8, 477255, NOW() - interval '24 hour', NOW() + interval '24 hour', 3, 3, 3);
insert into orders (id, number, created_date, shipping_date, address_id, manager_id, route_id)
values (9, 986453, NOW() - interval '24 hour', NOW() - interval '24 hour', 3, 1, 3);
insert into orders (id, number, created_date, shipping_date, address_id, manager_id, route_id)
values (10, 426457, NOW() - interval '24 hour', NOW() - interval '24 hour', 3, 2, 3);

insert into products (id, name, kg, store_id)
values (1, '3434 Паста шоколадна', 1.52, 1);
insert into products (id, name, kg, store_id)
values (2, '353 Стружка кокосова', 0.34, 1);
insert into products (id, name, kg, store_id)
values (3, '6798 Фарба харчова червона', 0.52, 1);
insert into products (id, name, kg, store_id)
values (4, '576853 Масло екстра', 20.56, 1);
insert into products (id, name, kg, store_id)
values (5, '66784 Арахісова паста', 0.55, 1);
insert into products (id, name, kg, store_id)
values (6, '8795 Мука екстра', 123.56, 2);
insert into products (id, name, kg, store_id)
values (7, '56743 Форми пасхальні', 4.65, 2);
insert into products (id, name, kg, store_id)
values (8, '4695 Фарба харчова зелена', 3.54, 2);
insert into products (id, name, kg, store_id)
values (9, '8452 Масло 1й гатунок', 34.44, 1);
insert into products (id, name, kg, store_id)
values (10, '46643 Фарба харчова синя', 5.78, 2);

insert into stores (id, name, address)
values (1, 'Склад №1', 'Фастів, вул. Широка, 15');
insert into stores (id, name, address)
values (2, 'Склад №2', 'Фастів, вул. Зелена, 5');

insert into orders_products (id, order_id, product_id, count)
values (1, 1, 1, 1);
insert into orders_products (id, order_id, product_id, count)
values (2, 1, 2, 2);

insert into orders_products (id, order_id, product_id, count)
values (3, 2, 3, 3);
insert into orders_products (id, order_id, product_id, count)
values (4, 2, 4, 4);

insert into orders_products (id, order_id, product_id, count)
values (5, 3, 5, 5);
insert into orders_products (id, order_id, product_id, count)
values (6, 3, 5, 5);

insert into orders_products (id, order_id, product_id, count)
values (7, 4, 5, 5);
insert into orders_products (id, order_id, product_id, count)
values (8, 4, 5, 5);

insert into orders_products (id, order_id, product_id, count)
values (9, 5, 8, 8);
insert into orders_products (id, order_id, product_id, count)
values (10, 5, 9, 9);

insert into orders_products (id, order_id, product_id, count)
values (11, 6, 10, 10);
insert into orders_products (id, order_id, product_id, count)
values (12, 6, 1, 1);

insert into orders_products (id, order_id, product_id, count)
values (13, 7, 10, 10);
insert into orders_products (id, order_id, product_id, count)
values (14, 7, 2, 2);

insert into orders_products (id, order_id, product_id, count)
values (15, 8, 3, 3);
insert into orders_products (id, order_id, product_id, count)
values (16, 8, 4, 4);

insert into orders_products (id, order_id, product_id, count)
values (17, 9, 5, 5);
insert into orders_products (id, order_id, product_id, count)
values (18, 9, 6, 6);

insert into orders_products (id, order_id, product_id, count)
values (19, 10, 7, 7);
insert into orders_products (id, order_id, product_id, count)
values (20, 10, 8, 8);

insert into cars (id, name, licence_plate, driver, weight_capacity, cooler)
values (1, 'Mercedes Sprinter', 'AA 1111 CT', 'Oleg Dudka', 2000, FALSE);
insert into cars (id, name, licence_plate, driver, weight_capacity, cooler)
values (2, 'Mercedes Vito', 'AA 2222 CT', 'Ivan Piddubny', 1000, FALSE);
insert into cars (id, name, licence_plate, driver, weight_capacity, cooler)
values (3, 'Mercedes Sprinter', 'AA 3333 CT', 'Anton Geraschenko', 2500, TRUE);
insert into cars (id, name, licence_plate, driver, weight_capacity, cooler)
values (4, 'Mercedes 818', 'AA 4444 CT', 'Serhiy Kotovich', 4000, FALSE);