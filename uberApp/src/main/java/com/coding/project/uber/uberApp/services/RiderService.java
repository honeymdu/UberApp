package com.coding.project.uber.uberApp.services;

import java.util.List;

import com.coding.project.uber.uberApp.dto.DriverDto;
import com.coding.project.uber.uberApp.dto.RideDto;
import com.coding.project.uber.uberApp.dto.RideRequestDto;
import com.coding.project.uber.uberApp.dto.RiderDto;
import com.coding.project.uber.uberApp.enities.Rider;
import com.coding.project.uber.uberApp.enities.User;

public interface RiderService {

    RideRequestDto createRideRequest(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getmyprofile();

    List<RideDto> getAllMyRides();

    Rider CreateNewRider(User user);

    Rider getCurrentRider();

   
}
