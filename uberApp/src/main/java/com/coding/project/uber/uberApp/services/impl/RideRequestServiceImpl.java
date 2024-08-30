package com.coding.project.uber.uberApp.services.impl;

import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.enities.RideRequest;
import com.coding.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.coding.project.uber.uberApp.repositories.RideRequestRepository;
import com.coding.project.uber.uberApp.services.RideRequestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId).orElseThrow(
                () -> new ResourceNotFoundException("Ride Request Not Found with RideRequestId = " + rideRequestId));
    }

    @Override
    public void updateRideRequest(RideRequest rideRequest) {
        rideRequestRepository.findById(rideRequest.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Ride Request Not Found with Id = " + rideRequest.getId()));
        rideRequestRepository.save(rideRequest);
    }

}
