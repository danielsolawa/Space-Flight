package com.danielsolawa.spaceflight.command;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFlightCommand {

    private LocalDateTime arrival;
    private LocalDateTime departure;
    private Integer numberOfSeats;
    private BigDecimal price;

}
