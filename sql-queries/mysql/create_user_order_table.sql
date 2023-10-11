CREATE TABLE
    delivery.user_order_id (
        id bigint(32) NOT NULL AUTO_INCREMENT,
        user_id bigint(32) not NULL,
        status VARCHAR(50) not null,
        amount DECIMAL(11, 4) not null,
        ordered_at DATETIME,
        # 주문 수락 시간
        accepted_at DATETIME,
        cooking_started_at DATETIME,
        # 배달기사 픽업 시간
        delivery_started_at DATETIME,
        received_at DATETIME,
        PRIMARY KEY (id),
        index idx_user_id (user_id ASC) visible
    ) ENGINE = InnoDB;

create table
    delivery.user_order_menu (
        id bigint(32) NOT NULL AUTO_INCREMENT,
        user_order_id bigint(32) not NULL,
        store_menu_id bigint(32) not NULL,
        status VARCHAR(50) not null,
        PRIMARY KEY (id),
        index fk_user_order_menu_user_order_id (user_order_id ASC) visible,
        index fk_user_order_menu_store_menu_idx (store_menu_id ASC) visible
    ) engine = InnoDB;


ALTER TABLE delivery.user_order_id 
  CHANGE COLUMN cooking_start_at cooking_started_at DATETIME NULL DEFAULT NULL,
  CHANGE COLUMN delivery_start_at delivery_started_at DATETIME NULL DEFAULT NULL;

ALTER TABLE delivery.user_order_id 
  CHANGE COLUMN recieved received_at DATETIME NULL DEFAULT NULL;

