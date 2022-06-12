package com.example.javasv2advprojectszalaytamas.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_meter_id")
    private Meter meter;
    @Column(name = "customer_balance")
    private double balance;
    @OneToMany(mappedBy = "customer")
    private List<Invoice> invoices = new LinkedList<>();
    @Embedded
    private Contact contact;
    @Column(name = "customer_price_per_kilowatt")
    private double pricePerKiloWatt;

    public void addInvoice(Invoice invoice){
        invoices.add(invoice);
        invoice.setCustomer(this);
    }
    public void addMeter(Meter meter){
        this.setMeter(meter);
        meter.setCustomer(this);
    }
    public Customer(Contact contact) {
        this.contact = contact;
    }
}