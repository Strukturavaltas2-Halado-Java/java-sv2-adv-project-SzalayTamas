package com.example.javasv2advprojectszalaytamas.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "meter_id")
    private Meter meter;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "invoice_status")
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @Column(name = "customer_debt")
    private double debt;
    @Column(name = "invoice_date")
    private LocalDateTime dateOfInvoiceCreation;
    @Column(name = "customer_used_electricity")
    private double usedElectricity;
    @Column(name = "invoice_price_per_kilowatt")
    private double pricePerKiloWatt;
}
