package com.danielsolawa.spaceflight.service;

import com.danielsolawa.spaceflight.command.CreateTouristCommand;
import com.danielsolawa.spaceflight.command.UpdateTouristCommand;
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
    public void create(CreateTouristCommand command) {
        Tourist tourist = Tourist.createTourist(command);
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
    public void update(UpdateTouristCommand command, Long id) {
        Tourist touristForUpdate = prepareForUpdate(command, id);

        touristRepository.save(touristForUpdate);
    }

    @Override
    public void delete(Long id) {
         touristRepository.deleteById(id);
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
