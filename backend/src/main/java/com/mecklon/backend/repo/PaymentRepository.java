package com.mecklon.backend.repo;

import com.mecklon.backend.model.Payment;
import com.mecklon.backend.model.type.PaymentStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {


    @Modifying
    @Transactional
    @Query("""
            update Payment p
            set
            p.status = :paymentStatus
            where
            p.id = :paymentId
            """)
    int setPaymentStatusTo(String paymentId, PaymentStatus paymentStatus);

    List<Payment> findByStatus(PaymentStatus paymentStatus);
}
