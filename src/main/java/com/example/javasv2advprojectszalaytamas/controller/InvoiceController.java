package com.example.javasv2advprojectszalaytamas.controller;

import com.example.javasv2advprojectszalaytamas.command.create.CreateInvoiceCommand;
import com.example.javasv2advprojectszalaytamas.command.update.UpdateInvoiceCommand;
import com.example.javasv2advprojectszalaytamas.dto.InvoiceDto;
import com.example.javasv2advprojectszalaytamas.service.BillingMeterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/invoice")
@Tag(name = "Operation on invoice Entity")
@AllArgsConstructor
public class InvoiceController {
    private BillingMeterService billingMeterService;

    @GetMapping
    public List<InvoiceDto> findAllInvoice() {
        return billingMeterService.findAllInvoice();
    }

    @PostMapping
    public InvoiceDto createInvoiceByCustomerId(@RequestBody CreateInvoiceCommand command) {
        return billingMeterService.createInvoiceByCustomerId(command);
    }

    @PostMapping("/{id}")
    public InvoiceDto createInvoiceBasedOnMeasuredElectricityUsage(@PathVariable("id") Long id) {
        return billingMeterService.createInvoiceFromUsage(id);
    }
}
