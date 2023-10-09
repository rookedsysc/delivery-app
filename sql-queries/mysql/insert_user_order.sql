-- Active: 1695838882506@@121.254.195.229@3306@delivery
# user order id
insert into user_order_id (user_id, status, amount, ordered_at) 
VALUES (1, 'REGISTERED', 10000, '2023-10-08 07:40:32');


# user order menu
insert into user_order_menu (user_order_id, store_menu_id, status) VALUES (1, 1, 'REGISTERED'),
(1,2,"REGISTERED"),
(1,3,"REGISTERED");
