package com.example.javasv2advprojectszalaytamas.command.update;

import com.example.javasv2advprojectszalaytamas.entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UpdateInvoiceCommand {

    @Schema(description = "the id of invoice to update")
    private Long invoiceId;
    @Schema(description = "This meter will be assigned to the invoice")
    private Long meterId;
    @Schema(description = "The invoice  will be assigned to the invoice")
    private long customerId;
    @Schema(description = "Change the used amount of electricity of in invoice", example = "13")
    @Positive
    private double usedElectricity;
    @Schema(description = "Change the invoice status can be pending or paid")
    private Status status;
    @Schema(description = "Update amount of money customer have to pay for the used electricity", example = "1")
    private double debt;
    @Schema(description = "The date of the creation this invoice", example = "2022-06-10T16:15:39.9627303")
    private LocalDateTime dateOfInvoiceCreation;
    @Schema(description = "Change the price of electricity in the invoice")
    private double pricePerKiloWatt;
}
