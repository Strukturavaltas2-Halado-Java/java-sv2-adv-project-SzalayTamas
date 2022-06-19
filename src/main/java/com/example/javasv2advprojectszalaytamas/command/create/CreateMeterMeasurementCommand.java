package com.example.javasv2advprojectszalaytamas.command.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CreateMeterMeasurementCommand {

    @Schema(description = "value of the new measurement", example = "100")
    @NotNull
    private double usedElectricity;
    @Schema(description = "the date of measurement", example = "2022-06-10T16:15:39.9627303")
    @NotNull
    private LocalDateTime dateOfMeasurement;
}
