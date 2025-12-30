package com.mecklon.backend.service;


import com.mecklon.backend.DTO.*;
import com.mecklon.backend.FlightSpecifications;
import com.mecklon.backend.PaymentService;
import com.mecklon.backend.mapper.FlightMapper;
import com.mecklon.backend.model.Flight;
import com.mecklon.backend.model.Payment;
import com.mecklon.backend.model.Seat;
import com.mecklon.backend.model.User;
import com.mecklon.backend.model.key.SeatKey;
import com.mecklon.backend.model.type.PaymentStatus;
import com.mecklon.backend.repo.FlightRepository;
import com.mecklon.backend.repo.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final FlightMapper flightMapper;
    private final PaymentService paymentService;


    public Page<FlightDto> getFlights(GetFlightRequestDto request) {

        Specification<Flight> spec = FlightSpecifications.fromPlace(request.getFrom())
                .and(FlightSpecifications.toPlace(request.getTo()))
                .and(FlightSpecifications.departureOn(request.getDate()))
                .and(FlightSpecifications.airline(request.getAirlineName()));

        Pageable pageable = PageRequest.of(request.getPageNo(), 20);

        Page<Flight> flightPage = flightRepository.findAll(spec, pageable);
        return flightPage.map(flightMapper::toDto);
    }

    public FlightMapDto getFlightMap(Long id) {
        List<SeatDto> seats = seatRepository.getFlightSeats(id);
        return new FlightMapDto(seats);
    }


    @Transactional
    public void attemptHoldSeat(HoldSeatRequestListDto request, User user) {
        Set<SeatKey> seatKeys= request.getSeats().stream().map(seat -> new SeatKey(
                request.getFlightId(),
                seat.getColumn(),
                seat.getRow())
        ).collect(Collectors.toSet());

        if(seatRepository.attemptHoldSeats(user, request.getFlightId(), seatKeys, LocalDateTime.now().plusMinutes(5))!=request.getSeats().size()){
            throw new RuntimeException("could not hold all books");
        }
    }

    @Transactional
    public void setSetUnderProcessing(BookSeatRequestDto request, User user, Set<SeatKey> seatKeys){
        int count = seatRepository.setSeatsToUnderPaymentProcess(user, seatKeys, request.getFlightId());
        if(count != request.getSeats().size()){
            throw new RuntimeException("Bookiong time expired or invalid user");
        }
    }

    @Transactional
    public void assignPaymentToSeat(BookSeatRequestDto request, User user, Set<SeatKey> seatKeys, Payment payment){
        int count = seatRepository.assignPaymentToSeat(user, seatKeys, request.getFlightId(),payment);
        if(count != request.getSeats().size()){
            throw new RuntimeException("Could not assign payment details to seats");
        }
    }



    public void bookSeat(BookSeatRequestDto request, User user){
        Set<SeatKey> seatKeys = request.getSeats().stream().map(seat->
                new SeatKey(
                        request.getFlightId(),
                        seat.getColumn(),
                        seat.getRow())
        ).collect(Collectors.toSet());
        // if error here no money lost not database changes so fine
        setSetUnderProcessing(request,user, seatKeys);

        Seat seat = seatRepository.findById(
                new SeatKey(request.getFlightId(),
                        request.getSeats().get(1).getColumn(),
                        request.getSeats().get(1).getRow())
        ).orElseThrow(()->new RuntimeException("Database error"));


        Payment payment  = seat.getPayment();
        if(payment==null){
            String paymentId = UUID.randomUUID().toString();
            payment = new Payment(paymentId, PaymentStatus.PENDING, LocalDateTime.now());
            assignPaymentToSeat(request,user, seatKeys, payment);
        }

        // if error here periodic clean (cleanUpHolds()) up reverts it to on hold
        // if error occurs here no issue periodic query just reverts seat to on hold and keeps the payment id
        try{
            paymentService.handlePayment(request.getCardHolderName(), request.getCvv(), request.getAmount(), request.getCardNo());
            // if error here then handled gracefully
            // if execution stops abrubtly, no issue since payment id stored in db and payment service can use it as idempotency for payment status
        }catch (Exception err){
            seatRepository.revertSeatToOnHold(user, seatKeys, request.getFlightId());
            paymentService.revertPayment();
        }
        // over here i think i should have a some code that runs on boot that runs througt the payment id of seats that were left locked in payment underprocess by abnormal stoppage and checks their paymennt status and update seats status accordingly
        seatRepository.setSeatsToBooked(seatKeys, request.getFlightId());
    }



}
