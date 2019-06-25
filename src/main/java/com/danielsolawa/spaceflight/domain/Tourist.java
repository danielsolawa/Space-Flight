package com.danielsolawa.spaceflight.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String country;

    private String notes;

    private LocalDate dateOfBirth;

    @ManyToMany
    @JoinTable(name = "tourist_flight",
            joinColumns = @JoinColumn(name = "tourist_id"),
            inverseJoinColumns = @JoinColumn(name= "flight_id"))
    private List<Flight> flights = new ArrayList<>();
}
