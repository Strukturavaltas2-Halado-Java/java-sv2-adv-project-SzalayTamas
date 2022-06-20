package com.example.javasv2advprojectszalaytamas.service;

import com.example.javasv2advprojectszalaytamas.command.create.CreateInvoiceCommand;
import com.example.javasv2advprojectszalaytamas.command.create.CreateMeterCommand;
import com.example.javasv2advprojectszalaytamas.command.create.CreateMeterMeasurementCommand;
import com.example.javasv2advprojectszalaytamas.command.update.UpdateInvoiceStatusCommand;
import com.example.javasv2advprojectszalaytamas.command.update.UpdateMeterCommand;
import com.example.javasv2advprojectszalaytamas.dto.InvoiceDto;
import com.example.javasv2advprojectszalaytamas.dto.MeterDto;
import com.example.javasv2advprojectszalaytamas.entity.*;
import com.example.javasv2advprojectszalaytamas.exception.*;
import com.example.javasv2advprojectszalaytamas.mapper.MapperToDto;
import com.example.javasv2advprojectszalaytamas.repositori.InvoiceRepository;
import com.example.javasv2advprojectszalaytamas.repositori.MeterRepository;
import com.example.javasv2advprojectszalaytamas.repositori.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
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
    public MeterDto createMeterForCustomerById(CreateMeterCommand command) {
        Customer customer = customerRepository.findById(command.getUserId()).orElseThrow(() -> new CustomerNotFoundException(command.getUserId()));
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

    public void deleteInvoiceById(Long id) {
        invoiceRepository.deleteById(id);
    }


    @Transactional
    public void deleteMeterById(Long id) {
//        deleteConstraint(id);
        meterRepository.deleteById(id);
    }

//    private void deleteConstraint(Long id) {
//        Meter meter = meterRepository.findById(id).orElseThrow(() -> new MeterNotFoundException(id));
//        meter.setCustomer(null);
//    }

    @Transactional
    public Long updateStatusOfTheInvoice(Long id, UpdateInvoiceStatusCommand command) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException(id));
        invoice.setStatus(parseStatus(command));
        return invoice.getId();
    }

    private Status parseStatus(UpdateInvoiceStatusCommand command) {
        if (command.getStatus().equals(Status.Pending.toString())) {
            return Status.Pending;
        } else if (command.getStatus().equals(Status.Paid.toString())) {
            return Status.Paid;
        }
        throw new IllegalInvoiceStatusException(command.getStatus());
    }

    @Transactional
    public InvoiceDto createInvoiceByCustomerId(CreateInvoiceCommand command) {
        Customer customer = customerRepository.findById(command.getCustomerId()).orElseThrow(
                () -> new CustomerNotFoundException(command.getCustomerId()));
        Invoice invoice = new Invoice();
        setInvoice(invoice, customer, command);
        return mapper.toInvoiceDto(invoiceRepository.save(invoice));
    }

    private void setInvoice(Invoice invoice, Customer customer, CreateInvoiceCommand command) {
        invoice.setCustomer(customer);
        invoice.setMeter(customer.getMeter());
        invoice.setBilled_electricity(command.getUsedElectricity());
        invoice.setDebt(customer.getPricePerKiloWatt() * command.getUsedElectricity());
        invoice.setPricePerKiloWatt(customer.getPricePerKiloWatt());
        invoice.setStatus(Status.Pending);
        invoice.setDateOfInvoiceCreation(LocalDateTime.now());
    }

    public InvoiceDto findInvoiceById(Long id) {
        return mapper.toInvoiceDto(invoiceRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException(id)));
    }

    @Transactional
    public MeterDto findMeterById(Long id) {
        return mapper.toMeterDto(meterRepository.findById(id).orElseThrow(() -> new MeterNotFoundException(id)));
    }

    @Transactional
    public MeterDto createMeterMeasurement(Long id, CreateMeterMeasurementCommand command) {
        Meter meter = meterRepository.findById(id).orElseThrow(() -> new MeterNotFoundException(id));
        validateMeasurement(meter, command);
        meter.addMeasurement(new Measurement(command.getDateOfMeasurement(), command.getUsedElectricity()));
        return mapper.toMeterDto(meter);
    }

    private void validateMeasurement(Meter meter, CreateMeterMeasurementCommand command) {
        double highestMeasurement = meter.getMeasurements().stream().map(e -> e.getUsedElectricity()).sorted(Comparator.reverseOrder()).findFirst().orElse(0D);
        LocalDateTime latestMeasurement = meter.getMeasurements().stream().map(e -> e.getDateOfMeasurement()).sorted(Comparator.reverseOrder()).findFirst().orElse(LocalDateTime.MIN);
        if (highestMeasurement > command.getUsedElectricity() || latestMeasurement.isAfter(command.getDateOfMeasurement())) {
            throw new IllegalMeasurementException();
        }
    }

    public void deleteAllInvoice() {
        invoiceRepository.deleteAll();
    }

    public void deleteAllMeter() {
        meterRepository.deleteAll();
    }
}
