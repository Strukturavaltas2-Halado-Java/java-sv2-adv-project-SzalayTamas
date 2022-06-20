ALTER TABLE invoices ADD CONSTRAINT FK_INVOICES_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (customer_id) ON DELETE set null ;

ALTER TABLE invoices ADD CONSTRAINT FK_INVOICES_ON_METER FOREIGN KEY (meter_id) REFERENCES meters (meter_id) ON DELETE set null;

ALTER TABLE meters ADD CONSTRAINT FK_METERS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (customer_id) ON DELETE set null;

ALTER TABLE measurements
    ADD CONSTRAINT FK_METER FOREIGN KEY (`meter_id`) REFERENCES `meters` (`meter_id`);