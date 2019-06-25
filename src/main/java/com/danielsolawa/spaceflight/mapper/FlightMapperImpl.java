package com.danielsolawa.spaceflight.mapper;

import com.danielsolawa.spaceflight.domain.Flight;
import com.danielsolawa.spaceflight.dto.FlightDto;
import org.springframework.stereotype.Component;

@Component
public class FlightMapperImpl implements FlightMapper {

    @Override
    public FlightDto MapToDto(Flight flight) {

        return FlightDto.builder()
                        .id(flight.getId())
                        .arrival(flight.getArrival())
                        .departure(flight.getDeparture())
                        .numberOfSeats(flight.getNumberOfSeats())
                        .tourists(flight.getTourists())
                        .price(flight.getPrice()).build();
    }

    @Override
    public Flight MapFromDto(FlightDto flightDto) {
        Flight flight = new Flight();
        flight.setId(flightDto.getId());
        flight.setArrival(flightDto.getArrival());
        flight.setDeparture(flightDto.getDeparture());
        flight.setNumberOfSeats(flightDto.getNumberOfSeats());
        flight.setTourists(flightDto.getTourists());
        flight.setPrice(flightDto.getPrice());


        return flight;
    }
}
