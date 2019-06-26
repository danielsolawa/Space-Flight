package com.danielsolawa.spaceflight.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFlightCommand {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrival;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departure;
    private Integer numberOfSeats;
    private BigDecimal price;

}
