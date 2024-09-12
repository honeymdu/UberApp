package com.coding.project.uber.uberApp.dto;

import java.time.LocalDateTime;
import com.coding.project.uber.uberApp.enities.enums.TransactionMethod;
import com.coding.project.uber.uberApp.enities.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletTransactionDto {

   
    private Long id;

    private Double Amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private RideDto ridedDto;

    private String TransactionId;

    private WalletDto walletdDto;

    private LocalDateTime timeStamp;

}
