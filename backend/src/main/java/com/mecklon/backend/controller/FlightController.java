package com.mecklon.backend.controller;


import com.mecklon.backend.DTO.*;
import com.mecklon.backend.model.User;
import com.mecklon.backend.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping("/getFlights")
    ResponseEntity<List<FlightDto>> getFlights(@RequestBody GetFlightRequestDto request){
        return ResponseEntity.status(HttpStatus.OK).body(flightService.getFlights(request));
    }

    @GetMapping("/getFlightSeats/{id}")
    ResponseEntity<FlightMapDto> getFlightSeats(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(flightService.getFlightMap(id));
    }


    @PostMapping("/holdSeat")
    ResponseEntity<Void> attemptHoldSeat(@RequestBody HoldSeatRequestListDto request, @AuthenticationPrincipal User user){
        flightService.attemptHoldSeat(request, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/bookSeat")
    ResponseEntity<Void> bookSeat(@RequestBody BookSeatRequestDto request, @AuthenticationPrincipal User user){
        flightService.bookSeat(request, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
