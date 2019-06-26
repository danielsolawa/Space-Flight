package com.danielsolawa.spaceflight.service;

import com.danielsolawa.spaceflight.command.CreateFlightCommand;
import com.danielsolawa.spaceflight.command.UpdateFlightCommand;
import com.danielsolawa.spaceflight.domain.Flight;
import com.danielsolawa.spaceflight.domain.Tourist;
import com.danielsolawa.spaceflight.dto.FlightDto;
import com.danielsolawa.spaceflight.mapper.FlightMapper;
import com.danielsolawa.spaceflight.repository.FlightRepository;
import com.danielsolawa.spaceflight.repository.TouristRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    private final TouristRepository touristRepository;

    private final FlightMapper flightMapper;

    public FlightServiceImpl(FlightRepository flightRepository, TouristRepository touristRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.touristRepository = touristRepository;
        this.flightMapper = flightMapper;
    }

    // Creates a new Flight object.
    @Override
    public void create(CreateFlightCommand command) {
        Flight flight = Flight.createFlight(command);

        log.info("A new flight has been added.");

        flightRepository.save(flight);
    }

    // Assigns given tourist to the flight.
    @Transactional
    @Override
    public void addTouristToList(Long touristId, Long flightId) {
        Tourist t = touristRepository.getOne(touristId);
        if(t == null){
            throw new RuntimeException("Tourist with the given doesn\'t exist.");
        }

        Flight f = flightMapper.MapFromDto(getById(flightId));
        f.addTourist(t);

        log.info("Given tourist added to the list.");

        flightRepository.save(f);
    }

    // Returns a Flight object if exists.
    @Override
    public FlightDto getById(Long id){
        Flight flight = flightRepository.getOne(id);

        if(flight == null){
            //todo create a custom exception for not  found
            throw new RuntimeException("Flight with given id was not found.");
        }

        log.info("Returning the flight.");

        return flightMapper.MapToDto(flight);
    }

    // Returns a list of all flight objects.
    @Override
    public List<FlightDto> getAll() {
        log.info("Returning the list of all flights.");

        return flightRepository.findAll()
                                .stream()
                                .map(flightMapper::MapToDto)
                                .collect(Collectors.toList());
    }

    // Updates object.
    @Transactional
    @Override
    public void update(UpdateFlightCommand command, Long id) {
        Flight flightForUpdate = prepareForUpdate(command, id);

        log.info("Updating the flight.");

        flightRepository.save(flightForUpdate);
    }


    // Deletes object.
    @Override
    public void delete(Long id) {

        log.info("Removing the flight with the given id.");

        flightRepository.deleteById(id);
    }


    private Flight prepareForUpdate(UpdateFlightCommand command, Long id) {
        Flight flight = flightMapper.MapFromDto(getById(id));

        if(command.getArrival() != null)
        {
            flight.setArrival(command.getArrival());
        }

        if(command.getDeparture() != null)
        {
            flight.setDeparture(command.getDeparture());
        }

        if(command.getNumberOfSeats() != 0)
        {
            flight.setNumberOfSeats(command.getNumberOfSeats());
        }

        if(command.getPrice() != null)
        {
            flight.setPrice(command.getPrice());
        }


        return flight;
    }



}
