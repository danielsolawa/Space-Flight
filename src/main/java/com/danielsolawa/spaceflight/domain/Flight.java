package com.danielsolawa.spaceflight.domain;


import com.danielsolawa.spaceflight.command.CreateFlightCommand;
import com.danielsolawa.spaceflight.exception.AlreadyAddedException;
import com.danielsolawa.spaceflight.exception.LimitExceededException;
import com.danielsolawa.spaceflight.exception.NotFoundException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@AllArgsConstructor
@Table(name = "fli")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrival;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departure;
    private Integer numberOfSeats;

    @ManyToMany
    @JoinTable(name = "flight_tourist",
            joinColumns = @JoinColumn(name = "fli_id"),
            inverseJoinColumns = @JoinColumn(name= "tour_id"))
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
            throw new LimitExceededException("The number of seats has been exceeded.");
        }

        if(tourists.stream().anyMatch(t -> t.equals(tourist))){
            throw new AlreadyAddedException("Given tourist has been already added.");
        }

        tourist.addFlight(this);
        this.tourists.add(tourist);
    }

    public void removeTourist(Tourist tourist){
        if(this.tourists.size() > 0){
            Optional<Tourist> optionalTourist =
                    this.tourists.stream().filter(t -> t.getId().equals(tourist.getId())).findAny();
            if(!optionalTourist.isPresent()){
                throw new NotFoundException("Tourist was not found.");
            }
            this.tourists.remove(optionalTourist.get());
            tourist.removeFlight(this);

        }
    }

}
