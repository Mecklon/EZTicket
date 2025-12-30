package com.mecklon.backend.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    private String arrival;
    private String departure;
    private String from;
    private String to;
    private String airlineName;
    private int price;
    private Long id;
}
