package com.danielsolawa.spaceflight.dto;

import com.danielsolawa.spaceflight.domain.Tourist;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class FlightDto {

    private long id;
    private LocalDateTime arrival;
    private LocalDateTime departure;
    private int numberOfSeats;
    private List<Tourist> tourists = new ArrayList<>();
    private BigDecimal price;
}
