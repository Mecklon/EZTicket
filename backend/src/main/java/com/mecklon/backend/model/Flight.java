package com.mecklon.backend.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        indexes = {
                @Index(
                        name = "idx_flight_route_date_price",
                        columnList = "from_, to_, departure"
                ),
                @Index(
                        name = "idx_flight_airline",
                        columnList = "airlineName"
                )
        }
)

public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "from_")
    private String from;

    @Column(nullable = false, name = "to_")
    private String to;

    @Column(nullable = false)
    private LocalDateTime departure;

    @Column(nullable = false)
    private LocalDateTime arrival;

    @Column(nullable = false)
    private String airlineName;

    @Column(nullable = false)
    private int price;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Seat> seats = new ArrayList<>();

}
