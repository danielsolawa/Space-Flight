package com.danielsolawa.spaceflight.service;

import com.danielsolawa.spaceflight.command.CreateTouristCommand;
import com.danielsolawa.spaceflight.command.UpdateTouristCommand;
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
import java.util.Optional;
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
    public void createSuccessTest() {
        TouristDto dummyTouristDto = getDummyTouristDto();


        service.create(CreateTouristCommand.builder()
                                            .firstName(dummyTouristDto.getFirstName())
                                            .gender(dummyTouristDto.getGender())
                                            .country(dummyTouristDto.getCountry())
                                            .notes(dummyTouristDto.getNotes())
                                            .lastName(dummyTouristDto.getLastName())
                                            .dateOfBirth(dummyTouristDto.getDateOfBirth())
                                            .build());

        // touristRepository.save method should be invoked.
        then(touristRepository).should().save(ArgumentMatchers.any(Tourist.class));
    }

    @Test
    public void getByIdSuccessTest() {
        Tourist dummyTourist = touristMapper.MapFromDto(getDummyTouristDto());
        Optional<Tourist> optionalTourist = Optional.of(dummyTourist);

        // touristRepository.findById method should return a given object.
        given(touristRepository.findById(anyLong())).willReturn(optionalTourist);

        TouristDto dummyTouristDto = service.getById(1L);

        assertThat(dummyTouristDto, notNullValue());
        assertThat(dummyTouristDto.getId(), equalTo(dummyTourist.getId()));
        assertThat(dummyTouristDto.getFirstName(), equalTo(dummyTourist.getFirstName()));
        assertThat(dummyTouristDto.getLastName(), equalTo(dummyTourist.getLastName()));

        // touristRepository.findById method should be invoked.
        then(touristRepository).should().findById(anyLong());
    }



    @Test
    public void getAllSuccessTest() {
        List<Tourist> dummyTourists = getDummyTouristDtoList().stream()
                                                    .map(touristMapper::MapFromDto)
                                                    .collect(Collectors.toList());

        // touristRepository.findAll method should return a given object.
        given(touristRepository.findAll()).willReturn(dummyTourists);

        List<TouristDto> resultTouristDtos = service.getAll();

        assertThat(resultTouristDtos, notNullValue());
        assertThat(resultTouristDtos.size(), equalTo(3));

        // touristRepository.findAll method should be invoked.
        then(touristRepository).should().findAll();
    }

    @Test
    public void updateSuccessTest() {
        TouristDto dummyTourist = getDummyTouristDto();
        Optional<Tourist> optionalTourist = Optional.of(touristMapper.MapFromDto(dummyTourist));

        // touristRepository.findById method should return a given object.
        given(touristRepository.findById(anyLong())).willReturn(optionalTourist);

        service.update(UpdateTouristCommand.builder().build(), 2L);

        // touristRepository.findById method should be invoked.
        then(touristRepository).should().findById(anyLong());
        // touristRepository.save method should be invoked.
        then(touristRepository).should().save(any(Tourist.class));
    }

    @Test
    public void deleteSuccessTest() {

        service.delete(2L);

        // touristRepository.deleteById method should be invoked.
        then(touristRepository).should().deleteById(anyLong());
    }

    public static Tourist getDummyTourist(){
        return new TouristMapperImpl().MapFromDto(getDummyTouristDto());
    }

    private static TouristDto getDummyTouristDto(){
        return TouristDto.builder()
                        .id(1L)
                        .firstName("Jane")
                        .lastName("Doe")
                        .gender(Gender.Female)
                        .country("France")
                        .notes("Empty")
                        .dateOfBirth(LocalDate.of(1980, 5, 22)).build();
    }

    private static List<TouristDto> getDummyTouristDtoList()
    {
        List<TouristDto> list =  new ArrayList<>();
        for(int i = 0; i < 3; i++)
        {
            list.add(getDummyTouristDto());
        }

        return list;
    }

}