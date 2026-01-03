package com.mecklon.backend.service;


import com.mecklon.backend.DTO.*;
import com.mecklon.backend.FlightSpecifications;
import com.mecklon.backend.PaymentService;
import com.mecklon.backend.mapper.FlightMapper;
import com.mecklon.backend.model.*;
import com.mecklon.backend.model.key.SeatKey;
import com.mecklon.backend.model.type.IdempotencyStatus;
import com.mecklon.backend.model.type.PaymentStatus;
import com.mecklon.backend.repo.FlightRepository;
import com.mecklon.backend.repo.PaymentRepository;
import com.mecklon.backend.repo.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final FlightMapper flightMapper;
    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;
    private final IdempotencyService idempotencyService;


    @Cacheable(
            value = "flights",
            key = """
                        'from=' + #request.from +
                        '|to=' + #request.to +
                        '|date=' + #request.date +
                        '|airline=' + #request.airlineName +
                        '|page=' + #request.pageNo
                    """
    )

    public List<FlightDto> getFlights(GetFlightRequestDto request) {

        Specification<Flight> spec = FlightSpecifications.fromPlace(request.getFrom())
                .and(FlightSpecifications.toPlace(request.getTo()))
                .and(FlightSpecifications.departureOn(request.getDate()))
                .and(FlightSpecifications.airline(request.getAirlineName()));

        Pageable pageable = PageRequest.of(request.getPageNo(), 20);
        Page<Flight> flightPage =  flightRepository.findAll(spec, pageable);
        return flightPage.stream().map(flightMapper::toDto).toList();
    }


    @Cacheable(value = "flightMap", key = "#id")
    public FlightMapDto getFlightMap(Long id) {
        List<SeatDto> seats = seatRepository.getFlightSeats(id);
        return new FlightMapDto(seats);
    }


    @Transactional
    public void attemptHoldSeat(HoldSeatRequestListDto request, User user) {
        Set<SeatKey> seatKeys = request.getSeats().stream().map(seat -> new SeatKey(
                request.getFlightId(),
                seat.getColumn(),
                seat.getRow())
        ).collect(Collectors.toSet());

        int size = seatRepository.attemptHoldSeats(user, request.getFlightId(), seatKeys, LocalDateTime.now().plusMinutes(5));
        if (size != request.getSeats().size()) {
            throw new RuntimeException("could not hold all books");
        }
    }

    @Transactional
    public void setSeatUnderProcessingAndAssignPayment(BookSeatRequestDto request, User user, Set<SeatKey> seatKeys, Payment payment){
        int count = seatRepository.setSeatsToUnderPaymentProcess(user, seatKeys, request.getFlightId());
        if (count != request.getSeats().size()) {
            throw new RuntimeException("Bookiong time expired or invalid user");
        }
        count = seatRepository.assignPaymentToSeat(user, seatKeys, request.getFlightId(), payment);
        if (count != request.getSeats().size()) {
            throw new RuntimeException("Could not assign payment details to seats");
        }
    }

    @Transactional
    public void setPaymentSuccessful(Set<SeatKey> seatKeys, BookSeatRequestDto request, String id, String key){
        seatRepository.setSeatsToBooked(seatKeys, request.getFlightId());
        paymentRepository.setPaymentStatusTo(id, PaymentStatus.PAID);
        idempotencyService.complete(key, "complete");
    }

    @Transactional
    public void revertDueToPaymentFailure(User user, Set<SeatKey> seatKeys, BookSeatRequestDto request,IdempotencyKey idempotencyKey , Payment payment){
        seatRepository.revertSeatToOnHold(user, seatKeys, request.getFlightId());
        idempotencyService.fail(request.getIdempotencyKey());
        paymentRepository.setPaymentStatusTo(payment.getId(), PaymentStatus.FAILED);

    }


    // if there is any abnormal stoppage while a db is happening the dbms manages the consistency
    public void bookSeat(BookSeatRequestDto request, User user) {

        //if error here no loff
        IdempotencyKey idempotencyKey = idempotencyService.start(request.getIdempotencyKey());
        if(idempotencyKey.getStatus()== IdempotencyStatus.COMPLETE){
            return;
        }
        Set<SeatKey> seatKeys = request.getSeats().stream().map(seat ->
                new SeatKey(
                        request.getFlightId(),
                        seat.getColumn(),
                        seat.getRow())
        ).collect(Collectors.toSet());
        Payment payment = idempotencyKey.getPayment();

        setSeatUnderProcessingAndAssignPayment(request, user, seatKeys, payment);
        // if error here idempotency key and payment status are left in pending state which will be reverted to failed by the cleanup because payment never happened


        try {
            paymentService.handlePayment(request.getCardHolderName(), request.getCvv(), request.getAmount(), request.getCardNo(), payment.getId());
        } catch (Exception err) {
            // error happened do nothing just leave the seats under processing, payment and idempotency as pending so the clean up then correctly call the payment service to know payment actually happened or not
            return;
        }
        // if error happened here then seat payment and idempotency key will be left pending but payment has been done but the clean up again queries the payment service for the status of the payment allowing for payment to be set successful based on which seat and idempotency will be updated
        setPaymentSuccessful(seatKeys, request, payment.getId(), idempotencyKey.getKey());
    }


}
