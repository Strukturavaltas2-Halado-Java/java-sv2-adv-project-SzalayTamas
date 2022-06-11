package com.example.javasv2advprojectszalaytamas.mapper;

import com.example.javasv2advprojectszalaytamas.command.create.CreateCustomerCommand;
import com.example.javasv2advprojectszalaytamas.dto.CustomerDto;
import com.example.javasv2advprojectszalaytamas.dto.InvoiceDto;
import com.example.javasv2advprojectszalaytamas.dto.MeterDto;
import com.example.javasv2advprojectszalaytamas.entity.Contact;
import com.example.javasv2advprojectszalaytamas.entity.Invoice;
import com.example.javasv2advprojectszalaytamas.entity.Meter;
import com.example.javasv2advprojectszalaytamas.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperToDto {

    MeterDto toMeterDto(Meter meter);

    List<MeterDto> toMeterDto(List<Meter> meter);

    CustomerDto toCustomerDto(Customer customer);

    List<CustomerDto> toCustomerDto(List<Customer> customers);

    InvoiceDto toInvoiceDto(Invoice invoice);

    List<InvoiceDto> toInvoiceDto(List<Invoice> invoices);

    Contact createContact(CreateCustomerCommand command);

    default Long createIdFromInvoice(Invoice invoice) {
        return invoice.getId();
    }

    default Long createIdFromMeter(Meter meter) {
        return meter.getId();
    }

    default Long createIdFromCustomer(Customer customer) {
        return customer.getId();
    }
}
