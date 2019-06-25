package com.danielsolawa.spaceflight.service;

import com.danielsolawa.spaceflight.dto.FlightDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Override
    public void create(FlightDto flightDto) {

    }

    @Override
    public FlightDto getById(Long id) {
        return null;
    }

    @Override
    public List<FlightDto> getAll() {
        return null;
    }

    @Override
    public void update(FlightDto flightDto, Long id) {

    }

    @Override
    public void delete(Long id) {

    }
}
