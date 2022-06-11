package com.example.javasv2advprojectszalaytamas.controller;

import com.example.javasv2advprojectszalaytamas.command.create.CreateMeterCommand;
import com.example.javasv2advprojectszalaytamas.dto.MeterDto;
import com.example.javasv2advprojectszalaytamas.service.BillingMeterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/meter")
@AllArgsConstructor
public class MeterController {

    private BillingMeterService billingMeterService;


    @PostMapping("/{id}")
    public MeterDto createNewMeterForCustomerById(@PathVariable("id") Long id,@Valid @RequestBody CreateMeterCommand command) {
        return billingMeterService.createMeterForCustomerById(id,command);
    }

}
