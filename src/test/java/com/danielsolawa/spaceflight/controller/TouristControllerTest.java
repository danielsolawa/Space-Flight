package com.danielsolawa.spaceflight.controller;

import com.danielsolawa.spaceflight.command.CreateTouristCommand;
import com.danielsolawa.spaceflight.command.UpdateTouristCommand;
import com.danielsolawa.spaceflight.dto.TouristDto;
import com.danielsolawa.spaceflight.service.TouristService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

public class TouristControllerTest extends AbstractControllerTest{

    private MockMvc mockMvc;

    private TouristController touristController ;

    @Mock
    private TouristService touristService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        touristController = new TouristController(touristService);
        mockMvc = MockMvcBuilders.standaloneSetup(touristController).build();
    }

    @Test
    public void getAllTouristsSuccessTest() throws Exception {
        List<TouristDto> dummyTouristDtoList = Arrays.asList(new TouristDto(), new TouristDto(), new TouristDto());

        // touristService.getAll should return a list of tourist dtos.
        given(touristService.getAll()).willReturn(dummyTouristDtoList);

        mockMvc.perform(get(TouristController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.tourists", hasSize(3)))
                .andExpect(jsonPath("$.size", equalTo(3)))
                .andExpect(status().isOk());

        // touristService.getAll should be invoked.
        then(touristService).should().getAll();
    }

    @Test
    public void getTouristByIdSuccessTest() throws Exception {
        TouristDto dummyTouristDto = TouristDto.builder().id(1L).build();

        // touristService.getById should return a tourist dto.
        given(touristService.getById(anyLong())).willReturn(dummyTouristDto);

        mockMvc.perform(get(TouristController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(status().isOk());

        // touristService.getById should be invoked.
        then(touristService).should().getById(anyLong());
    }

    @Test
    public void createSuccessTest() throws Exception {
        CreateTouristCommand createTouristCommand = CreateTouristCommand.builder().build();

        mockMvc.perform(post(TouristController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(createTouristCommand)))
                .andExpect(status().isCreated());

        // touristService.create should be invoked.
        then(touristService).should().create(ArgumentMatchers.any(CreateTouristCommand.class));

    }

    @Test
    public void updateSuccessTest() throws Exception {
        UpdateTouristCommand updateTouristCommand = UpdateTouristCommand.builder().build();

        mockMvc.perform(put(TouristController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(updateTouristCommand)))
                .andExpect(status().isOk());

        // touristService.update should be invoked.
        then(touristService).should().update(ArgumentMatchers.any(UpdateTouristCommand.class), anyLong());



    }

    @Test
    public void deleteSuccessTest() throws Exception {

        mockMvc.perform(delete(TouristController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // touristService.delete should be invoked.
        then(touristService).should().delete(anyLong());

    }
}