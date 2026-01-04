package com.mecklon.backend.DTO;


import com.mecklon.backend.model.type.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDto implements Serializable {
    private Character column;
    private int row;
    private SeatStatus status;
}
