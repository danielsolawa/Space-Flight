package com.danielsolawa.spaceflight.service;

import com.danielsolawa.spaceflight.command.CreateTouristCommand;
import com.danielsolawa.spaceflight.command.UpdateTouristCommand;
import com.danielsolawa.spaceflight.dto.TouristDto;

public interface TouristService extends BasicService<TouristDto> {

    void create(CreateTouristCommand command);
    void update(UpdateTouristCommand command, Long id);
}
