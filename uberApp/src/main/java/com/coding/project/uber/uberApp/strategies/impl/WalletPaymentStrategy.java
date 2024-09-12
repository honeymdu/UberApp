package com.coding.project.uber.uberApp.strategies.impl;

import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.enities.Driver;
import com.coding.project.uber.uberApp.enities.Payment;
import com.coding.project.uber.uberApp.enities.Rider;
import com.coding.project.uber.uberApp.enities.enums.PaymentStatus;
import com.coding.project.uber.uberApp.enities.enums.TransactionMethod;
import com.coding.project.uber.uberApp.repositories.PaymentRepository;
import com.coding.project.uber.uberApp.services.WalletService;
import com.coding.project.uber.uberApp.strategies.PaymentStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void ProcessPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();
        // Wallet driverwallet = walletService.findWalletById(driver.getId());
        double platform_commission = payment.getAmount() * PLATFORM_COMMISSION;
        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(), null, payment.getRide(),
                TransactionMethod.RIDE);

        double drivercut = payment.getAmount() - platform_commission;

        walletService.addMoneyToWallet(driver.getUser(), drivercut, null,
                payment.getRide(),
                TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);

    }

}
