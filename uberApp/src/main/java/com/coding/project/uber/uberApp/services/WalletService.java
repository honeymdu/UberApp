package com.coding.project.uber.uberApp.services;

import com.coding.project.uber.uberApp.dto.WalletDto;
import com.coding.project.uber.uberApp.enities.Ride;
import com.coding.project.uber.uberApp.enities.User;
import com.coding.project.uber.uberApp.enities.Wallet;
import com.coding.project.uber.uberApp.enities.enums.TransactionMethod;

public interface WalletService {

    WalletDto addMoneyToWallet(User user, Double amount, String transactionId, Ride Ride,
            TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride Ride,
            TransactionMethod transactionMethod);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long WalletId);

    WalletDto createNewWallet(User user);

    Wallet findWalletByUser(User user);

}
