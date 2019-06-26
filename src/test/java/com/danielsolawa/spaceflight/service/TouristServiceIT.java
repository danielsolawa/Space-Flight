package com.danielsolawa.spaceflight.service;

import com.danielsolawa.spaceflight.bootstrap.SpaceFlightBootstrap;
import com.danielsolawa.spaceflight.domain.Flight;
import com.danielsolawa.spaceflight.domain.Gender;
import com.danielsolawa.spaceflight.domain.Tourist;
import com.danielsolawa.spaceflight.repository.TouristRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TouristServiceIT {

    @Autowired
    private TouristRepository touristRepository;

    @Test
    public void testTourist() {
        // Removes all tourists.
        removeAllTourists();

        // Creates a new tourist object.
        Tourist tourist =
                SpaceFlightBootstrap.getDummyTourist("Jane", "Sparrow",
                                                    Gender.Female, "Brazil", "None",
                                                LocalDate.of(1921, 3, 2));

        touristRepository.save(tourist);

        //Fetches all tourists.
        List<Tourist> tourists = touristRepository.findAll();

        assertThat(tourists.size(), equalTo(1));

        //The last object in the list should be the one that has been created above.
        Tourist addedTourist = tourists.get(tourists.size() - 1);

        assertThat(addedTourist.getFirstName(), equalTo(tourist.getFirstName()));
        assertThat(addedTourist.getCountry(), equalTo(tourist.getCountry()));

        //Fetches the updated object by its id.
        Optional<Tourist> optionalFlight = touristRepository.findById(addedTourist.getId());

        //Checks if it exists
        assertThat(optionalFlight.isPresent(), equalTo(true));


    }

    private void removeAllTourists() {
        List<Tourist> tourists = touristRepository.findAll();
        for(Tourist t : tourists){
            touristRepository.deleteById(t.getId());
        }
    }


}
