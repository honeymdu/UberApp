package com.coding.project.uber.uberApp.strategies.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.dto.RideRequestDto;
import com.coding.project.uber.uberApp.enities.Driver;
import com.coding.project.uber.uberApp.strategies.DriverMatchingStrategy;

@Service
public class DriverMathcingHighestRatingStrategy implements DriverMatchingStrategy {

    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findMatchingDriver'");
    }

}
