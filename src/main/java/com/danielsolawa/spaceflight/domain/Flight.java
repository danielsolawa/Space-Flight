package com.danielsolawa.spaceflight.domain;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime arrival;

    private LocalDateTime departure;

    private int numberOfSeats;

    @ManyToMany(mappedBy = "flights")
    private List<Tourist> tourists = new ArrayList<>();

    private BigDecimal price;


}
