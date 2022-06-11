package com.example.javasv2advprojectszalaytamas.dto;

import com.example.javasv2advprojectszalaytamas.entity.Contact;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {

    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;
    @Schema(description = "Meter that follow the customers electricity usage ")
    private MeterDto meter;
    @Schema(description = "Balance of the user,negative on debt,positive on overpay", example = "-124")
    private double balance;
    @Schema(description = "List of created invoices for that customer")
    private List<InvoiceDto> invoices;
    @Schema(description = "Contact details of user")
    private ContactDto contact;
    @Schema(description = "The amount of money the user will get billed for consumed electricity per watt", example = "100")
    private double pricePerKiloWatt;
}
