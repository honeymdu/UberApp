package com.coding.project.uber.uberApp.services.impl;

import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.enities.WalletTransaction;
import com.coding.project.uber.uberApp.repositories.WalletTransactionRepository;
import com.coding.project.uber.uberApp.services.WalletTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;

  //  private final ModelMapper modelMapper;

    @Override
    public void CreateNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }

}
