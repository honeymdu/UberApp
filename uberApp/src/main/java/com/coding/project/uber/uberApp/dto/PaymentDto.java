package com.coding.project.uber.uberApp.dto;

import java.time.LocalDateTime;

import com.coding.project.uber.uberApp.enities.Ride;
import com.coding.project.uber.uberApp.enities.enums.PaymentMethod;
import com.coding.project.uber.uberApp.enities.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private Long id;

    private PaymentMethod paymentMethod;

    private Ride ride;

    private Double amount;

    private PaymentStatus PaymentStatus;

    private LocalDateTime paymentTime;

}
