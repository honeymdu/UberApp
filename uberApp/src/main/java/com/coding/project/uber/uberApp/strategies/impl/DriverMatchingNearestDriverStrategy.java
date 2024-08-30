package com.coding.project.uber.uberApp.strategies.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.coding.project.uber.uberApp.enities.Driver;
import com.coding.project.uber.uberApp.enities.RideRequest;
import com.coding.project.uber.uberApp.repositories.DriverRepository;
import com.coding.project.uber.uberApp.strategies.DriverMatchingStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy{

    public final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
       
        driverRepository.findTenNearestDriver(rideRequest.getPickupLocation());

        return List.of();
    }
}
