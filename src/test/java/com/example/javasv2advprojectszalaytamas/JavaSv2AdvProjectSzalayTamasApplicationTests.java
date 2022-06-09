package com.example.javasv2advprojectszalaytamas;

import com.example.javasv2advprojectszalaytamas.entity.*;
import com.example.javasv2advprojectszalaytamas.repositori.InvoiceRepository;
import com.example.javasv2advprojectszalaytamas.repositori.MeterRepository;
import com.example.javasv2advprojectszalaytamas.repositori.UserRepository;
import com.example.javasv2advprojectszalaytamas.service.UserDetailsService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JavaSv2AdvProjectSzalayTamasApplicationTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MeterRepository meterRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    UserDetailsService userDetailsService;

    @Test
    void contextLoads() {

    }

    @Order(1)
    @Test
    void testUserRepo() {

    }

    @Order(2)
    @Test
    void testDataBase() {
        Contact contact = new Contact();
        contact.setAddress("Jo utca 12");
        contact.setEmail("Semmi@Semmi.com");
        contact.setTown("Budapest");
        contact.setPhoneNumbers("36205278601");
        contact.setZipCode("ZipCode");
        User user = new User();
        user.setPricePerKiloWatt(1_000);
        user.setContact(contact);
        Meter meter = new Meter();
        Measurement measurement = new Measurement();
        measurement.setDateOfMeasurement(LocalDateTime.now());
        measurement.setUsedElectricity(1337);
        meter.addMeasurement(measurement);
        user.addMeter(meter);
        Invoice invoice = new Invoice();
        invoice.setMeter(meter);
        invoice.setStatus(Status.Pending);
        invoice.setDateOfInvoiceCreation(LocalDateTime.now());
        invoice.setUsedElectricity(measurement.getUsedElectricity());
        invoice.setDebt(user.getPricePerKiloWatt()*measurement.getUsedElectricity());
        user.addInvoice(invoice);
        user.setBalance(invoice.getDebt()*-1);
        userRepository.save(user);
        meterRepository.save(meter);
        invoiceRepository.save(invoice);
    }

    @Test
    void nothing() {


    }
}
