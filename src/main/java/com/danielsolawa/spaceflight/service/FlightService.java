package com.danielsolawa.spaceflight.service;

import com.danielsolawa.spaceflight.command.CreateFlightCommand;
import com.danielsolawa.spaceflight.command.UpdateFlightCommand;
import com.danielsolawa.spaceflight.dto.FlightDto;

public interface FlightService extends BasicService<FlightDto> {

    void create(CreateFlightCommand command);
    void update(UpdateFlightCommand command, Long id);
    void addTouristToList(Long touristId, Long flightId);

}
