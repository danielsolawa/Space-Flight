package com.danielsolawa.spaceflight.service;

import com.danielsolawa.spaceflight.domain.Flight;
import com.danielsolawa.spaceflight.dto.FlightDto;
import com.danielsolawa.spaceflight.mapper.FlightMapper;
import com.danielsolawa.spaceflight.mapper.FlightMapperImpl;
import com.danielsolawa.spaceflight.repository.FlightRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

public class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    private FlightMapper flightMapper;

    private FlightService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        flightMapper = new FlightMapperImpl();
        service = new FlightServiceImpl(flightRepository, flightMapper);
    }

    @Test
    public void create() {
        FlightDto dummyFlightDto = getDummyFlightDto();
        service.create(dummyFlightDto);

        then(flightRepository).should().save(ArgumentMatchers.any(Flight.class));

    }

    @Test
    public void getById() {
        Flight dummyFlight = flightMapper.MapFromDto(getDummyFlightDto());

        given(flightRepository.getOne(anyLong())).willReturn(dummyFlight);

        FlightDto resultFlightDto = service.getById(1L);

        assertThat(resultFlightDto, notNullValue());
        assertThat(resultFlightDto.getId(), equalTo(dummyFlight.getId()));
        assertThat(resultFlightDto.getDeparture(), equalTo(dummyFlight.getDeparture()));
        assertThat(resultFlightDto.getArrival(), equalTo(dummyFlight.getArrival()));
        assertThat(resultFlightDto.getPrice(), equalTo(dummyFlight.getPrice()));


        then(flightRepository).should().getOne(anyLong());
    }

    @Test
    public void getAll() {
        List<Flight> dummyFlights = getDummyListDto().stream()
                                                .map(flightMapper::MapFromDto)
                                                .collect(Collectors.toList());

        given(flightRepository.findAll()).willReturn(dummyFlights);

        List<FlightDto> resultFlightDto = service.getAll();

        assertThat(resultFlightDto, notNullValue());
        assertThat(resultFlightDto.size(), equalTo(dummyFlights.size()));

        then(flightRepository).should().findAll();


    }

    @Test
    public void update() {
        FlightDto dummyFlight = getDummyFlightDto();

        given(flightRepository.getOne(anyLong())).willReturn(flightMapper.MapFromDto(dummyFlight));

        service.update(dummyFlight, 1L);

        then(flightRepository).should().getOne(anyLong());
        then(flightRepository).should().save(ArgumentMatchers.any(Flight.class));
    }

    @Test
    public void delete() {
        service.delete(1L);

        then(flightRepository).should().deleteById(anyLong());
    }

    private static FlightDto getDummyFlightDto(){
        return FlightDto.builder()
                .id(1L)
                .arrival(LocalDateTime.now().plusDays(5).plusHours(6))
                .departure(LocalDateTime.now().plusDays(5).plusHours(2))
                .numberOfSeats(100)
                .price(new BigDecimal("99.59")).build();
    }

    private static List<FlightDto> getDummyListDto() {
        List<FlightDto> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            list.add(getDummyFlightDto());
        }

        return list;
    }
}