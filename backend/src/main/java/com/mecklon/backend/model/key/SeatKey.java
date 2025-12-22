package com.mecklon.backend.model.key;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class SeatKey implements Serializable {
    private Long flightId;

    @Column(name = "column_")
    private Character column;
    private int row;
}
