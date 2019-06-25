package com.danielsolawa.spaceflight.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Tourist {

    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String country;
    private String notes;
    private LocalDate dateOfBirth;
    private List<Flight> flights = new ArrayList<>();;
}
