package com.mecklon.backend;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PaymentService {
    Random random = new Random();

    public void handlePayment(String cardHolderName, int cvv, int amount, Long cardNo) throws InterruptedException {
        Thread.sleep(random.nextInt(5) * 10);
    }

    public void revertPayment() {

    }

    public boolean verify(String id) {
        return true;
    }
}
