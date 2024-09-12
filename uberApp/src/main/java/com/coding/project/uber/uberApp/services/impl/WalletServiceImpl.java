package com.coding.project.uber.uberApp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.dto.WalletDto;
import com.coding.project.uber.uberApp.enities.Ride;
import com.coding.project.uber.uberApp.enities.User;
import com.coding.project.uber.uberApp.enities.Wallet;
import com.coding.project.uber.uberApp.enities.WalletTransaction;
import com.coding.project.uber.uberApp.enities.enums.TransactionMethod;
import com.coding.project.uber.uberApp.enities.enums.TransactionType;
import com.coding.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.coding.project.uber.uberApp.repositories.WalletRepository;
import com.coding.project.uber.uberApp.services.WalletService;
import com.coding.project.uber.uberApp.services.WalletTransactionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;
    private final WalletTransactionService walletTransactionService;

    @Override
    public Wallet findWalletById(Long WalletId) {
        return walletRepository.findById(WalletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not Found with id " + WalletId));
    }

    @Override
    @Transactional
    public WalletDto addMoneyToWallet(User User, Double amount, String transactionId, Ride Ride,
            TransactionMethod transactionMethod) {
        Wallet wallet = findWalletByUser(User);
        wallet.setBalance(wallet.getBalance() + amount);

        WalletTransaction walletTransaction = WalletTransaction.builder().TransactionId(transactionId)
                .ride(Ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(transactionMethod)
                .Amount(amount)
                .build();

      //  wallet.getWalletTransaction().add(walletTransaction);
        walletTransactionService
        .CreateNewWalletTransaction(walletTransaction);

        return modelMapper.map(walletRepository.save(wallet), WalletDto.class);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User User, Double amount, String transactionId, Ride Ride,
            TransactionMethod transactionMethod) {
        Wallet wallet = findWalletByUser(User);
        wallet.setBalance(wallet.getBalance() - amount);
        WalletTransaction walletTransaction = WalletTransaction.builder().TransactionId(transactionId)
                .ride(Ride)
                .wallet(wallet)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .Amount(amount)
                .build();

      //  wallet.getWalletTransaction().add(walletTransaction);
        walletTransactionService
        .CreateNewWalletTransaction(walletTransaction);
        return walletRepository.save(wallet);

    }

    @Override
    public void withdrawAllMyMoneyFromWallet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withdrawAllMyMoneyFromWallet'");
    }

    @Override
    public WalletDto createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(0.0);
        return modelMapper.map(walletRepository.save(wallet), WalletDto.class);
    }

    @Override
    public Wallet findWalletByUser(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("wallet Not Found for UserId" + user.getId()));
    }

}
