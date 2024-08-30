package com.coding.project.uber.uberApp.strategies;

import com.coding.project.uber.uberApp.enities.RideRequest;

public interface RideFareCalculationStrategy {

    public static final double RIDE_FARE_MULTIPLYIER = 10;

    double calculateFare(RideRequest rideRequest);

}
