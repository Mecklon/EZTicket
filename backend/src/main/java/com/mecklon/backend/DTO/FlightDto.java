package com.mecklon.backend.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto implements Serializable {
    private String arrival;
    private String departure;
    private String from;
    private String to;
    private String airlineName;
    private int price;
    private Long id;
}
