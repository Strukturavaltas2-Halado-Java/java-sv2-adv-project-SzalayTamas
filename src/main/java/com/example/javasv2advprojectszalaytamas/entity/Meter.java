package com.example.javasv2advprojectszalaytamas.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "meters")
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meter_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ElementCollection
    @CollectionTable(name = "measurements", joinColumns = @JoinColumn(name = "meter_id"))
    private List<Measurement> measurements = new LinkedList<>();

    public void addMeasurement(Measurement measurement) {
        measurements.add(measurement);
    }
}
