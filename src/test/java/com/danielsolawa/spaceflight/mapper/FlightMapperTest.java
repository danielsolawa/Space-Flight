package com.danielsolawa.spaceflight.mapper;

import com.danielsolawa.spaceflight.domain.Flight;
import com.danielsolawa.spaceflight.dto.FlightDto;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class FlightMapperTest {

    private FlightMapper flightMapper;

    @Before
    public void setUp() throws Exception {
        flightMapper = new FlightMapperImpl();
    }

    @Test
    public void mapToDto() {
        Flight dummyFlight = new Flight();
        dummyFlight.setId(1L);
        dummyFlight.setArrival(LocalDateTime.now().plusDays(5).plusHours(6));
        dummyFlight.setDeparture(LocalDateTime.now().plusDays(5).plusHours(2));
        dummyFlight.setNumberOfSeats(100);
        dummyFlight.setPrice(new BigDecimal("199.99"));

        FlightDto dummyFlightDto = flightMapper.MapToDto(dummyFlight);

        assertThat(dummyFlightDto, notNullValue());
    }

    @Test
    public void mapFromDto() {
        FlightDto dummyFlightDto = FlightDto.builder()
                                            .id(1L)
                                            .arrival(LocalDateTime.now().plusDays(5).plusHours(6))
                                            .departure(LocalDateTime.now().plusDays(5).plusHours(2))
                                            .numberOfSeats(100)
                                            .price(new BigDecimal("99.59")).build();
        Flight dummyFlight = flightMapper.MapFromDto(dummyFlightDto);

        assertThat(dummyFlight, notNullValue());
    }
}