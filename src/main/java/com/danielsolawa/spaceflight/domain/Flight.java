package com.danielsolawa.spaceflight.domain;


import com.danielsolawa.spaceflight.command.CreateFlightCommand;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime arrival;

    private LocalDateTime departure;

    private Integer numberOfSeats;

    @ManyToMany
    @JoinTable(name = "flight_tourist",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name= "tourist_id"))
    private List<Tourist> tourists = new ArrayList<>();

    private BigDecimal price;

    protected Flight() {
    }

    public static Flight createFlight(CreateFlightCommand command){

        if(command.getArrival() == null){
            throw new RuntimeException("The arrival must be set.");
        }

        if(command.getDeparture() == null){
            throw new RuntimeException("The departure must be set.");
        }

        if(command.getNumberOfSeats() == 0){
            throw new RuntimeException("The number of seats must be greater than 0.");
        }

        if(command.getPrice() == null){
            throw new RuntimeException("The price must cannot be empty.");
        }

        Flight flight = new Flight();
        flight.setArrival(command.getArrival());
        flight.setDeparture(command.getDeparture());
        flight.setNumberOfSeats(command.getNumberOfSeats());
        flight.setPrice(command.getPrice());
        flight.setTourists(new ArrayList<>());

        return flight;
    }



    public void addTourist(Tourist tourist){
        if(this.tourists.size() + 1 > this.numberOfSeats)
        {
            throw new RuntimeException("The number of seats has been exceeded.");
        }

        this.tourists.add(tourist);
    }

}
