package com.danielsolawa.spaceflight.service;

import com.danielsolawa.spaceflight.command.CreateFlightCommand;
import com.danielsolawa.spaceflight.command.CreateTouristCommand;
import com.danielsolawa.spaceflight.command.UpdateFlightCommand;
import com.danielsolawa.spaceflight.domain.Flight;
import com.danielsolawa.spaceflight.domain.Gender;
import com.danielsolawa.spaceflight.domain.Tourist;
import com.danielsolawa.spaceflight.dto.FlightDto;
import com.danielsolawa.spaceflight.mapper.FlightMapper;
import com.danielsolawa.spaceflight.mapper.FlightMapperImpl;
import com.danielsolawa.spaceflight.repository.FlightRepository;
import com.danielsolawa.spaceflight.repository.TouristRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Mock
    private TouristRepository touristRepository;

    private FlightMapper flightMapper;

    private FlightService service;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        flightMapper = new FlightMapperImpl();
        service = new FlightServiceImpl(flightRepository, touristRepository, flightMapper);
    }

    @Test
    public void createSuccessTest() {
        CreateFlightCommand command =
                CreateFlightCommand.builder()
                                    .arrival(LocalDateTime.now())
                                    .departure(LocalDateTime.now().minusHours(2))
                                    .price(new BigDecimal("122.00"))
                                    .numberOfSeats(100).build();


        service.create(command);

        // flightRepository.save should be invoked.
        then(flightRepository).should().save(ArgumentMatchers.any(Flight.class));

    }

    @Test
    public void addTouristToListSuccessTest(){
        Flight dummyFlight = flightMapper.MapFromDto(getDummyFlightDto());
        dummyFlight.setTourists(new ArrayList<>());

        // touristRepository.getOne should return a dummy tourist object;
        given(touristRepository.getOne(anyLong()))
                .willReturn(TouristServiceTest.getDummyTourist());
        // flightRepository.getOne should return a dummy flight object;
        given(flightRepository
                .getOne(anyLong())).willReturn(dummyFlight);

        service.addTouristToList(1L, 1L);

        // touristRepository.getOne should be invoked.
        then(touristRepository).should().getOne(anyLong());
        // flightRepository.getOne should be invoked.
        then(flightRepository).should().getOne(anyLong());
        // flightRepository.save should be invoked.
        then(flightRepository).should().save(ArgumentMatchers.any(Flight.class));
    }

    @Test
    public void getByIdSuccessTest() {
        Flight dummyFlight = flightMapper.MapFromDto(getDummyFlightDto());

        // flightRepository.getOne should return a dummy flight object;
        given(flightRepository.getOne(anyLong())).willReturn(dummyFlight);

        FlightDto resultFlightDto = service.getById(1L);

        assertThat(resultFlightDto, notNullValue());
        assertThat(resultFlightDto.getId(), equalTo(dummyFlight.getId()));
        assertThat(resultFlightDto.getDeparture(), equalTo(dummyFlight.getDeparture()));
        assertThat(resultFlightDto.getArrival(), equalTo(dummyFlight.getArrival()));
        assertThat(resultFlightDto.getPrice(), equalTo(dummyFlight.getPrice()));

        // flightRepository.getOne should be invoked.
        then(flightRepository).should().getOne(anyLong());
    }

    @Test
    public void getAllSuccessTest() {
        List<Flight> dummyFlights = getDummyListDto().stream()
                                                .map(flightMapper::MapFromDto)
                                                .collect(Collectors.toList());

        // flightRepository.findAll should return a list of dummy flight objects;
        given(flightRepository.findAll()).willReturn(dummyFlights);

        List<FlightDto> resultFlightDtos = service.getAll();

        assertThat(resultFlightDtos, notNullValue());
        assertThat(resultFlightDtos.size(), equalTo(dummyFlights.size()));

        // flightRepository.findAll should be invoked.
        then(flightRepository).should().findAll();


    }

    @Test
    public void updateSuccessTest() {
        FlightDto dummyFlight = getDummyFlightDto();


        UpdateFlightCommand command =
                UpdateFlightCommand.builder()
                        .arrival(LocalDateTime.now())
                        .departure(LocalDateTime.now().minusHours(2))
                        .price(new BigDecimal("122.00"))
                        .numberOfSeats(100).build();

        // flightRepository.getOne should return a dummy flight object;
        given(flightRepository.getOne(anyLong())).willReturn(flightMapper.MapFromDto(dummyFlight));

        service.update(command, 1L);

        // flightRepository.getOne should be invoked.
        then(flightRepository).should().getOne(anyLong());
        // flightRepository.save should be invoked.
        then(flightRepository).should().save(ArgumentMatchers.any(Flight.class));
    }

    @Test
    public void deleteSuccessTest() {
        service.delete(1L);


        // flightRepository.save should be invoked.
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