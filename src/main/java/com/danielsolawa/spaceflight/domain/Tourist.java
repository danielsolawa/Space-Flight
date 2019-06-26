package com.danielsolawa.spaceflight.domain;

import com.danielsolawa.spaceflight.command.CreateTouristCommand;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "tour")
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String country;

    private String notes;

    private LocalDate dateOfBirth;

    @ManyToMany(mappedBy = "tourists")
    @JsonIgnore
    private List<Flight> flights = new ArrayList<>();

    protected Tourist() {
    }

    public static Tourist createTourist(CreateTouristCommand command){
        if(command.getFirstName() == null){
            throw new RuntimeException("First name cannot be empty.");
        }

        if(command.getLastName() == null){
            throw new RuntimeException("Last name cannot be empty.");
        }

        if(command.getGender() == null){
            throw new RuntimeException("Gender cannot be empty.");
        }

        if(command.getCountry() == null){
            throw new RuntimeException("Country  cannot be empty.");
        }

        if(command.getNotes() == null){
            throw new RuntimeException("Notes cannot be empty.");
        }

        if(command.getDateOfBirth() == null){
            throw new RuntimeException("Date of birth cannot be empty.");
        }

        Tourist t = new Tourist();
        t.setFirstName(command.getFirstName());
        t.setLastName(command.getLastName());
        t.setGender(command.getGender());
        t.setCountry(command.getCountry());
        t.setDateOfBirth(command.getDateOfBirth());
        t.setNotes(command.getNotes());

        return t;
    }

    public void addFlight(Flight flight){
        if(this.flights != null){
            this.flights.add(flight);
        }

    }

    public void removeFlight(Flight flight){
        if(this.flights != null){
            this.flights.remove(flight);
        }
    }





}
