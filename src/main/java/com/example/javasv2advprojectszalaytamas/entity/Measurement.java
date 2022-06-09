package com.example.javasv2advprojectszalaytamas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@Table(name = "measurements")
public class Measurement {
    @Column(name = "measurement_date")
    private LocalDateTime dateOfMeasurement;
    @Column(name = "measurement_used_electricity")
    private double usedElectricity;

    public Measurement() {

    }
}
