package com.mecklon.backend.model;

import com.mecklon.backend.model.key.SeatKey;
import com.mecklon.backend.model.type.SeatStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @EmbeddedId
    private SeatKey id;

    @MapsId("flightId")
    @ManyToOne(optional = false, fetch=FetchType.LAZY)
    @JoinColumn(nullable = false, name="flight_id")
    private Flight flight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
