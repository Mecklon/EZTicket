package com.mecklon.backend.repo;


import com.mecklon.backend.DTO.SeatDto;
import com.mecklon.backend.model.Payment;
import com.mecklon.backend.model.Seat;
import com.mecklon.backend.model.User;
import com.mecklon.backend.model.key.SeatKey;
import com.mecklon.backend.model.type.PaymentStatus;
import com.mecklon.backend.model.type.SeatStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface SeatRepository extends JpaRepository<Seat, SeatKey> {


    @Query("""
            select new com.mecklon.backend.DTO.SeatDto(
                s.id.column,
                s.id.row,
                s.status
            ) from Seat s
            where s.id.flightId = :id
            """)
    List<SeatDto> getFlightSeats(Long id);




    @Modifying
    @Transactional
    @Query("""
            update Seat s
            set
            s.status = 'ON_HOLD',
            s.user = :user,
            s.onHoldTill = :onHoldTill
            where
            s.id.flightId = :flightId
            and
            s.id in :seatKeys
            and s.status = 'AVAILABLE'
            """)
    int attemptHoldSeats(
            @Param("user") User user,
            @Param("flightId") Long flightId,
            @Param("seatKeys") Set<SeatKey> seatKeys,
            @Param("onHoldTill") LocalDateTime onHoldTill
    );



    @Modifying
    @Transactional
    @Query("""
            update Seat s
            set s.status = 'AVAILABLE',
            s.user = null,
            s.onHoldTill = null
            where
            s.status = 'ON_HOLD'
            and
            s.onHoldTill < CURRENT_TIMESTAMP
            """)
    int cleanUpHolds();

    @Modifying
    @Transactional
    @Query("""
           update Seat s
           set s.status = 'PAYMENT_IN_PROCESS'
           where
           s.id.flightId = :id
           and
           s.id in :seats
           and
           s.status = 'ON_HOLD'
           and
           s.user = :user
           and
           s.onHoldTill > CURRENT_TIMESTAMP
           """)
    int setSeatsToUnderPaymentProcess(User user, Set<SeatKey> seats, Long flightId);


    @Modifying
    @Transactional
    @Query("""
            update Seat s
            set s.status = 'ON_HOLD'
            where s.id.flightId = :id
            and
            s.id in :seatKeys
            """)
    void revertSeatToOnHold(User user, Set<SeatKey> seatKeys, Long flightId);


    @Modifying
    @Transactional
    @Query("""
            update Seat s
            set s.status = 'BOOKED',
            s.onHoldTill = null
            where s.id.flightId = :flightId
            and
            s.id in :seatKeys
            """)
    void setSeatsToBooked( Set<SeatKey> seatKeys, Long flightId);


    @Modifying
    @Transactional
    @Query("""
           update Seat s
           set s.payment = :payment
           where
           s.id.flightId = :id
           and
           s.id in :seats
           """)
    int assignPaymentToSeat(User user, Set<SeatKey> seatKeys, Long flightId, Payment payment);


    @Modifying
    @Transactional
    @Query("""
            update Seat s
            set s.status = :seatStatus
            where
            s.payment.id = :paymentId
            """)
    void setSeatStatusTo(String paymentId, SeatStatus seatStatus);

    @Query("""
            select distinct s.payment
            from
            Seat s
            where
            s.payment.creationTimeStamp < :timeStamp
            and s.status = 'PENDING'
            """)
    List<Payment> getAbortedPayments(LocalDateTime timeStamp);


    @Query("""
            update Seat s
            set s.payment = null
            where s.payment = :payment
            """)
    void detachPayment(Payment payment);
}
