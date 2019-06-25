package com.danielsolawa.spaceflight.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TouristListDto {

    List<TouristDto> tourists;
    Integer size;
}
