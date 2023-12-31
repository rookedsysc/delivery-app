# Account Table 생성
CREATE TABLE
    delivery.account (
        id bigint(32) NOT NULL AUTO_INCREMENT,
        PRIMARY KEY (id)
    ) ENGINE = InnoDB;

# User Entity
CREATE TABLE
    delivery.user (
        id bigint(32) NOT NULL AUTO_INCREMENT,
        name VARCHAR(50) NOT NULL,
        email VARCHAR(100) NOT NULL,
        password VARCHAR(100) NOT NULL,
        status VARCHAR(50) NOT null,
        address VARCHAR(150) not null,
        registered_at DATETIME,
        unregistered_at DATETIME,
        last_login_at DATETIME,
        PRIMARY KEY (id)
    ) ENGINE = InnoDB;


ALTER TABLE delivery.user
  CHANGE COLUMN registed_at registered_at DATETIME NULL DEFAULT NULL;

ALTER TABLE delivery.user
  CHANGE COLUMN unregisted_at unregistered_at DATETIME NULL DEFAULT NULL;