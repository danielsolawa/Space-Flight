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

    private Long id;
    private LocalDateTime arrival;
    private LocalDateTime departure;
    private Integer numberOfSeats;
    private List<Tourist> tourists = new ArrayList<>();
    private BigDecimal price;
}
