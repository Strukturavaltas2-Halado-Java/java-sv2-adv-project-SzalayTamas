package com.example.javasv2advprojectszalaytamas.service;

import com.example.javasv2advprojectszalaytamas.command.create.CreateInvoiceCommand;
import com.example.javasv2advprojectszalaytamas.command.create.CreateMeterCommand;
import com.example.javasv2advprojectszalaytamas.command.update.UpdateInvoiceCommand;
import com.example.javasv2advprojectszalaytamas.dto.InvoiceDto;
import com.example.javasv2advprojectszalaytamas.dto.MeterDto;
import com.example.javasv2advprojectszalaytamas.entity.*;
import com.example.javasv2advprojectszalaytamas.exception.CustomerNotFoundException;
import com.example.javasv2advprojectszalaytamas.mapper.MapperToDto;
import com.example.javasv2advprojectszalaytamas.repositori.InvoiceRepository;
import com.example.javasv2advprojectszalaytamas.repositori.MeterRepository;
import com.example.javasv2advprojectszalaytamas.repositori.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class BillingMeterService {
    private CustomerRepository customerRepository;
    private MeterRepository meterRepository;
    private InvoiceRepository invoiceRepository;
    private MapperToDto mapper;

    public List<InvoiceDto> findAllInvoice() {
        return mapper.toInvoiceDto(invoiceRepository.findAll());
    }

    @Transactional
    public InvoiceDto createInvoiceByCustomerId(CreateInvoiceCommand command) {
        Customer customer = customerRepository.findById(command.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException(command.getCustomerId()));

        return mapper.toInvoiceDto(billCustomer(customer, command));
    }


    private Invoice billCustomer(Customer customer, CreateInvoiceCommand command) {
        Invoice invoice = new Invoice();
        return billCustomer(invoice, customer, command.getUsedElectricity());
    }

    @Transactional
    public InvoiceDto createInvoiceFromUsage(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        Measurement lastMeasurement = customer.getMeter().getMeasurements().stream().sorted(Comparator.comparing(Measurement::getDateOfMeasurement)).toList().get(0);
        Invoice lastInvoice = customer.getInvoices().stream().sorted(Comparator.comparing(Invoice::getDateOfInvoiceCreation)).toList().get(0);
        return mapper.toInvoiceDto(billBasedOnUsage(customer, lastInvoice, lastMeasurement));
    }

    private Invoice billBasedOnUsage(Customer customer, Invoice lastInvoice, Measurement lastMeasurement) {
        Invoice newInvoice = new Invoice();
        double usedElectricity = (lastInvoice.getUsedElectricity() - lastMeasurement.getUsedElectricity());
        return billCustomer(newInvoice, customer, usedElectricity);

    }

    private Invoice billCustomer(Invoice newInvoice, Customer customer, double usedElectricity) {
        newInvoice.setPricePerKiloWatt(customer.getPricePerKiloWatt());
        newInvoice.setMeter(customer.getMeter());
        newInvoice.setDebt(customer.getPricePerKiloWatt() * usedElectricity);
        newInvoice.setDateOfInvoiceCreation(LocalDateTime.now());
        newInvoice.setStatus(Status.Pending);
        newInvoice.setCustomer(customer);
        return invoiceRepository.save(newInvoice);
    }

    @Transactional
    public MeterDto createMeterForCustomerById(Long id, CreateMeterCommand command) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        Meter meter = new Meter();
        if (customer.getMeter() != null) {
            customer.getMeter().setCustomer(null);
        }
        meter.setCustomer(customer);
        meter.addMeasurement(new Measurement(command.getDateOfMeasurement(), command.getMeterStartingUsedElectricity()));
        return mapper.toMeterDto(meterRepository.save(meter));
    }
}
