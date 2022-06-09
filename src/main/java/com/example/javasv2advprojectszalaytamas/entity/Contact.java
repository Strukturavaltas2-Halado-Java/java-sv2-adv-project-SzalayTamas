package com.example.javasv2advprojectszalaytamas.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Getter
@Setter
public class Contact {
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_zipcode")
    private String zipCode;
    @Column(name = "user_address")
    private String address;
    @Column(name = "user_town")
    private String town;
    @Column(name = "user_phone_number")
    private String phoneNumbers;
}
