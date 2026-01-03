package com.mecklon.backend;


import com.mecklon.backend.model.Payment;
import com.mecklon.backend.model.type.PaymentStatus;
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
    private final SeatCleanUpService seatCleanUpService;
    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void cleanUpHolds() {
        System.out.println("cleaning up holds");
        int rows = seatRepository.cleanUpHolds();

        if (rows > 0) {
            System.out.println("seat holds released :" + rows);
        }
    }

    @Scheduled(fixedDelay = 60000)
    public void cleanUpAbortedPaymentProcesses() {
        List<Payment> payments = seatRepository.getAbortedPayments(LocalDateTime.now().minusMinutes(10));
        for (Payment payment : payments) {
            seatCleanUpService.resolveInconsistentSeatStates(payment);
        }
    }

    @Scheduled(fixedDelay = 180000)
    public void refundFailedBookings(){
        List<Payment> payments = paymentRepository.findByStatus(PaymentStatus.REFUND_PENDING);

        for(Payment payment : payments){

            try{
                if(paymentService.revertPayment(payment.getId())){
                    paymentRepository.setPaymentStatusTo(payment.getId(), PaymentStatus.REFUNDED);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }



}
