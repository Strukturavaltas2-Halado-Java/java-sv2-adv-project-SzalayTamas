package com.example.javasv2advprojectszalaytamas.command.delete;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class DeleteInvoiceCommand {
    @Schema(description = "id of invoice to delete")
    @NotBlank
    private Long invoiceId;
}
