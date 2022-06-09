CREATE TABLE users
(
    user_id           BIGINT AUTO_INCREMENT NOT NULL,
    user_balance      DOUBLE,
    user_email        VARCHAR(30),
    user_zipcode      VARCHAR(11),
    user_town         varchar(20),
    user_address      VARCHAR(120),
    user_phone_number varchar(15),
    user_price_per_kilowatt DOUBLE,
    PRIMARY KEY (user_id)
);
CREATE TABLE meters
(
    meter_id BIGINT AUTO_INCREMENT NOT NULL,
    user_id  BIGINT,
    PRIMARY KEY (meter_id) USING BTREE
);

CREATE TABLE invoices
(
    invoice_id            BIGINT AUTO_INCREMENT NOT NULL,
    meter_id              BIGINT                NOT NULL,
    user_id               BIGINT                NOT NULL,
    user_debt             DOUBLE,
    user_used_electricity DOUBLE,
    invoice_status        VARCHAR(10),
    invoice_date          DATETIME,
    PRIMARY KEY (invoice_id)
);

CREATE TABLE measurements
(
    measurement_date             DATETIME,
    measurement_used_electricity DOUBLE,
    meter_id                     BIGINT NOT NULL
);