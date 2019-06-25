package com.danielsolawa.spaceflight.service;

import com.danielsolawa.spaceflight.domain.Gender;
import com.danielsolawa.spaceflight.domain.Tourist;
import com.danielsolawa.spaceflight.dto.TouristDto;
import com.danielsolawa.spaceflight.mapper.TouristMapper;
import com.danielsolawa.spaceflight.mapper.TouristMapperImpl;
import com.danielsolawa.spaceflight.repository.TouristRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


public class TouristServiceTest {

    @Mock
    private TouristRepository touristRepository;

    private TouristMapper touristMapper;

    private TouristService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        touristMapper = new TouristMapperImpl();
        service = new TouristServiceImpl(touristRepository, touristMapper);
    }

    @Test
    public void create() {
        TouristDto dummyTouristDto = getDummyTourist();

        service.create(dummyTouristDto);

        then(touristRepository).should().save(ArgumentMatchers.any(Tourist.class));
    }

    @Test
    public void getById() {
        Tourist dummyTourist = touristMapper.MapFromDto(getDummyTourist());

        given(touristRepository.getOne(anyLong())).willReturn(dummyTourist);

        TouristDto dummyTouristDto = service.getById(1L);

        assertThat(dummyTouristDto, notNullValue());
        assertThat(dummyTouristDto.getId(), equalTo(dummyTourist.getId()));
        assertThat(dummyTouristDto.getFirstName(), equalTo(dummyTourist.getFirstName()));
        assertThat(dummyTouristDto.getLastName(), equalTo(dummyTourist.getLastName()));

        then(touristRepository).should().getOne(anyLong());
    }

    @Test
    public void getAll() {
        List<Tourist> dummyTourists = getDummyList().stream()
                                                    .map(touristMapper::MapFromDto)
                                                    .collect(Collectors.toList());

        given(touristRepository.findAll()).willReturn(dummyTourists);

        List<TouristDto> resultTouristDtos = service.getAll();

        assertThat(resultTouristDtos, notNullValue());
        assertThat(resultTouristDtos.size(), equalTo(3));

        then(touristRepository).should().findAll();
    }

    @Test
    public void update() {
        TouristDto dummyTourist = getDummyTourist();

        given(touristRepository.getOne(anyLong())).willReturn(touristMapper.MapFromDto(dummyTourist));

        service.update(dummyTourist, 2L);

        then(touristRepository).should().getOne(anyLong());
        then(touristRepository).should().save(any(Tourist.class));
    }

    @Test
    public void delete() {

        service.delete(2L);

        then(touristRepository).should().deleteById(anyLong());
    }

    private static TouristDto getDummyTourist(){
        return TouristDto.builder()
                        .id(1L)
                        .firstName("Jane")
                        .lastName("Doe")
                        .gender(Gender.Female)
                        .country("France")
                        .notes("Empty")
                        .dateOfBirth(LocalDate.of(1980, 5, 22)).build();
    }

    private static List<TouristDto> getDummyList()
    {
        List<TouristDto> list =  new ArrayList<>();
        for(int i = 0; i < 3; i++)
        {
            list.add(getDummyTourist());
        }

        return list;
    }

}