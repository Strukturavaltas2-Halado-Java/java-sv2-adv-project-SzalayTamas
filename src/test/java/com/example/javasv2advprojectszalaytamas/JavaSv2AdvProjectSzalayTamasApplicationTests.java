package com.example.javasv2advprojectszalaytamas;

import com.example.javasv2advprojectszalaytamas.entity.*;
import com.example.javasv2advprojectszalaytamas.repositori.InvoiceRepository;
import com.example.javasv2advprojectszalaytamas.repositori.MeterRepository;
import com.example.javasv2advprojectszalaytamas.repositori.CustomerRepository;
import com.example.javasv2advprojectszalaytamas.service.CustomerDetailsService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JavaSv2AdvProjectSzalayTamasApplicationTests {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    MeterRepository meterRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    CustomerDetailsService customerDetailsService;

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
        contact.setPhoneNumber("36205278601");
        contact.setZipCode("ZipCode");
        Customer customer = new Customer();
        customer.setPricePerKiloWatt(1_000);
        customer.setContact(contact);
        Meter meter = new Meter();
        Measurement measurement = new Measurement();
        measurement.setDateOfMeasurement(LocalDateTime.now());
        measurement.setUsedElectricity(1337);
        meter.addMeasurement(measurement);

        customer.addMeter(meter);
        Invoice invoice = new Invoice();
        invoice.setMeter(meter);
        invoice.setStatus(Status.Pending);
        invoice.setDateOfInvoiceCreation(LocalDateTime.now());
        invoice.setUsedElectricity(measurement.getUsedElectricity());
        invoice.setDebt(customer.getPricePerKiloWatt()*measurement.getUsedElectricity());
        invoice.setPricePerKiloWatt(customer.getPricePerKiloWatt());
        customer.addInvoice(invoice);
        customer.setBalance(invoice.getDebt()*-1);
        customerRepository.save(customer);
        meterRepository.save(meter);
        invoiceRepository.save(invoice);
        customerDetailsService.addMeterToCustomerByUserId(new Meter(),customer.getId());
    }

    @Test
    void nothing() {


    }
}
