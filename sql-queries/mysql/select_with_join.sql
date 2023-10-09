-- Active: 1695838882506@@121.254.195.229@3306@delivery

select u.name, sm.name
from user as u
    join user_order_id as uoi on u.id = uoi.user_id
    join user_order_menu as uom on uoi.id = uom.user_order_id
    join store_menu as sm on uom.store_menu_id = sm.id
where u.id = 1 and uoi.id = 1;

select sm.name from store_menu as sm where sm.id = 1 or sm.id = 2;