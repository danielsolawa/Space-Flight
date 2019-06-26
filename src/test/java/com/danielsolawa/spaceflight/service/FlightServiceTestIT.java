package com.danielsolawa.spaceflight.service;

import com.danielsolawa.spaceflight.command.CreateFlightCommand;
import com.danielsolawa.spaceflight.domain.Flight;
import com.danielsolawa.spaceflight.repository.FlightRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightServiceTestIT {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void testFlight() {
        //Removes all flight objects (if any exists).
        removeAllFlights();

        //Creates a new flight object
        Flight flight = Flight.createFlight(CreateFlightCommand.builder()
                .arrival(LocalDateTime.now().plusDays(5).plusHours(6))
                .departure(LocalDateTime.now().plusDays(5).plusHours(2))
                .numberOfSeats(88)
                .price(new BigDecimal("1929.99")).build());

        flightRepository.save(flight);

        //Fetches all flights.
        List<Flight> flights = flightRepository.findAll();

        assertThat(flights.size(), equalTo(1));


        //The last object in the list should be the one that has been created above.
        Flight addedFlight = flights.get(flights.size() - 1);

        assertThat(addedFlight.getPrice(), equalTo(flight.getPrice()));
        assertThat(addedFlight.getNumberOfSeats(), equalTo(flight.getNumberOfSeats()));

        //Fetches the updated object by its id.
        Optional<Flight> optionalFlight = flightRepository.findById(addedFlight.getId());

        //Checks if it exists
        assertThat(optionalFlight.isPresent(), equalTo(true));

    }

    private void removeAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        for(Flight flight : flights){
            flightRepository.deleteById(flight.getId());
        }
    }
}
