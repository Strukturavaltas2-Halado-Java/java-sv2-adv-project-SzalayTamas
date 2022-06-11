ALTER TABLE `invoices`
    ADD CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON UPDATE CASCADE ON DELETE NO ACTION,
    ADD CONSTRAINT `fk_meter_id_invoice` FOREIGN KEY (`meter_id`) REFERENCES `meters` (`meter_id`) ON UPDATE CASCADE ON DELETE NO ACTION;

ALTER TABLE `measurements`
    ADD CONSTRAINT `fk_meter_id` FOREIGN KEY (`meter_id`) REFERENCES `meters` (`meter_id`) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE `meters`
    ADD UNIQUE INDEX `fk_customer_id_in_meters` (`customer_id`) USING BTREE;
#     ADD CONSTRAINT `fk_customer_id_in_meters` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON UPDATE CASCADE ON DELETE SET NULL ;
