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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @OneToMany(mappedBy = "user")
    private List<Meter> meters = new LinkedList<>();
    @Column(name = "user_balance")
    private double balance;
    @OneToMany(mappedBy = "user")
    private List<Invoice> invoices = new LinkedList<>();
    @Embedded
    private Contact contact;
    @Column(name = "user_price_per_kilowatt")
    private double pricePerKiloWatt;

    public void addMeter(Meter meter) {
        meters.add(meter);
        meter.setUser(this);
    }
    public void addInvoice(Invoice invoice){
        invoices.add(invoice);
        invoice.setUser(this);
    }
}
