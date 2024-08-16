package com.coding.project.uber.uberApp.strategies;
import com.coding.project.uber.uberApp.dto.RideRequestDto;

public interface RideFareCalculationStrategy {

    double calculateFare(RideRequestDto rideRequesttDto);

}
