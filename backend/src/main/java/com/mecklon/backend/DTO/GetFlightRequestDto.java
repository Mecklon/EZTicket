package com.mecklon.backend.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GetFlightRequestDto {
    private String from;
    private String to;
    private String airlineName;
    private LocalDate date;
    private int pageNo;
}
