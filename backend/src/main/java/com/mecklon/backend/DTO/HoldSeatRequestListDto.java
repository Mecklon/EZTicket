package com.mecklon.backend.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoldSeatRequestListDto {
    private Long flightId;
    List<HoldSeatRequestDto> seats = new ArrayList<>();
}
