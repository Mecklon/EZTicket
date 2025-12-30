package com.mecklon.backend;


import com.mecklon.backend.model.Payment;
import com.mecklon.backend.model.Seat;
import com.mecklon.backend.model.type.PaymentStatus;
import com.mecklon.backend.model.type.SeatStatus;
import com.mecklon.backend.repo.PaymentRepository;
import com.mecklon.backend.repo.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatCleanUpJob {

    private final SeatRepository seatRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentService paymentService;

    @Scheduled(fixedDelay = 60000)
    @Transactional
    void cleanUpHolds(){
        System.out.println("cleaning up holds");
        int rows = seatRepository.cleanUpHolds();

        if(rows>0){
            System.out.println("seat holds released :" + rows);
        }
    }

    @Transactional
    void cleanUpAbortedPaymentProcess(Payment payment){
        int changed = 0;
        if(paymentService.verify(payment.getId())){
            changed = paymentRepository.setPaymentStatusTo(payment.getId(), PaymentStatus.PAID);
            if(changed==0) return;
            seatRepository.setSeatStatusTo(payment.getId(), SeatStatus.BOOKED);
        }else{
            changed = paymentRepository.setPaymentStatusTo(payment.getId(), PaymentStatus.FAILED);
            if(changed==0) return;
            seatRepository.setSeatStatusTo(payment.getId(), SeatStatus.ON_HOLD);
        }
    }

    @Scheduled(fixedDelay = 60000)
    void cleanUpAbortedPaymentProcesses(){
        List<Payment> payments = paymentRepository.findAllByStatusAndCreationTimeStampBefore(PaymentStatus.PENDING, LocalDateTime.now().minusMinutes(2));
        for(Payment payment : payments){
            cleanUpAbortedPaymentProcess(payment);
        }
    }

}
