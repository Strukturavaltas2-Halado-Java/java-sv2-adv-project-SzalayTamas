package com.example.javasv2advprojectszalaytamas.dto;

import com.example.javasv2advprojectszalaytamas.entity.Measurement;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MeterDto {
    @Schema(description = "Unique identifier of the meter" , example = "1")
    private Long id;
    @Schema(description = "The meter belongs to this user")
    private Long customer;
    @Schema(description = "List of used electricity measurements with dates")
    private List<Measurement> measurements;
}
