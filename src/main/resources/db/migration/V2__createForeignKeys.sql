ALTER TABLE `invoices`
    ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE ON DELETE NO ACTION,
    ADD CONSTRAINT `fk_meter_id_invoice` FOREIGN KEY (`meter_id`) REFERENCES `meters` (`meter_id`) ON UPDATE CASCADE ON DELETE NO ACTION;

ALTER TABLE `measurements`
    ADD CONSTRAINT `fk_meter_id` FOREIGN KEY (`meter_id`) REFERENCES `meters` (`meter_id`) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE `meters`
    ADD CONSTRAINT `fk_user_id_in_meters` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE ON DELETE NO ACTION;
