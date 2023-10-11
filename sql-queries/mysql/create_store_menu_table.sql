CREATE TABLE
    delivery.store_menu (
        id bigint(32) NOT NULL AUTO_INCREMENT,
        store_id bigint(32) NOT NULL,
        name VARCHAR(100) NOT NULL,
        amount DECIMAL(11,4)not null,
        status VARCHAR(50) not null,
        thumbnail_url VARCHAR(500) not null,
        like_count int not null DEFAULT 0,
        sequence int not null DEFAULT 0,
        PRIMARY KEY (id)
    ) ENGINE = InnoDB;


# store <> store_menu OneToMany Relation

ALTER TABLE delivery.store_menu 
add INDEX idx_store_id (store_id ASC) visible;

# column name change 
ALTER TABLE store_menu 
CHANGE COLUMN amout amount DECIMAL(11,4) not null;