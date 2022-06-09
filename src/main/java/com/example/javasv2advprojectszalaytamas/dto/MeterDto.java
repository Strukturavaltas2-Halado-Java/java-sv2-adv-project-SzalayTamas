package com.example.javasv2advprojectszalaytamas.dto;

import com.example.javasv2advprojectszalaytamas.entity.Measurement;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import java.util.List;

public class MeterDto {
    private Long id;
    private List<Measurement> measurements;
    private UserDto userDto;
}
