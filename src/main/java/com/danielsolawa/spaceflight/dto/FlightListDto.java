package com.danielsolawa.spaceflight.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FlightListDto {

    private List<FlightDto> flights;
    private Integer size;
}
