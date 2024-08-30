package com.coding.project.uber.uberApp.strategies.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.enities.Driver;
import com.coding.project.uber.uberApp.enities.RideRequest;
import com.coding.project.uber.uberApp.strategies.DriverMatchingStrategy;
import com.coding.project.uber.uberApp.repositories.DriverRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverMathcingHighestRatingStrategy implements DriverMatchingStrategy {

    private final DriverRepository DriverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return DriverRepository.findTenNearByTopRatedDriver(rideRequest.getPickupLocation());
    }
}
