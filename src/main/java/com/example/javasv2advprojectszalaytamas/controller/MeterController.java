package com.example.javasv2advprojectszalaytamas.controller;

import com.example.javasv2advprojectszalaytamas.command.create.CreateMeterCommand;
import com.example.javasv2advprojectszalaytamas.command.update.UpdateMeterCommand;
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


    @PostMapping("/{id}")
    @Operation(description = "Assign a new meter to a customer")
    public MeterDto createNewMeterForCustomerById(@PathVariable("id") Long id,@Valid @RequestBody CreateMeterCommand command) {
        return billingMeterService.createMeterForCustomerById(id,command);
    }

    @GetMapping
    @Operation(description = "return with all meters from database")
    public List<MeterDto> findAllMeters(){
        return billingMeterService.findAllMeters();
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
    @PutMapping("/{id}")
    @Operation(summary = "assign an other customer to a meter")
    public Long updateMeterWithNewCustomer(@PathVariable("id")Long id, UpdateMeterCommand command){
        return billingMeterService.updateMeterUser(id,command);
    }
}
