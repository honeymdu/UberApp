package com.coding.project.uber.uberApp.enities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.coding.project.uber.uberApp.enities.enums.TransactionMethod;
import com.coding.project.uber.uberApp.enities.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double Amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    @OneToOne(fetch = FetchType.LAZY)
    private Ride ride;

    private String TransactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet wallet;

    @CreationTimestamp
    private LocalDateTime timeStamp;
}
