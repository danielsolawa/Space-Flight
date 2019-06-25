package com.danielsolawa.spaceflight.mapper;

import com.danielsolawa.spaceflight.domain.Flight;
import com.danielsolawa.spaceflight.dto.FlightDto;
import org.springframework.stereotype.Component;

@Component
public class FlightMapperImpl implements FlightMapper {

    @Override
    public FlightDto MapToDto(Flight flight) {
        return null;
    }

    @Override
    public Flight MapFromDto(FlightDto flightDto) {
        return null;
    }
}
