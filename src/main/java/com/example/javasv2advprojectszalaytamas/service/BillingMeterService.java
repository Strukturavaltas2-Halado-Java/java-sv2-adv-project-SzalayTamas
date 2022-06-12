package com.example.javasv2advprojectszalaytamas.service;

import com.example.javasv2advprojectszalaytamas.command.create.CreateInvoiceCommand;
import com.example.javasv2advprojectszalaytamas.command.create.CreateMeterCommand;
import com.example.javasv2advprojectszalaytamas.command.update.UpdateInvoiceCommand;
import com.example.javasv2advprojectszalaytamas.command.update.UpdateMeterCommand;
import com.example.javasv2advprojectszalaytamas.dto.InvoiceDto;
import com.example.javasv2advprojectszalaytamas.dto.MeterDto;
import com.example.javasv2advprojectszalaytamas.entity.*;
import com.example.javasv2advprojectszalaytamas.exception.CantCreateInvoiceException;
import com.example.javasv2advprojectszalaytamas.exception.CustomerNotFoundException;
import com.example.javasv2advprojectszalaytamas.exception.InvoiceNotFoundException;
import com.example.javasv2advprojectszalaytamas.exception.MeterNotFoundException;
import com.example.javasv2advprojectszalaytamas.mapper.MapperToDto;
import com.example.javasv2advprojectszalaytamas.repositori.InvoiceRepository;
import com.example.javasv2advprojectszalaytamas.repositori.MeterRepository;
import com.example.javasv2advprojectszalaytamas.repositori.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
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
        if (lastMeasurement.getDateOfMeasurement().isBefore(lastInvoice.getDateOfInvoiceCreation())) {
            throw new CantCreateInvoiceException(customer.getId());
        }
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

    @Transactional
    public List<MeterDto> findAllMeters() {
        return mapper.toMeterDto(meterRepository.findAllMeterWithMeasurements());
//        return mapper.toMeterDto(meterRepository.findAll());
    }


    @Transactional
    public Long updatePriceForCustomerInvoice(UpdateInvoiceCommand command) {
        Invoice invoice = invoiceRepository.findById(command.getCustomerId()).orElseThrow(() -> new InvoiceNotFoundException(command.getCustomerId()));
        invoice.setPricePerKiloWatt(command.getPricePerKiloWatt());
        return invoice.getId();
    }

    public void deleteAllInvoice() {
        invoiceRepository.deleteAll();
    }

    public void deleteInvoiceById(Long id) {
        invoiceRepository.deleteById(id);
    }

    public void deleteAllMeter() {
        meterRepository.deleteAll();
    }

    public void deleteMeterById(Long id) {
        meterRepository.deleteById(id);
    }

    @Transactional
    public Long updateDebtInInvoice(UpdateInvoiceCommand command) {
        Invoice invoice = invoiceRepository.findById(command.getInvoiceId()).orElseThrow(() -> new InvoiceNotFoundException(command.getInvoiceId()));
        invoice.setDebt(command.getDebt());
        return invoice.getId();
    }

    @Transactional
    public Long updateMeterUser(Long id, UpdateMeterCommand command) {
        Meter meter = meterRepository.findById(id).orElseThrow(() -> new MeterNotFoundException(id));
        Customer customer = customerRepository.
                findById(command.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException(command.getCustomerId()));
        meter.addCustomer(customer);
        return meter.getId();
    }
}
