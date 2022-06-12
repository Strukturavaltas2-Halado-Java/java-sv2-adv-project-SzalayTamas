package com.example.javasv2advprojectszalaytamas.command.update;

import com.example.javasv2advprojectszalaytamas.dto.ContactDto;
import com.example.javasv2advprojectszalaytamas.dto.MeterDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class UpdateCustomerCommand {

    private ContactDto contact;
    @Positive
    private double pricePerKiloWatt;

}
