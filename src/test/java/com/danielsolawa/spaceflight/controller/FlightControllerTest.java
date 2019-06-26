package com.danielsolawa.spaceflight.controller;

import com.danielsolawa.spaceflight.command.AddTouristCommand;
import com.danielsolawa.spaceflight.command.CreateFlightCommand;
import com.danielsolawa.spaceflight.command.UpdateFlightCommand;
import com.danielsolawa.spaceflight.dto.FlightDto;
import com.danielsolawa.spaceflight.service.FlightService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FlightControllerTest extends AbstractControllerTest{

    private MockMvc mockMvc;

    private FlightController flightController;

    @Mock
    private FlightService flightService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        flightController = new FlightController(flightService);
        mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
    }

    @Test
    public void getAllFLightsSuccessTest() throws Exception {
        List<FlightDto> dummyFlightDtoList = Arrays.asList(new FlightDto(), new FlightDto());

        // flightService.getAll should return a list of flight dtos.
        given(flightService.getAll()).willReturn(dummyFlightDtoList);


        mockMvc.perform(get(flightController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flights", hasSize(2)))
                .andExpect(jsonPath("$.size", equalTo(2)));

        // flightService.getAll should be invoked.
        then(flightService).should().getAll();
    }

    @Test
    public void getFlightByIdSuccessTest() throws Exception {
        FlightDto dummyFlightDto = FlightDto.builder().id(1L).build();

        // flightService.getById should return an object with the given id.
        given(flightService.getById(anyLong())).willReturn(dummyFlightDto);

        mockMvc.perform(get(flightController.BASE_URL +"/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

        // flightService.getById should be invoked.
        then(flightService).should().getById(anyLong());


    }

    @Test
    public void createSuccessTest() throws Exception {
        CreateFlightCommand dummyCreateFlightCommand = CreateFlightCommand.builder().build();

        mockMvc.perform(post(flightController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(dummyCreateFlightCommand)))
                .andExpect(status().isCreated());

        // flightService.create should be invoked.
        then(flightService).should().create(ArgumentMatchers.any(CreateFlightCommand.class));

    }

    @Test
    public void updateSuccessTest() throws Exception {
       UpdateFlightCommand dummyUpdateFlightCommand =
                UpdateFlightCommand.builder().price(new BigDecimal("122")).build();

        mockMvc.perform(put(flightController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(dummyUpdateFlightCommand)))
                .andExpect(status().isOk());

        // flightService.update should be invoked.
        then(flightService).should().update( ArgumentMatchers.any(UpdateFlightCommand.class), anyLong());


    }

    @Test
    public void addTouristToListSuccessTest() throws Exception {
        AddTouristCommand dummyTourist = AddTouristCommand.builder().id(1L).build();

        mockMvc.perform(put(flightController.BASE_URL + "/1/add-tourist")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(dummyTourist)))
                .andExpect(status().isOk());


        // flightService.update should be invoked.
        then(flightService).should().addTouristToList(anyLong(), anyLong());


    }

    @Test
    public void deleteSuccessTest() throws Exception {

        mockMvc.perform(delete(flightController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // flightService.delete should be invoked.
        then(flightService).should().delete(anyLong());

    }
}