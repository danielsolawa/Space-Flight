package com.danielsolawa.spaceflight.service;

import com.danielsolawa.spaceflight.bootstrap.SpaceFlightBootstrap;
import com.danielsolawa.spaceflight.domain.Flight;
import com.danielsolawa.spaceflight.repository.FlightRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightServiceIT {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void testFlight() {
        //Removes all flight objects (if any exists).
        removeAllFlights();

        //Creates a new flight object
        Flight flight = SpaceFlightBootstrap.getDummyFlight(12, new BigDecimal("11.87"));

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
