package com.danielsolawa.spaceflight.service;

import com.danielsolawa.spaceflight.domain.Flight;
import com.danielsolawa.spaceflight.dto.FlightDto;
import com.danielsolawa.spaceflight.mapper.FlightMapper;
import com.danielsolawa.spaceflight.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    private final FlightMapper flightMapper;

    public FlightServiceImpl(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }


    @Override
    public void create(FlightDto flightDto) {
        Flight flight = flightMapper.MapFromDto(flightDto);
        flightRepository.save(flight);
    }

    @Override
    public FlightDto getById(Long id){
        Flight flight = flightRepository.getOne(id);

        if(flight == null){
            //todo create a custom exception for not  found
            throw new RuntimeException("Flight with given id was not found.");
        }

        return flightMapper.MapToDto(flight);
    }

    @Override
    public List<FlightDto> getAll() {
        return flightRepository.findAll()
                                .stream()
                                .map(flightMapper::MapToDto)
                                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void update(FlightDto flightDto, Long id) {
        Flight flightForUpdate = prepareForUpdate(flightDto, id);

        flightRepository.save(flightForUpdate);
    }


    @Override
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }

    private Flight prepareForUpdate(FlightDto flightDto, Long id) {
        Flight flight = flightMapper.MapFromDto(getById(id));

        if(flightDto.getArrival() != null)
        {
            flight.setArrival(flightDto.getArrival());
        }

        if(flightDto.getDeparture() != null)
        {
            flight.setDeparture(flightDto.getDeparture());
        }

        if(flightDto.getNumberOfSeats() != 0)
        {
            flight.setNumberOfSeats(flightDto.getNumberOfSeats());
        }

        if(flightDto.getPrice() != null)
        {
            flight.setPrice(flightDto.getPrice());
        }


        return flight;
    }


}
