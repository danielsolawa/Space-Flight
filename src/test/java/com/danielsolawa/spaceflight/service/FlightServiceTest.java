package com.danielsolawa.spaceflight.service;

import com.danielsolawa.spaceflight.command.CreateFlightCommand;
import com.danielsolawa.spaceflight.command.UpdateFlightCommand;
import com.danielsolawa.spaceflight.domain.Flight;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
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
        Optional<Flight> optionalFlight = Optional.of(dummyFlight);
        dummyFlight.setTourists(new ArrayList<>());

        // touristRepository.findById should return a dummy tourist object;
        given(touristRepository.findById(anyLong()))
                .willReturn(Optional.of(TouristServiceTest.getDummyTourist()));
        // flightRepository.findById should return a dummy flight object;
        given(flightRepository
                .findById(anyLong())).willReturn(optionalFlight);

        service.addTouristToList(1L, 1L);

        // touristRepository.findById should be invoked.
        then(touristRepository).should().findById(anyLong());
        // flightRepository.findById should be invoked.
        then(flightRepository).should().findById(anyLong());
        // flightRepository.save should be invoked.
        then(flightRepository).should().save(ArgumentMatchers.any(Flight.class));
    }

    @Test
    public void removeTouristToListSuccessTest(){
        Flight dummyFlight = flightMapper.MapFromDto(getDummyFlightDto());
        Optional<Flight> optionalFlight = Optional.of(dummyFlight);
        dummyFlight.setTourists(new ArrayList<>());

        // touristRepository.findById should return a dummy tourist object;
        given(touristRepository.findById(anyLong()))
                .willReturn(Optional.of(TouristServiceTest.getDummyTourist()));
        // flightRepository.findById should return a dummy flight object;
        given(flightRepository
                .findById(anyLong())).willReturn(optionalFlight);

        service.removeTouristFromTheList(1L, 1L);

        // touristRepository.findById should be invoked.
        then(touristRepository).should().findById(anyLong());
        // flightRepository.findById should be invoked.
        then(flightRepository).should().findById(anyLong());
        // flightRepository.save should be invoked.
        then(flightRepository).should().save(ArgumentMatchers.any(Flight.class));
    }

    @Test
    public void getByIdSuccessTest() {
        Flight dummyFlight = flightMapper.MapFromDto(getDummyFlightDto());
        Optional<Flight> optionalFlight = Optional.of(dummyFlight);

        // flightRepository.findById should return a dummy flight object;
        given(flightRepository.findById(anyLong())).willReturn(optionalFlight);

        FlightDto resultFlightDto = service.getById(1L);

        assertThat(resultFlightDto, notNullValue());
        assertThat(resultFlightDto.getId(), equalTo(dummyFlight.getId()));
        assertThat(resultFlightDto.getDeparture(), equalTo(dummyFlight.getDeparture()));
        assertThat(resultFlightDto.getArrival(), equalTo(dummyFlight.getArrival()));
        assertThat(resultFlightDto.getPrice(), equalTo(dummyFlight.getPrice()));

        // flightRepository.getOne should be invoked.
        then(flightRepository).should().findById(anyLong());
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
        Optional<Flight> optionalFlightDto =Optional.of(flightMapper.MapFromDto(dummyFlight));

        UpdateFlightCommand command =
                UpdateFlightCommand.builder()
                        .arrival(LocalDateTime.now())
                        .departure(LocalDateTime.now().minusHours(2))
                        .price(new BigDecimal("122.00"))
                        .numberOfSeats(100).build();

        // flightRepository.findById should return a dummy flight object;
        given(flightRepository.findById(anyLong())).willReturn(optionalFlightDto);

        service.update(command, 1L);

        // flightRepository.findById should be invoked.
        then(flightRepository).should().findById(anyLong());
        // flightRepository.save should be invoked.
        then(flightRepository).should().save(ArgumentMatchers.any(Flight.class));
    }

    @Test
    public void deleteSuccessTest() {
        Flight dummyFlight = flightMapper.MapFromDto(getDummyFlightDto());
        Optional<Flight> optionalFlight = Optional.of(dummyFlight);

        // flightRepository.findById should return a dummy flight object;
        given(flightRepository.findById(anyLong())).willReturn(optionalFlight);


        service.delete(1L);


        // flightRepository.findById should be invoked.
        then(flightRepository).should().findById(anyLong());
        // flightRepository.save should be invoked.
        then(flightRepository).should().deleteById(anyLong());
    }

    private static FlightDto getDummyFlightDto(){
        return FlightDto.builder()
                .id(1L)
                .arrival(LocalDateTime.now().plusDays(5).plusHours(6))
                .departure(LocalDateTime.now().plusDays(5).plusHours(2))
                .numberOfSeats(100)
                .price(new BigDecimal("99.59"))
                .tourists(new ArrayList<>()).build();
    }

    private static List<FlightDto> getDummyListDto() {
        List<FlightDto> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            list.add(getDummyFlightDto());
        }

        return list;
    }
}