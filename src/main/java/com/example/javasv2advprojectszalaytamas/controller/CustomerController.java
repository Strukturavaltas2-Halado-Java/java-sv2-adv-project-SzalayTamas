package com.example.javasv2advprojectszalaytamas.controller;

import com.example.javasv2advprojectszalaytamas.command.create.CreateCustomerCommand;
import com.example.javasv2advprojectszalaytamas.command.update.UpdateCustomerCommand;
import com.example.javasv2advprojectszalaytamas.dto.CustomerDto;
import com.example.javasv2advprojectszalaytamas.dto.InvoiceDto;
import com.example.javasv2advprojectszalaytamas.dto.MeterDto;
import com.example.javasv2advprojectszalaytamas.service.CustomerDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/customer")
@AllArgsConstructor
@Tag(name = "Operation on customer Entity")
public class CustomerController {

    private CustomerDetailsService customerDetailsService;

    @GetMapping
    @Operation(summary = "will return with all user", description = "will return with all user")
    public List<CustomerDto> findAllUser() {
        return customerDetailsService.findAllWithInvoice();
//        return customerDetailsService.findAllCustomer();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "customer has been created")
    @Operation(summary = "Create a new customer with contact details given", description = "email address,zipcode,address,city,phone number")
    public CustomerDto createCustomerWithContact(@Valid @RequestBody CreateCustomerCommand command) {
        return customerDetailsService.createNewCustomer(command);
    }

    @DeleteMapping
    @Operation(summary = "delete all customer from the database")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(responseCode = "204", description = "customer has been deleted")
    public void deleteAllCustomer() {
        customerDetailsService.deleteAllCustomer();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete all customer from the database")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(responseCode = "204", description = "customer has been deleted")
    public void deleteCustomerById(@PathVariable("id") Long id) {
        customerDetailsService.deleteCustomerById(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "will find and return a customer by id", description = "find a customer by id and return with it")
    public CustomerDto findCustomerById(@Parameter(description = "The id of the customer") @PathVariable("id") Long id) {
        return customerDetailsService.findCustomerById(id);
    }

    @PutMapping("/{id}/price")
    @Operation(summary = "update electricity price for customer")
    @ApiResponse(responseCode = "200", description = "update successful")
    public void updateCustomersPricePerKiloWatt(@PathVariable Long id, @Valid @RequestBody UpdateCustomerCommand command) {
        customerDetailsService.updateCustomersPrice(id, command);
    }

    @GetMapping("/{id}/invoices")
    @Operation(summary = "return customers invoice")
    public List<InvoiceDto> findCustomerAllInvoice(@PathVariable("id") Long id) {
        return customerDetailsService.findAllInvoicesByCustomerId(id);
    }

    @GetMapping("/{id}/meters")
    @Operation(summary = "return customers meters")
    public List<MeterDto> findCustomerAllMeter(@PathVariable("id") Long id) {
        return customerDetailsService.findMetersByCustomerId(id);
    }


}
