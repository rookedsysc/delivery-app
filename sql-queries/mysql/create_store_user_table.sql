-- Active: 1697006872503@@121.254.195.229@3306@delivery
CREATE TABLE
    delivery.store_user (
        id bigint(32) NOT NULL AUTO_INCREMENT,
        store_id bigint(32) NOT NULL,
        email VARCHAR(100) not null,
        password VARCHAR(100) not null,
        status VARCHAR(50) not null,
        # Spring Security는 항상 사용자의 권한을 체크하기 때문에 
        # role이라는 컬럼이 존재해야함
        # 컬럼이름이 반드시 role일 이유는 없음
        role VARCHAR(50) not null,
        registered_at DATETIME not null,
        unregistered_at DATETIME not null,
        last_login_at DATETIME not null,
        PRIMARY KEY (id),
        index idx_store_id (store_id ASC) visible,
    ) ENGINE = InnoDB;

ALTER TABLE delivery.store_user
add INDEX idx_store_id (store_id ASC) visible;


ALTER table delivery.store_user
CHANGE COLUMN unregisterd_at unregistered_at DATETIME NULL DEFAULT NULL;