package com.example.javasv2advprojectszalaytamas.command.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapping;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerCommand {
    @NotBlank
    @Email
    @Schema(description = "Email address of the new customer", example = "newcustomer@fastmail.com")
    private String email;
    @NotBlank
    @Schema(description = "Zipcode of the new customer", example = "GZHD12P")
    private String zipCode;
    @NotBlank
    @Schema(description = "Street,house number,floor and other details of the customer ", example = "Szondy street 69, 2nd floor, 12th door")
    private String address;
    @NotBlank
    @Schema(description = "The City customer living in", example = "Budapest")
    private String town;
    @NotBlank
    @Schema(description = "Phone number of the customer", example = "867895124")
    private String phoneNumber;
    @NotNull
    @Schema(description = "price for electricity", example = "1000")
    private double pricePerKiloWatt;
}
