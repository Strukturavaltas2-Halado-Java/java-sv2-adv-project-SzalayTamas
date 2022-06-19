package com.example.javasv2advprojectszalaytamas.command.delete;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class DeleteCustomerCommand {
    @NotBlank
    @Schema(description = "id of customer to delete", example = "1")
    private long customerId;
}
