package com.example.javasv2advprojectszalaytamas.command.update;

import com.example.javasv2advprojectszalaytamas.dto.ContactDto;
import com.example.javasv2advprojectszalaytamas.dto.MeterDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class UpdateCustomerCommand {

    @NotBlank
    @Email
    @Schema(description = "Email address of the new customer", example = "newcustomer@fastmail.com")
    private String email;
    @NotBlank
    @Schema(description = "Phone number of the customer", example = "867895124")
    private String phoneNumber;
}
