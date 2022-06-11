package com.example.javasv2advprojectszalaytamas.controller;

import com.example.javasv2advprojectszalaytamas.command.create.CreateMeterCommand;
import com.example.javasv2advprojectszalaytamas.dto.MeterDto;
import com.example.javasv2advprojectszalaytamas.service.BillingMeterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/meter")
@AllArgsConstructor
@Tag(name = "Operation on meter Entity")
public class MeterController {

    private BillingMeterService billingMeterService;


    @PostMapping("/{id}")
    @Operation(description = "Assign a new meter to a customer")
    public MeterDto createNewMeterForCustomerById(@PathVariable("id") Long id,@Valid @RequestBody CreateMeterCommand command) {
        return billingMeterService.createMeterForCustomerById(id,command);
    }

}
