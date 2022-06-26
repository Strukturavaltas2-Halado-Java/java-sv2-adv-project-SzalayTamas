package com.example.javasv2advprojectszalaytamas.command.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMeterCommand {

    @Schema(description = "Create meter for this user", example = "1")
    @NotNull
    private Long userId;
    @Schema(description = "The date of deploying the meter for user", example = "2022-06-10T16:15:39.9627303")
    private LocalDateTime dateOfMeasurement;
    @Schema(description = "The meter starts with that amount of electricity used up", example = "0")
    private double meterStartingUsedElectricity;
}
