package com.example.javasv2advprojectszalaytamas.controller;

import com.example.javasv2advprojectszalaytamas.command.create.CreateInvoiceCommand;
import com.example.javasv2advprojectszalaytamas.command.update.UpdateInvoiceCommand;
import com.example.javasv2advprojectszalaytamas.dto.InvoiceDto;
import com.example.javasv2advprojectszalaytamas.service.BillingMeterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/invoice")
@Tag(name = "Operation on invoice Entity")
@AllArgsConstructor
public class InvoiceController {
    private BillingMeterService billingMeterService;

    @GetMapping
    @Operation(summary = "Return with all the invoices")
    public List<InvoiceDto> findAllInvoice() {
        return billingMeterService.findAllInvoice();
    }

    @PostMapping
    @Operation(summary = "Create an invoice for a customer by id and used electricity given.")
    public InvoiceDto createInvoiceByCustomerId(@Valid @RequestBody CreateInvoiceCommand command) {
        return billingMeterService.createInvoiceByCustomerId(command);
    }

    @PostMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an invoice for customer based on the last measurement on the meter")
    public InvoiceDto createInvoiceBasedOnMeasuredElectricityUsage(@PathVariable("id") Long id) {
        return billingMeterService.createInvoiceFromUsage(id);
    }

    @DeleteMapping
    @Operation(summary = "delete all invoice from the database")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(responseCode = "204", description = "invoices has been deleted")
    public void deleteAllCustomer() {
        billingMeterService.deleteAllInvoice();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete an invoice from the database")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(responseCode = "204", description = "invoice has been deleted")
    public void deleteInvoiceById(@PathVariable("id") Long id) {
        billingMeterService.deleteInvoiceById(id);
    }

    @PutMapping("/price")
    @Operation(summary = "Update the price for electricity in the invoice for customer ")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Long updatePriceForCustomerInvoice(@Valid @RequestBody UpdateInvoiceCommand command) {
        return billingMeterService.updatePriceForCustomerInvoice(command);
    }

    @PutMapping("/debt")
    @Operation(summary = "Update the debt in the invoice for customer ")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Long updateDebtForCustomerInvoice(@Valid @RequestBody UpdateInvoiceCommand command) {
        return billingMeterService.updateDebtInInvoice(command);
    }
}
