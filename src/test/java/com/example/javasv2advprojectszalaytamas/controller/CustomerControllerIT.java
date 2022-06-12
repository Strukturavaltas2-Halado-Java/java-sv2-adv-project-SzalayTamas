package com.example.javasv2advprojectszalaytamas.controller;

import com.example.javasv2advprojectszalaytamas.command.create.CreateCustomerCommand;
import com.example.javasv2advprojectszalaytamas.dto.CustomerDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

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
                .bodyValue(new CreateCustomerCommand("EmailAddress@Gmail.com", "ZipCode123", "Szondy street 162", "Budapest", "062012345678"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Long.class);
    }

    @Test
    @Order(2)
    void testCreateNewCustomerFailure() {
        webTestClient
                .post()
                .uri("api/customer")
                .bodyValue(new CreateCustomerCommand("", "ZipCode123", "Szondy street 162", "Budapest", "062012345678"))
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