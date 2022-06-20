package com.example.javasv2advprojectszalaytamas.controller;

import com.example.javasv2advprojectszalaytamas.command.create.CreateInvoiceCommand;
import com.example.javasv2advprojectszalaytamas.command.update.UpdateInvoiceStatusCommand;
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
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "invoice has been created")
    @Operation(summary = "Create an invoice for a customer by id and used electricity given.")
    public InvoiceDto createInvoiceByCustomerId(@Valid @RequestBody CreateInvoiceCommand command) {
        return billingMeterService.createInvoiceByCustomerId(command);
    }

    @GetMapping("/{id}")
    @Operation(summary = "will find an invoice by its id")
    public InvoiceDto findInvoiceById(@PathVariable("id") Long id) {
        return billingMeterService.findInvoiceById(id);
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

    @PutMapping("/{id}/status")
    @Operation(summary = "Update the status of the Invoice Can be Pending or Paid ")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiResponse(responseCode = "200", description = "update successful")
    public Long updatePriceForCustomerInvoice(@PathVariable("id") Long id, @Valid @RequestBody UpdateInvoiceStatusCommand command) {
        return billingMeterService.updateStatusOfTheInvoice(id, command);
    }
}
