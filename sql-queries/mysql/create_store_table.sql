# Store Entity
CREATE TABLE
    delivery.store (
        id bigint(32) NOT NULL AUTO_INCREMENT,
        name VARCHAR(100) NOT NULL,
        address VARCHAR(150) not null,
        status VARCHAR(50),
        category VARCHAR(50),
        star double not null DEFAULT 0,
        thumbnail_url VARCHAR(500) not null,
        minimum_amount DECIMAL(11,4)not null,
        minimum_delivery_amount DECIMAL(11,4)not null,
        phone_number VARCHAR(20),
        PRIMARY KEY (id)
    ) ENGINE = InnoDB;
