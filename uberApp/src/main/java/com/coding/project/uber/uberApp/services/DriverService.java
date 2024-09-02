package com.coding.project.uber.uberApp.services;

import com.coding.project.uber.uberApp.dto.DriverDto;
import com.coding.project.uber.uberApp.dto.RideDto;
import com.coding.project.uber.uberApp.dto.RideStartDto;
import com.coding.project.uber.uberApp.dto.RiderDto;
import com.coding.project.uber.uberApp.enities.Driver;

import java.util.List;

import org.springframework.data.domain.PageRequest;

public interface DriverService {

    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId, RideStartDto rideStartDto);

    RideDto endRide(Long rideId);

    RiderDto raterider(Long rideId, Integer rating);

    DriverDto getmyprofile();

    List<RideDto> getAllMyRides(PageRequest pageRequest);

    Driver getCurrentDriver();

    Driver updateDriverAvailability(Driver driver, boolean available);
       

}
