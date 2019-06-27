package com.danielsolawa.spaceflight.command;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-M-d H:m")
    private LocalDateTime arrival;
    @JsonFormat(pattern = "yyyy-M-d H:m")
    private LocalDateTime departure;
    private Integer numberOfSeats;
    private BigDecimal price;
}
