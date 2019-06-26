package com.danielsolawa.spaceflight.controller;

import com.danielsolawa.spaceflight.command.AddTouristCommand;
import com.danielsolawa.spaceflight.command.CreateFlightCommand;
import com.danielsolawa.spaceflight.command.UpdateFlightCommand;
import com.danielsolawa.spaceflight.dto.FlightDto;
import com.danielsolawa.spaceflight.dto.FlightListDto;
import com.danielsolawa.spaceflight.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(FlightController.BASE_URL)
public class FlightController {

    public static final String BASE_URL = "/api/flight";

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public FlightListDto getAllFLights(){
        List<FlightDto> flights = flightService.getAll();

        return new FlightListDto(flights, flights.size());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDto getFlightById(@PathVariable Long id){
        return flightService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateFlightCommand command){
        flightService.create(command);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @RequestBody UpdateFlightCommand command){
        flightService.update(command, id);
    }

    @PutMapping("/{id}/add-tourist")
    @ResponseStatus(HttpStatus.OK)
    public void addTouristToList(@PathVariable Long id, @RequestBody AddTouristCommand command){
        flightService.addTouristToList(id, command.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        flightService.delete(id);
    }

}
