package com.mecklon.backend;


import com.mecklon.backend.model.Payment;
import com.mecklon.backend.model.type.PaymentStatus;
import com.mecklon.backend.model.type.SeatStatus;
import com.mecklon.backend.repo.PaymentRepository;
import com.mecklon.backend.repo.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatCleanUpService {
    private final SeatRepository seatRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public void resolveInconsistentSeatStates(Payment payment){
        int changed = paymentRepository.setPaymentStatusTo(payment.getId(), PaymentStatus.REFUND_PENDING);
        if(changed == 0) return;
        seatRepository.detachPayment(payment);
        seatRepository.setSeatStatusTo(payment.getId(), SeatStatus.ON_HOLD);
    }
}



