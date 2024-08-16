package com.coding.project.uber.uberApp.dto;

import org.locationtech.jts.geom.Point;

import com.coding.project.uber.uberApp.enities.Rider;
import com.coding.project.uber.uberApp.enities.enums.PaymentMethod;
import com.coding.project.uber.uberApp.enities.enums.RideRequestStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {

    private Long id;

    private Point pickupLocation;

    private Point dropoffLocation;

    private LocalDateTime requestedTime;

    private Rider rider;

    private PaymentMethod paymentMethod;

    private RideRequestStatus rideRequestStatus;

}
