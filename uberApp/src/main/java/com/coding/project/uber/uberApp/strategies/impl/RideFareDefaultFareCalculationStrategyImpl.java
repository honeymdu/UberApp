package com.coding.project.uber.uberApp.strategies.impl;

import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.dto.RideRequestDto;
import com.coding.project.uber.uberApp.strategies.RideFareCalculationStrategy;

@Service
public class RideFareDefaultFareCalculationStrategyImpl implements RideFareCalculationStrategy{

    @Override
    public double calculateFare(RideRequestDto rideRequesttDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateFare'");
    }

}