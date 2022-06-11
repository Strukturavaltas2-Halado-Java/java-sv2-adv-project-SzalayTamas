package com.example.javasv2advprojectszalaytamas.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Contact {
    @Column(name = "customer_email")
    private String email;
    @Column(name = "customer_zipcode")
    private String zipCode;
    @Column(name = "customer_address")
    private String address;
    @Column(name = "customer_town")
    private String town;
    @Column(name = "customer_phone_number")
    private String phoneNumber;
}
