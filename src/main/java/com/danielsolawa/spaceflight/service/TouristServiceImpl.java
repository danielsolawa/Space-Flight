package com.danielsolawa.spaceflight.service;

import com.danielsolawa.spaceflight.command.CreateTouristCommand;
import com.danielsolawa.spaceflight.command.UpdateTouristCommand;
import com.danielsolawa.spaceflight.domain.Flight;
import com.danielsolawa.spaceflight.domain.Tourist;
import com.danielsolawa.spaceflight.dto.TouristDto;
import com.danielsolawa.spaceflight.exception.NotFoundException;
import com.danielsolawa.spaceflight.mapper.TouristMapper;
import com.danielsolawa.spaceflight.repository.TouristRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TouristServiceImpl implements TouristService {

    private final TouristRepository touristRepository;

    private final TouristMapper touristMapper;

    public TouristServiceImpl(TouristRepository touristRepository, TouristMapper touristMapper) {
        this.touristRepository = touristRepository;
        this.touristMapper = touristMapper;
    }

    //Creates a new tourist object.
    @Override
    public void create(CreateTouristCommand command) {
        Tourist tourist = Tourist.createTourist(command);

        touristRepository.save(tourist);

        log.info("A new tourist has been added");
    }

    //Returns a tourist object with the given id.
    @Override
    public TouristDto getById(Long id) {
        Optional<Tourist> tourist = touristRepository.findById(id);
        if(!tourist.isPresent()){
            throw new NotFoundException("Tourist with the given id was not found.");
        }
        log.info("Returning tourist...");
        return touristMapper.MapToDto(tourist.get());
    }

    //Returns all tourist objects.
    @Override
    public List<TouristDto> getAll() {
        log.info("Returning all tourists...");
        return touristRepository.findAll()
                                .stream()
                                .map(touristMapper::MapToDto)
                                .collect(Collectors.toList());
    }

    //Update a tourist object with the given id.
    @Override
    public void update(UpdateTouristCommand command, Long id) {
        Tourist touristForUpdate = prepareForUpdate(command, id);

        touristRepository.save(touristForUpdate);

        log.info("The tourist has been updated.");
    }

    //Deletes
    @Override
    public void delete(Long id) {
        Tourist t = touristMapper.MapFromDto(getById(id));
        List<Flight> flights = new ArrayList<>(t.getFlights());

        flights.forEach(f -> f.removeTourist(t));

        touristRepository.deleteById(id);

        log.info("The tourist has been deleted.");
    }

    private Tourist prepareForUpdate(UpdateTouristCommand command, Long id){
        Tourist tourist = touristMapper.MapFromDto(getById(id));

        if(command.getFirstName() != null){
            tourist.setFirstName(command.getFirstName());
        }

        if(command.getLastName() != null){
            tourist.setLastName(command.getLastName());
        }

        if(command.getGender() != null){
            tourist.setGender(command.getGender());
        }

        if(command.getCountry() != null){
            tourist.setCountry(command.getCountry());
        }

        if(command.getNotes() != null){
            tourist.setNotes(command.getNotes());
        }

        if(command.getDateOfBirth() != null){
            tourist.setDateOfBirth(command.getDateOfBirth());
        }

        return tourist;

    }
}
