package com.coding.project.uber.uberApp.services;

import com.coding.project.uber.uberApp.enities.Payment;
import com.coding.project.uber.uberApp.enities.Ride;
import com.coding.project.uber.uberApp.enities.enums.PaymentStatus;

public interface PaymentService {

    void ProcessPayment(Ride ride);

    Payment CreateNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);

}
