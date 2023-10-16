
# user order menu (N : 1) user order id
ALTER TABLE delivery.user_order_menu
ADD CONSTRAINT fk_user_order_menu_user_order_id
FOREIGN KEY (user_order_id)
REFERENCES delivery.user_order_id (id);

ALTER TABLE delivery.user_order_menu
DROP KEY fk_user_order_menu_user_order_id;


SHOW CREATE TABLE delivery.user_order_menu;
SHOW CREATE TABLE delivery.user_order_id;

SELECT *
FROM information_schema.key_column_usage
WHERE table_name = delivery.user_order_id.id;

