package com.example.javasv2advprojectszalaytamas.controller;

import com.example.javasv2advprojectszalaytamas.command.create.CreateMeterCommand;
import com.example.javasv2advprojectszalaytamas.command.create.CreateMeterMeasurementCommand;
import com.example.javasv2advprojectszalaytamas.dto.MeterDto;
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
@RequestMapping("api/meter")
@AllArgsConstructor
@Tag(name = "Operation on meter Entity")
public class MeterController {

    private BillingMeterService billingMeterService;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Assign a new meter to a customer")
    public MeterDto createNewMeterForCustomerById(@Valid @RequestBody CreateMeterCommand command) {
        return billingMeterService.createMeterForCustomerById(command);
    }

    @PostMapping("/{id}")
    @Operation(summary = "Add a new Measurement to a meter")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "meter has been created")
    public MeterDto createNewMeterForCustomerById(@PathVariable("id") Long id, @Valid @RequestBody CreateMeterMeasurementCommand command) {
        return billingMeterService.createMeterMeasurement(id, command);
    }

    @GetMapping
    @Operation(summary = "return with all meters from database")
    public List<MeterDto> findAllMeters() {
        return billingMeterService.findAllMeters();
    }

    @GetMapping("/{id}")
    @Operation(summary = "will find a meter by its id")
    public MeterDto findMeterById(@PathVariable("id") Long id) {
        return billingMeterService.findMeterById(id);
    }

    @DeleteMapping
    @Operation(summary = "delete all meter from the database")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(responseCode = "204", description = "meters has been deleted")
    public void deleteAllMeter() {
        billingMeterService.deleteAllMeter();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a meter from the database")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(responseCode = "204", description = "meters has been deleted")
    public void deleteOneMeterById(@PathVariable("id") Long id) {
        billingMeterService.deleteMeterById(id);
    }
}
