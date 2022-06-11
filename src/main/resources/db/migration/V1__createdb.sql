CREATE TABLE customers
(
    customer_id                 BIGINT AUTO_INCREMENT NOT NULL,
    customer_balance            DOUBLE,
    customer_email              VARCHAR(50),
    customer_zipcode            VARCHAR(11),
    customer_town               varchar(20),
    customer_address            VARCHAR(150),
    customer_phone_number       varchar(15),
    customer_price_per_kilowatt DOUBLE,
    customer_meter_id           BIGINT,
    PRIMARY KEY (customer_id)
);
CREATE TABLE meters
(
    meter_id    BIGINT AUTO_INCREMENT NOT NULL,
    customer_id BIGINT,
    PRIMARY KEY (meter_id) USING BTREE
);

CREATE TABLE invoices
(
    invoice_id                 BIGINT AUTO_INCREMENT NOT NULL,
    meter_id                   BIGINT                NOT NULL,
    customer_id                BIGINT                NOT NULL,
    customer_debt              DOUBLE,
    customer_used_electricity  DOUBLE,
    invoice_status             VARCHAR(10),
    invoice_date               DATETIME,
    invoice_price_per_kilowatt DOUBLE,
    PRIMARY KEY (invoice_id)
);

CREATE TABLE measurements
(
    measurement_date             DATETIME,
    measurement_used_electricity DOUBLE,
    meter_id                     BIGINT NOT NULL
);