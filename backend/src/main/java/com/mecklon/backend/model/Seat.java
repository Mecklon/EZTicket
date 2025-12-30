package com.mecklon.backend.model;

import com.mecklon.backend.model.key.SeatKey;
import com.mecklon.backend.model.type.SeatStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        indexes = {
                @Index(name = "index_state_onHoldTill", columnList = "status, on_hold_till")
        }

)
public class Seat {

    @EmbeddedId
    private SeatKey id;

    @MapsId("flightId")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "flight_id")
    private Flight flight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime onHoldTill;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
