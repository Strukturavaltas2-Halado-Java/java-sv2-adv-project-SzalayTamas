package com.example.javasv2advprojectszalaytamas.dto;

import com.example.javasv2advprojectszalaytamas.entity.Meter;
import com.example.javasv2advprojectszalaytamas.entity.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class InvoiceDto {
    private Long id;
    private MeterDto meter;
    private UserDto user;
    private double usedElectricity;
    private double debt;
    private LocalDateTime dateOfInvoiceCreation;
}
