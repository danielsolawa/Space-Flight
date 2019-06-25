package com.danielsolawa.spaceflight.controller;

import com.danielsolawa.spaceflight.dto.TouristDto;
import com.danielsolawa.spaceflight.dto.TouristListDto;
import com.danielsolawa.spaceflight.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(TouristController.BASE_URL)
public class TouristController {

    public static final String BASE_URL = "/api/tourist";

    private final TouristService touristService;


    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TouristListDto getAllTourists(){
        List<TouristDto> list = touristService.getAll();

        return new TouristListDto(list, list.size());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TouristDto getTouristById(@PathVariable Long id){
        return touristService.getById(id);
    }
}
