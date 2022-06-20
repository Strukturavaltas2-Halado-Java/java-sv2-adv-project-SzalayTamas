package com.example.javasv2advprojectszalaytamas.service;

import com.example.javasv2advprojectszalaytamas.command.create.CreateCustomerCommand;
import com.example.javasv2advprojectszalaytamas.command.update.UpdateCustomerCommand;
import com.example.javasv2advprojectszalaytamas.command.update.UpdateCustomerPriceCommand;
import com.example.javasv2advprojectszalaytamas.dto.CustomerDto;
import com.example.javasv2advprojectszalaytamas.dto.InvoiceDto;
import com.example.javasv2advprojectszalaytamas.dto.MeterDto;
import com.example.javasv2advprojectszalaytamas.entity.Customer;
import com.example.javasv2advprojectszalaytamas.entity.Meter;
import com.example.javasv2advprojectszalaytamas.exception.CantCreateCustomerException;
import com.example.javasv2advprojectszalaytamas.exception.CustomerNotFoundException;
import com.example.javasv2advprojectszalaytamas.mapper.MapperToDto;
import com.example.javasv2advprojectszalaytamas.repositori.InvoiceRepository;
import com.example.javasv2advprojectszalaytamas.repositori.MeterRepository;
import com.example.javasv2advprojectszalaytamas.repositori.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerDetailsService {
    private CustomerRepository customerRepository;
    private MeterRepository meterRepository;
    private InvoiceRepository invoiceRepository;
    private MapperToDto mapper;


    @Transactional
    public void addMeterToCustomerByUserId(Meter meter, Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        customer.getMeter().setCustomer(null);
        customerRepository.flush();
        customer.addMeter(meter);
        meterRepository.save(meter);
    }

    @Transactional
    public CustomerDto findCustomerById(Long id) {
        return mapper.toCustomerDto(customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id)));
    }

    @Transactional
    public CustomerDto createNewCustomer(CreateCustomerCommand command) {
        if (customerRepository.findCustomerByContactContainingIgnoreCase(command.getEmail()).isPresent()) {
            throw new CantCreateCustomerException(command.getEmail());
        }
        Customer customer = new Customer((mapper.createContact(command)));
        customer.setPricePerKiloWatt(command.getPricePerKiloWatt());
        return mapper.toCustomerDto(customerRepository.save(customer));
    }

    public void deleteAllCustomer() {
        customerRepository.deleteAll();
    }

    @Transactional
    public void updateCustomersPrice(Long id, UpdateCustomerPriceCommand command) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        customer.setPricePerKiloWatt(command.getPricePerKiloWatt());
    }

    public List<InvoiceDto> findAllInvoicesByCustomerId(Long id) {
        return mapper.toInvoiceDto(invoiceRepository.findAllByCustomerId(id));
    }


    public List<CustomerDto> findAllWithInvoice() {
        return mapper.toCustomerDto(customerRepository.findAllWithInvoice());
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    @Transactional
    public void updateCustomerContactDetails(Long id, UpdateCustomerCommand command) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        customer.getContact().setPhoneNumber(command.getPhoneNumber());
        customer.getContact().setEmail(command.getEmail());
    }
}
