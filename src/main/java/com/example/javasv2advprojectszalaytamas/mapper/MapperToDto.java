package com.example.javasv2advprojectszalaytamas.mapper;

import com.example.javasv2advprojectszalaytamas.command.create.CreateUserCommand;
import com.example.javasv2advprojectszalaytamas.dto.InvoiceDto;
import com.example.javasv2advprojectszalaytamas.dto.MeterDto;
import com.example.javasv2advprojectszalaytamas.dto.UserDto;
import com.example.javasv2advprojectszalaytamas.entity.Contact;
import com.example.javasv2advprojectszalaytamas.entity.Invoice;
import com.example.javasv2advprojectszalaytamas.entity.Meter;
import com.example.javasv2advprojectszalaytamas.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperToDto {

    MeterDto toMeterDto(Meter meter);

    List<MeterDto> toMeterDto(List<Meter> meter);

    UserDto toUserDto(User user);

    List<UserDto> toUserDto(List<User> users);

    InvoiceDto toInvoiceDto(Invoice invoice);

    List<InvoiceDto> toInvoiceDto(List<Invoice> invoices);

    Contact createContact(CreateUserCommand command);
}
