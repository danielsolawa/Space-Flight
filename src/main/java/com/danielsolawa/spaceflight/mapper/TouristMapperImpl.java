package com.danielsolawa.spaceflight.mapper;

import com.danielsolawa.spaceflight.domain.Tourist;
import com.danielsolawa.spaceflight.dto.TouristDto;
import org.springframework.stereotype.Component;

@Component
public class TouristMapperImpl implements TouristMapper {

    @Override
    public TouristDto MapToDto(Tourist tourist) {

        return TouristDto.builder()
                .id(tourist.getId())
                .firstName(tourist.getFirstName())
                .lastName(tourist.getLastName())
                .gender(tourist.getGender())
                .country(tourist.getCountry())
                .notes(tourist.getNotes())
                .dateOfBirth(tourist.getDateOfBirth())
                .flights(tourist.getFlights()).build();

    }

    @Override
    public Tourist MapFromDto(TouristDto touristDto) {
        Tourist tourist = new Tourist();

        tourist.setId(touristDto.getId());
        tourist.setFirstName(touristDto.getFirstName());
        tourist.setLastName(touristDto.getLastName());
        tourist.setGender(touristDto.getGender());
        tourist.setCountry(touristDto.getCountry());
        tourist.setNotes(touristDto.getNotes());
        tourist.setDateOfBirth(touristDto.getDateOfBirth());

        return tourist;
    }
}
