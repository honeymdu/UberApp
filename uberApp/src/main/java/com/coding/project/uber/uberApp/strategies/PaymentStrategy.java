package com.coding.project.uber.uberApp.strategies;

import com.coding.project.uber.uberApp.enities.Payment;

public interface PaymentStrategy {

    Double PLATFORM_COMMISSION = 0.3;

    void ProcessPayment(Payment payment);

}
