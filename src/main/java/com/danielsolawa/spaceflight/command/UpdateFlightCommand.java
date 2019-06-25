package com.danielsolawa.spaceflight.command;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class UpdateFlightCommand {

    private LocalDateTime arrival;
    private LocalDateTime departure;
    private Integer numberOfSeats;
    private BigDecimal price;
}
