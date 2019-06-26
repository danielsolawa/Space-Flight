package com.danielsolawa.spaceflight.bootstrap;

import com.danielsolawa.spaceflight.command.CreateFlightCommand;
import com.danielsolawa.spaceflight.command.CreateTouristCommand;
import com.danielsolawa.spaceflight.domain.Flight;
import com.danielsolawa.spaceflight.domain.Gender;
import com.danielsolawa.spaceflight.domain.Tourist;
import com.danielsolawa.spaceflight.repository.FlightRepository;
import com.danielsolawa.spaceflight.repository.TouristRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Profile("dev")
@Slf4j
public class SpaceFlightBootstrap implements CommandLineRunner {

    private final TouristRepository touristRepository;

    private final FlightRepository flightRepository;

    public SpaceFlightBootstrap(TouristRepository touristRepository, FlightRepository flightRepository) {
        this.touristRepository = touristRepository;
        this.flightRepository = flightRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        loadTourists();
        loadFlights();
    }

    private void loadFlights() {
        Flight f1 = getDummyFlight(5);
        flightRepository.save(f1);




        log.info("A new flight has been added.");
    }

    private void loadTourists() {
        touristRepository.save(getDummyTourist("John", "Black",
                                                Gender.Male, "USA",
                                                "Empty", LocalDate.now()));

        log.info("A new tourist has been added.");
    }

    private static Flight getDummyFlight(int seats)
    {
        return Flight.createFlight(CreateFlightCommand.builder()
                                                    .arrival(LocalDateTime.now().plusDays(5).plusHours(6))
                                                    .departure(LocalDateTime.now().plusDays(5).plusHours(2))
                                                    .numberOfSeats(seats)
                                                    .price(new BigDecimal("199.99")).build());
    }

    private static Tourist getDummyTourist(String firstName, String lastName,
                                           Gender gender, String country,
                                           String notes, LocalDate dateOfBirth){

        return Tourist.createTourist(CreateTouristCommand.builder()
                                                        .firstName(firstName)
                                                        .lastName(lastName)
                                                        .gender(gender)
                                                        .country(country)
                                                        .notes(notes)
                                                        .dateOfBirth(dateOfBirth).build());
    }
}
