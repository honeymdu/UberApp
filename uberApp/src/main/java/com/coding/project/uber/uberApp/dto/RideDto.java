package com.coding.project.uber.uberApp.dto;

import java.time.LocalDateTime;
import com.coding.project.uber.uberApp.enities.enums.PaymentMethod;
import com.coding.project.uber.uberApp.enities.enums.RideStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {

     private PointDto pickupLocation;

    private PointDto dropoffLocation;

    private LocalDateTime createdTime;

    private RiderDto rider;

    private DriverDto driver;

    private PaymentMethod paymentMethod;
    
    private RideStatus rideStatus;

    private Double fare;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private String otp;

}
