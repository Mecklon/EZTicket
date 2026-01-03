package com.mecklon.backend.service;

import com.mecklon.backend.model.IdempotencyKey;
import com.mecklon.backend.model.Payment;
import com.mecklon.backend.model.type.IdempotencyStatus;
import com.mecklon.backend.model.type.PaymentStatus;
import com.mecklon.backend.repo.IdempotencyRepository;
import com.mecklon.backend.repo.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IdempotencyService {

    private final IdempotencyRepository repository;

    @Transactional
    public IdempotencyKey start(String key) {

        int inserted = repository.tryInsert(key);

        if (inserted == 1) {
            String paymentId = UUID.randomUUID().toString();
            Payment payment = new Payment(paymentId, PaymentStatus.PENDING, LocalDateTime.now());
            repository.attachPayment(payment, key);
            return repository.find(key).orElseThrow();
        }

        IdempotencyKey existing = repository.find(key).orElseThrow();

        if (existing.getStatus() == IdempotencyStatus.COMPLETE) {
            return existing;
        }

        if (existing.getStatus() == IdempotencyStatus.PROCESSING) {
            throw new IllegalStateException("Request already in progress");
        }

        throw new IllegalStateException("Retry allowed");
    }

    @Transactional
    public void complete(String key, String response) {
        repository.find(key).ifPresent(i -> {
            i.setStatus(IdempotencyStatus.COMPLETE);
            i.setResponse(response);
        });
    }

    @Transactional
    public void fail(String key) {
        repository.find(key).ifPresent(i -> i.setStatus(IdempotencyStatus.FAILED));
    }
}
