package com.danielsolawa.spaceflight.dto;

import com.danielsolawa.spaceflight.domain.Flight;
import com.danielsolawa.spaceflight.domain.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class TouristDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String country;
    private String notes;
    private LocalDate dateOfBirth;
    @JsonIgnore
    private List<Flight> flights;
}
