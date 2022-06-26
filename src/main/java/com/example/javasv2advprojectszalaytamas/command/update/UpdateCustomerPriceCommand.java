package com.example.javasv2advprojectszalaytamas.command.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerPriceCommand {
    @Positive
    @Schema(description = "the new price to pay for electricity", example = "100")
    private double pricePerKiloWatt;
}
