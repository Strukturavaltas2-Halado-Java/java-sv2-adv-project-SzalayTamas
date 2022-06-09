package com.example.javasv2advprojectszalaytamas.command.create;

import com.example.javasv2advprojectszalaytamas.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class CreateMeterCommand {
    private Long userId;
    private LocalDateTime dateOfMeasurement;
    private double meterStartingUsedElectricity;
}
