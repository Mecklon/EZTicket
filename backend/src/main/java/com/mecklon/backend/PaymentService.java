package com.mecklon.backend;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PaymentService {
    Random random = new Random();

    public void handlePayment(String cardHolderName, int cvv, int amount, Long cardNo, String paymentId) throws InterruptedException {
        if(random.nextInt(100)==50){
            throw new RuntimeException("Payment Error");
        }
        Thread.sleep(random.nextInt(5) * 10);
    }

    boolean revertPayment(String id) throws InterruptedException {
        if(random.nextInt(100)==50){
            throw new RuntimeException("Refund Error");
        }
        Thread.sleep(random.nextInt(5) * 10);
        return true;
    }
}
