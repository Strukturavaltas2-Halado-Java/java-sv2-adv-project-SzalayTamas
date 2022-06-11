package com.example.javasv2advprojectszalaytamas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NotBlank
public class ContactDto {
    @Schema(description = "Email address of the customer" , example = "newcustoemr@zigzag.com")
    private String email;
    @Schema(description = "Customer's zipcode", example = "JASD12321D")
    private String zipCode;
    @Schema(description = "The address of customer", example = "Kolmi Street 7,Floor 12, Door 5")
    private String address;
    @Schema(description = "Customer's city", example = "Budapest")
    private String town;
    @Schema(description = "Customer's phone number", example = "0620578154")
    private String phoneNumber;
}
