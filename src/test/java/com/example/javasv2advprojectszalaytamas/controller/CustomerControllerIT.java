package com.example.javasv2advprojectszalaytamas.controller;

import com.example.javasv2advprojectszalaytamas.command.create.CreateCustomerCommand;
import com.example.javasv2advprojectszalaytamas.command.create.CreateInvoiceCommand;
import com.example.javasv2advprojectszalaytamas.command.create.CreateMeterCommand;
import com.example.javasv2advprojectszalaytamas.command.update.UpdateCustomerCommand;
import com.example.javasv2advprojectszalaytamas.command.update.UpdateCustomerPriceCommand;
import com.example.javasv2advprojectszalaytamas.command.update.UpdateInvoiceStatusCommand;
import com.example.javasv2advprojectszalaytamas.dto.ContactDto;
import com.example.javasv2advprojectszalaytamas.dto.CustomerDto;
import com.example.javasv2advprojectszalaytamas.dto.InvoiceDto;
import com.example.javasv2advprojectszalaytamas.dto.MeterDto;
import com.example.javasv2advprojectszalaytamas.entity.Contact;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerIT {

    @Autowired
    WebTestClient webTestClient;

    @Test
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
    void testCreateNewCustomerFailure() {
        webTestClient
                .post()
                .uri("api/customer")
                .bodyValue(new CreateCustomerCommand("", "ZipCode123", "Szondy street 162", "Budapest", "062012345678", 1000))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testFindAllCustomer() {

        webTestClient.delete().uri("api/customer").exchange();

        webTestClient
                .post()
                .uri("api/customer")
                .bodyValue(new CreateCustomerCommand("EmailAddress@Gmail.com", "ZipCode123", "Szondy street 162", "Budapest", "062012345678", 1200))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CustomerDto.class).value(e -> assertThat(e.getContact().getEmail()).isEqualTo("EmailAddress@Gmail.com")
                );

        webTestClient.get().uri("api/customer")
                .exchange()
                .expectBodyList(CustomerDto.class)
                .hasSize(1);
    }

    @Test
    void testCreateMeter() {
        webTestClient.delete().uri("api/customer").exchange();
        webTestClient.delete().uri("api/meter").exchange();

        CustomerDto customerDto = webTestClient
                .post()
                .uri("api/customer")
                .bodyValue(new CreateCustomerCommand("EmailAddress@Gmail.com", "ZipCode123", "Szondy street 162", "Budapest", "062012345678", 1200))
                .exchange().expectBody(CustomerDto.class).returnResult().getResponseBody();

        MeterDto meterDto =
                webTestClient.post().uri("api/meter")
                        .bodyValue(new CreateMeterCommand(customerDto.getId(), LocalDateTime.now(), 0))
                        .exchange().expectStatus().isCreated().expectBody(MeterDto.class).returnResult().getResponseBody();
    }

    @Test
    void testCreateMeterFailure() {
        webTestClient.delete().uri("api/customer").exchange();
        webTestClient.delete().uri("api/meter").exchange();

        CustomerDto customerDto = webTestClient
                .post()
                .uri("api/customer")
                .bodyValue(new CreateCustomerCommand("EmailAddress@Gmail.com", "ZipCode123", "Szondy street 162", "Budapest", "062012345678", 1200))
                .exchange().expectBody(CustomerDto.class).returnResult().getResponseBody();


        webTestClient.post().uri("api/meter")
                .bodyValue(new CreateMeterCommand(customerDto.getId() + 10000, LocalDateTime.now(), 0))
                .exchange().expectStatus().isNotFound();
    }

    @Test
    void testCreateInvoice() {
        webTestClient.delete().uri("api/customer").exchange();
        webTestClient.delete().uri("api/meter").exchange();
        webTestClient.delete().uri("api/invoice").exchange();

        CustomerDto customerDto = webTestClient
                .post()
                .uri("api/customer")
                .bodyValue(new CreateCustomerCommand("EmailAddress@Gmail.com", "ZipCode123", "Szondy street 162", "Budapest", "062012345678", 1200))
                .exchange().expectBody(CustomerDto.class).returnResult().getResponseBody();
        MeterDto meterDto = webTestClient.post().uri("api/meter")
                .bodyValue(new CreateMeterCommand(customerDto.getId(), LocalDateTime.now(), 0))
                .exchange().expectBody(MeterDto.class).returnResult().getResponseBody();

        webTestClient.post().uri("api/invoice")
                .bodyValue(new CreateInvoiceCommand(customerDto.getId(), 200))
                .exchange().expectStatus().isCreated();
    }

    @Test
    void testCreateInvoiceFailure() {
        webTestClient.delete().uri("api/customer").exchange();
        webTestClient.delete().uri("api/meter").exchange();
        webTestClient.delete().uri("api/invoice").exchange();

        CustomerDto customerDto = webTestClient
                .post()
                .uri("api/customer")
                .bodyValue(new CreateCustomerCommand("EmailAddress@Gmail.com", "ZipCode123", "Szondy street 162", "Budapest", "062012345678", 1200))
                .exchange().expectBody(CustomerDto.class).returnResult().getResponseBody();
        MeterDto meterDto = webTestClient.post().uri("api/meter")
                .bodyValue(new CreateMeterCommand(customerDto.getId(), LocalDateTime.now(), 0))
                .exchange().expectBody(MeterDto.class).returnResult().getResponseBody();

        webTestClient.post().uri("api/invoice")
                .bodyValue(new CreateInvoiceCommand(customerDto.getId() + 1000, 200))
                .exchange().expectStatus().isNotFound();
    }

    @Test
    void testUpdateStatusInvoice() {
        webTestClient.delete().uri("api/customer").exchange();
        webTestClient.delete().uri("api/meter").exchange();
        webTestClient.delete().uri("api/invoice").exchange();

        CustomerDto customerDto = webTestClient
                .post()
                .uri("api/customer")
                .bodyValue(new CreateCustomerCommand("EmailAddress@Gmail.com", "ZipCode123", "Szondy street 162", "Budapest", "062012345678", 1200))
                .exchange().expectBody(CustomerDto.class).returnResult().getResponseBody();
        MeterDto meterDto = webTestClient.post().uri("api/meter")
                .bodyValue(new CreateMeterCommand(customerDto.getId(), LocalDateTime.now(), 0))
                .exchange().expectBody(MeterDto.class).returnResult().getResponseBody();

        InvoiceDto invoiceDto = webTestClient.post().uri("api/invoice")
                .bodyValue(new CreateInvoiceCommand(customerDto.getId(), 200))
                .exchange().expectStatus().isCreated().expectBody(InvoiceDto.class).returnResult().getResponseBody();
        webTestClient.put().uri(
                        uriBuilder -> uriBuilder.path("api/invoice/{id}/status")
                                .build(invoiceDto.getId()))
                .bodyValue(new UpdateInvoiceStatusCommand("Paid"))
                .exchange().expectStatus().isAccepted();
    }

    @Test
    void testUpdateStatusInvoiceFailure() {
        webTestClient.delete().uri("api/customer").exchange();
        webTestClient.delete().uri("api/meter").exchange();
        webTestClient.delete().uri("api/invoice").exchange();

        CustomerDto customerDto = webTestClient
                .post()
                .uri("api/customer")
                .bodyValue(new CreateCustomerCommand("EmailAddress@Gmail.com", "ZipCode123", "Szondy street 162", "Budapest", "062012345678", 1200))
                .exchange().expectBody(CustomerDto.class).returnResult().getResponseBody();
        MeterDto meterDto = webTestClient.post().uri("api/meter")
                .bodyValue(new CreateMeterCommand(customerDto.getId(), LocalDateTime.now(), 0))
                .exchange().expectBody(MeterDto.class).returnResult().getResponseBody();

        InvoiceDto invoiceDto = webTestClient.post().uri("api/invoice")
                .bodyValue(new CreateInvoiceCommand(customerDto.getId(), 200))
                .exchange().expectStatus().isCreated().expectBody(InvoiceDto.class).returnResult().getResponseBody();
        webTestClient.put().uri(
                        uriBuilder -> uriBuilder.path("api/invoice/{id}/status")
                                .build(invoiceDto.getId()))
                .bodyValue(new UpdateInvoiceStatusCommand("Pair"))
                .exchange().expectStatus().is4xxClientError();
    }

    @Test
    void testCustomerUpdate() {
        webTestClient.delete().uri("api/customer").exchange();
        CustomerDto customerDto = webTestClient
                .post()
                .uri("api/customer")
                .bodyValue(new CreateCustomerCommand("EmailAddress@Gmail.com", "ZipCode123", "Szondy street 162", "Budapest", "062012345678", 1200))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CustomerDto.class).value(e -> assertThat(e.getContact().getEmail()).isEqualTo("EmailAddress@Gmail.com")
                ).returnResult().getResponseBody();
        webTestClient.put().uri(uriBuilder -> uriBuilder.path("api/customer/{id}/price").build(customerDto.getId()))
                .bodyValue(new UpdateCustomerPriceCommand(11)).exchange()
                .expectStatus().isAccepted();

        webTestClient.put().uri(uriBuilder -> uriBuilder.path("api/customer/{id}/contact").build(customerDto.getId()))
                .bodyValue(new UpdateCustomerCommand("brandNewEmailAddress@fastmail.com","0630547254")).exchange()
                .expectStatus().isAccepted();

    }
}