package com.example.javasv2advprojectszalaytamas.dto;

import com.example.javasv2advprojectszalaytamas.entity.Contact;
import com.example.javasv2advprojectszalaytamas.entity.Invoice;
import com.example.javasv2advprojectszalaytamas.entity.Meter;


import java.util.List;

public class UserDto {

    private Long id;
    private MeterDto meter;
    private double balance;
    private List<InvoiceDto> invoices;
    private Contact contact;
    private double pricePerKilowatt;
}
