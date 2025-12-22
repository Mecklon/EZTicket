package com.mecklon.backend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
