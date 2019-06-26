package com.danielsolawa.spaceflight.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFlightCommand {

    private LocalDateTime arrival;
    private LocalDateTime departure;
    private Integer numberOfSeats;
    private BigDecimal price;
}
