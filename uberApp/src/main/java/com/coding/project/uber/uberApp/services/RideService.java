package com.coding.project.uber.uberApp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.coding.project.uber.uberApp.dto.RideRequestDto;
import com.coding.project.uber.uberApp.enities.Driver;
import com.coding.project.uber.uberApp.enities.Ride;
import com.coding.project.uber.uberApp.enities.enums.RideStatus;

public interface RideService {

    Ride getRideById(Long rideId);

    void matchWithDrivers(RideRequestDto requestDto);

    Ride creteNewRide(RideRequestDto requestDto ,Driver Driver);

    Ride updateRideStatus(Long rideId,RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Long riderId,PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);

}
