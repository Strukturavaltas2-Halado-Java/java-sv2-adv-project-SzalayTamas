package com.example.javasv2advprojectszalaytamas.dto;

import com.example.javasv2advprojectszalaytamas.entity.Customer;
import com.example.javasv2advprojectszalaytamas.entity.Meter;
import com.example.javasv2advprojectszalaytamas.entity.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class InvoiceDto {

    @Schema(description = "Unique identifier of the invoice", example = "1")
    private Long id;
    @Schema(description = "The invoice belongs to this meter")
    private Long meter;
    @Schema(description = "The invoice belongs to this customer ")
    @JsonIgnoreProperties("invoices")
    private long customer;
    @Schema(description = "The customer is getting billed for that amount of electricity", example = "13")
    private double usedElectricity;
    @Schema(description = "Shows the invoice status can be pending or paid")
    private Status status;
    @Schema(description = "The customer have to pay that amount of money for the used electricity", example = "1")
    private double debt;
    @Schema(description = "The date of the creation this invoice" , example = "2022-06-10T16:15:39.9627303")
    private LocalDateTime dateOfInvoiceCreation;
    @Schema(description = "The price customer have to pay for electricity per kilowatt ")
    private double pricePerKiloWatt;







}
