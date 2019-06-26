package com.danielsolawa.spaceflight.dto;

import com.danielsolawa.spaceflight.domain.Tourist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    private Long id;
    private LocalDateTime arrival;
    private LocalDateTime departure;
    private Integer numberOfSeats;
    private List<Tourist> tourists;
    private BigDecimal price;
}
