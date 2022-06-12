package com.example.javasv2advprojectszalaytamas.command.update;

import com.example.javasv2advprojectszalaytamas.entity.Measurement;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.PrimitiveIterator;

@Getter
@Setter
@NoArgsConstructor
public class UpdateMeterCommand {
    @Schema(description = "asssign the meter to a customer")
    private Long customerId;
    @Schema(description = "Add the measurement date to the meter")
    private double usedElectricity;
    @Schema(description =  "Add used electricity to to the meter")
    private LocalDateTime dateOfMeasurement;
}
