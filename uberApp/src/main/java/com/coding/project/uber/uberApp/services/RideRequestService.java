package com.coding.project.uber.uberApp.services;

import com.coding.project.uber.uberApp.enities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    void updateRideRequest(RideRequest rideRequest);

}
