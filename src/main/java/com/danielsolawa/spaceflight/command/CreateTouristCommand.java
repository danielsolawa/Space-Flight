package com.danielsolawa.spaceflight.command;

import com.danielsolawa.spaceflight.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTouristCommand {

    private String firstName;
    private String lastName;
    private Gender gender;
    private String country;
    private String notes;
    private LocalDate dateOfBirth;
}
