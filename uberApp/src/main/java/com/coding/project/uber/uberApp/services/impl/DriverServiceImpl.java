package com.coding.project.uber.uberApp.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.dto.DriverDto;
import com.coding.project.uber.uberApp.dto.RideDto;
import com.coding.project.uber.uberApp.dto.RiderDto;
import com.coding.project.uber.uberApp.services.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

    @Override
    public RideDto acceptRide(Long rideId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'acceptRide'");
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelRide'");
    }

    @Override
    public RideDto startRide(Long rideId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startRide'");
    }

    @Override
    public RideDto endRide(Long rideId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endRide'");
    }

    @Override
    public RiderDto raterider(Long rideId, Integer rating) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'raterider'");
    }

    @Override
    public DriverDto getmyprofile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getmyprofile'");
    }

    @Override
    public List<RideDto> getAllMyRides() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllMyRides'");
    }

}
