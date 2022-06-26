package com.example.javasv2advprojectszalaytamas.command.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInvoiceStatusCommand {
    @NotBlank
    @Schema(description = "Status of the invoice, Pending or Paid, others are invalid", example = "Paid")
    String status;
}
