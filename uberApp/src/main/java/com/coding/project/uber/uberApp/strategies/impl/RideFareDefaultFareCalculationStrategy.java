package com.coding.project.uber.uberApp.strategies.impl;

import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.enities.RideRequest;
import com.coding.project.uber.uberApp.services.DistanceService;
import com.coding.project.uber.uberApp.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {

        double distance = distanceService.CalculateDistance(rideRequest.getPickupLocation(),
                rideRequest.getDropoffLocation());
        return distance* RIDE_FARE_MULTIPLYIER;
    }

}
