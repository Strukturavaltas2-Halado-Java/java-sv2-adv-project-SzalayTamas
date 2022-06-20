CREATE TABLE customers (
  customer_id BIGINT AUTO_INCREMENT NOT NULL,
   customer_price_per_kilowatt DOUBLE NULL,
   customer_email VARCHAR(255) NULL,
   customer_zipcode VARCHAR(255) NULL,
   customer_address VARCHAR(255) NULL,
   customer_town VARCHAR(255) NULL,
   customer_phone_number VARCHAR(255) NULL,
   CONSTRAINT pk_customers PRIMARY KEY (customer_id)
);

CREATE TABLE invoices (
  invoice_id BIGINT AUTO_INCREMENT NOT NULL,
   meter_id BIGINT NULL,
   customer_id BIGINT NULL,
   invoice_status VARCHAR(255) NULL,
   customer_debt DOUBLE NULL,
   invoice_date datetime NULL,
   invoice_price_per_kilowatt DOUBLE NULL,
   invoice_billed_electricity DOUBLE NULL,
   CONSTRAINT pk_invoices PRIMARY KEY (invoice_id)
);

CREATE TABLE measurements (
measurement_date datetime NULL,
meter_id                     BIGINT NOT NULL,

   measurement_used_electricity DOUBLE NULL
);

CREATE TABLE meters (
  meter_id BIGINT AUTO_INCREMENT NOT NULL,
   customer_id BIGINT NULL,
   CONSTRAINT pk_meters PRIMARY KEY (meter_id)
);

-- ALTER TABLE invoices ADD CONSTRAINT FK_INVOICES_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (customer_id) ON DELETE set null ;
--
-- ALTER TABLE invoices ADD CONSTRAINT FK_INVOICES_ON_METER FOREIGN KEY (meter_id) REFERENCES meters (meter_id) ON DELETE set null;
--
-- ALTER TABLE meters ADD CONSTRAINT FK_METERS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (customer_id) ON DELETE set null;
--
-- ALTER TABLE measurements
--     ADD CONSTRAINT FK_METER FOREIGN KEY (`meter_id`) REFERENCES `meters` (`meter_id`);