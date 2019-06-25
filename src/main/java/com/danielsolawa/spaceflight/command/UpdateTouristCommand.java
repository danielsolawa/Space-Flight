package com.danielsolawa.spaceflight.command;

import com.danielsolawa.spaceflight.domain.Gender;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UpdateTouristCommand{

    private String firstName;
    private String lastName;
    private Gender gender;
    private String country;
    private String notes;
    private LocalDate dateOfBirth;
}
