package com.example.javasv2advprojectszalaytamas.command.create;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CreateInvoiceCommand {
        @NotBlank
        private Long customerId;
        private double usedElectricity;
}
