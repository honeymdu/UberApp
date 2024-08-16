package com.coding.project.uber.uberApp.strategies;

import java.util.List;

import com.coding.project.uber.uberApp.dto.RideRequestDto;
import com.coding.project.uber.uberApp.enities.Driver;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDriver(RideRequestDto rideRequestDto);

}
