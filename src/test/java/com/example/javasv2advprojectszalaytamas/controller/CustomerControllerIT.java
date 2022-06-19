package com.example.javasv2advprojectszalaytamas.controller;

import com.example.javasv2advprojectszalaytamas.command.create.CreateCustomerCommand;
import com.example.javasv2advprojectszalaytamas.dto.ContactDto;
import com.example.javasv2advprojectszalaytamas.dto.CustomerDto;
import com.example.javasv2advprojectszalaytamas.entity.Contact;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerIT {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(1)
    void testCreateNewCustomer() {
        webTestClient.delete().uri("api/customer").exchange();
        webTestClient
                .post()
                .uri("api/customer")
                .bodyValue(new CreateCustomerCommand("EmailAddress@Gmail.com", "ZipCode123", "Szondy street 162", "Budapest", "062012345678", 1200))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CustomerDto.class).value(e -> assertThat(e.getContact().getEmail()).isEqualTo("EmailAddress@Gmail.com")
                );
    }

    @Test
    @Order(2)
    void testCreateNewCustomerFailure() {
        webTestClient
                .post()
                .uri("api/customer")
                .bodyValue(new CreateCustomerCommand("", "ZipCode123", "Szondy street 162", "Budapest", "062012345678",1000))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @Order(3)
    void testFindAllCustomer() {
        webTestClient.get().uri("api/customer")
                .exchange()
                .expectBodyList(CustomerDto.class)
                .hasSize(1);
    }
}