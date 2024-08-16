package com.coding.project.uber.uberApp.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.dto.RideRequestDto;
import com.coding.project.uber.uberApp.enities.Driver;
import com.coding.project.uber.uberApp.enities.Ride;
import com.coding.project.uber.uberApp.enities.enums.RideStatus;
import com.coding.project.uber.uberApp.services.RideService;

@Service
public class RideServiceImpl implements RideService {

    @Override
    public Ride getRideById(Long rideId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRideById'");
    }

    @Override
    public void matchWithDrivers(RideRequestDto requestDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matchWithDrivers'");
    }

    @Override
    public Ride creteNewRide(RideRequestDto requestDto, Driver Driver) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'creteNewRide'");
    }

    @Override
    public Ride updateRideStatus(Long rideId, RideStatus rideStatus) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRideStatus'");
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRidesOfRider'");
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRidesOfDriver'");
    }

}
