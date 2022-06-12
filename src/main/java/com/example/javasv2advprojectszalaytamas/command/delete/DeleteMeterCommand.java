package com.example.javasv2advprojectszalaytamas.command.delete;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class DeleteMeterCommand {
    @Schema(description = "id of meter to delete")
    @NotBlank
    private Long meterId;
}
