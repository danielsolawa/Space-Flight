package com.danielsolawa.spaceflight.service;

import com.danielsolawa.spaceflight.domain.Tourist;
import com.danielsolawa.spaceflight.dto.TouristDto;
import com.danielsolawa.spaceflight.mapper.TouristMapper;
import com.danielsolawa.spaceflight.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TouristServiceImpl implements TouristService {

    private final TouristRepository touristRepository;

    private final TouristMapper touristMapper;

    public TouristServiceImpl(TouristRepository touristRepository, TouristMapper touristMapper) {
        this.touristRepository = touristRepository;
        this.touristMapper = touristMapper;
    }

    @Override
    public void create(TouristDto touristDto) {
        Tourist tourist = touristMapper.MapFromDto(touristDto);
        touristRepository.save(tourist);
    }

    @Override
    public TouristDto getById(Long id) {
        Tourist tourist = touristRepository.getOne(id);
        if(tourist == null){
            //todo create a custom exception for not found
            throw new RuntimeException("Tourist with the given id was not found.");
        }
        return touristMapper.MapToDto(tourist);
    }

    @Override
    public List<TouristDto> getAll() {
        return touristRepository.findAll()
                                .stream()
                                .map(touristMapper::MapToDto)
                                .collect(Collectors.toList());
    }

    @Override
    public void update(TouristDto touristDto, Long id) {
        Tourist touristForUpdate = prepareForUpdate(touristDto, id);

        touristRepository.save(touristForUpdate);
    }

    @Override
    public void delete(Long id) {
         touristRepository.deleteById(id);
    }

    private Tourist prepareForUpdate(TouristDto touristDto, Long id){
        Tourist tourist = touristMapper.MapFromDto(getById(id));

        if(touristDto.getFirstName() != null){
            tourist.setFirstName(touristDto.getFirstName());
        }

        if(touristDto.getLastName() != null){
            tourist.setLastName(touristDto.getLastName());
        }

        if(touristDto.getGender() != null){
            tourist.setGender(touristDto.getGender());
        }

        if(touristDto.getCountry() != null){
            tourist.setCountry(touristDto.getCountry());
        }

        if(touristDto.getNotes() != null){
            tourist.setNotes(touristDto.getNotes());
        }

        if(touristDto.getDateOfBirth() != null){
            tourist.setDateOfBirth(touristDto.getDateOfBirth());
        }

        return tourist;

    }
}
