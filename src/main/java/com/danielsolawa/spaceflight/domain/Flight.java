package com.danielsolawa.spaceflight.domain;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Flight {

    private long id;
    private LocalDateTime arrival;
    private LocalDateTime departure;
    private int numberOfSeats;
    private List<Tourist> tourists = new ArrayList<>();
    private BigDecimal price;


}
