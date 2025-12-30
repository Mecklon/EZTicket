package com.mecklon.backend.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSeatRequestDto {
    Long flightId;
    List<SeatDto> seats = new ArrayList<>();
    Long cardNo;
    int cvv;
    int amount;
    String cardHolderName;
}
