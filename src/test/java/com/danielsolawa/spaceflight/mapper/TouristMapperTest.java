package com.danielsolawa.spaceflight.mapper;

import com.danielsolawa.spaceflight.domain.Gender;
import com.danielsolawa.spaceflight.domain.Tourist;
import com.danielsolawa.spaceflight.dto.TouristDto;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class TouristMapperTest {

    private TouristMapper touristMapper;

    @Before
    public void setUp() throws Exception {
        touristMapper =  new TouristMapperImpl();
    }

    @Test
    public void mapToDto() {
        Tourist dummyTourist = new Tourist();
        dummyTourist.setId(1L);
        dummyTourist.setFirstName("Thomas");
        dummyTourist.setLastName("Smith");
        dummyTourist.setGender(Gender.Male);
        dummyTourist.setCountry("UK");
        dummyTourist.setNotes("Empty");
        dummyTourist.setDateOfBirth(LocalDate.of(1976, 10, 15));

        TouristDto dummyTouristDto = touristMapper.MapToDto(dummyTourist);

        assertThat(dummyTouristDto, notNullValue());
        assertThat(dummyTouristDto.getId(), equalTo(dummyTourist.getId()));
        assertThat(dummyTouristDto.getFirstName(), equalTo(dummyTourist.getFirstName()));
        assertThat(dummyTouristDto.getLastName(), equalTo(dummyTourist.getLastName()));

}

    @Test
    public void mapFromDto() {
        TouristDto dummyTouristDto = TouristDto.builder()
                                                .firstName("Thomas")
                                                .lastName("Smith")
                                                .gender(Gender.Male)
                                                .country("UK")
                                                .notes("Empty")
                                                .dateOfBirth(LocalDate.of(1976, 10, 15)).build();



        Tourist dummyTourist = touristMapper.MapFromDto(dummyTouristDto);

        assertThat(dummyTourist, notNullValue());
        assertThat(dummyTourist.getId(), equalTo(dummyTouristDto.getId()));
        assertThat(dummyTourist.getFirstName(), equalTo(dummyTouristDto.getFirstName()));
        assertThat(dummyTourist.getLastName(), equalTo(dummyTouristDto.getLastName()));

    }
}