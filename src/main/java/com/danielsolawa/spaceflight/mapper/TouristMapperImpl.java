package com.danielsolawa.spaceflight.mapper;

import com.danielsolawa.spaceflight.domain.Tourist;
import com.danielsolawa.spaceflight.dto.TouristDto;
import org.springframework.stereotype.Component;

@Component
public class TouristMapperImpl implements TouristMapper {

    @Override
    public TouristDto MapToDto(Tourist tourist) {
        return null;
    }

    @Override
    public Tourist MapFromDto(TouristDto touristDto) {
        return null;
    }
}
