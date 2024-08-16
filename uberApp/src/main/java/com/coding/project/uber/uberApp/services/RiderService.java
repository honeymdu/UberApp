package com.coding.project.uber.uberApp.services;

import java.util.List;

import com.coding.project.uber.uberApp.dto.DriverDto;
import com.coding.project.uber.uberApp.dto.RideDto;
import com.coding.project.uber.uberApp.dto.RideRequestDto;
import com.coding.project.uber.uberApp.dto.RiderDto;

public interface RiderService {

    RideRequestDto createRideRequest(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getmyprofile();

    List<RideDto> getAllMyRides();

}
