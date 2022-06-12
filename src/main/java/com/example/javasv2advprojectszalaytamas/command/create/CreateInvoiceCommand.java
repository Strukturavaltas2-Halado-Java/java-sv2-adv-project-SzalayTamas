package com.example.javasv2advprojectszalaytamas.command.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class CreateInvoiceCommand {
        @NotBlank
        @Schema(description = "Create a new invoice for the customer with that id")
        private Long customerId;
        @Schema(description = "Used electricity for the new invoice")
        @Positive
        private double usedElectricity;
}
