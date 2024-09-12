package com.coding.project.uber.uberApp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.coding.project.uber.uberApp.enities.Driver;
import com.coding.project.uber.uberApp.enities.Ride;
import com.coding.project.uber.uberApp.enities.RideRequest;
import com.coding.project.uber.uberApp.enities.Rider;
import com.coding.project.uber.uberApp.enities.enums.RideStatus;

public interface RideService {

    Ride getRideById(Long rideId);

    Ride creteNewRide(RideRequest rideRequest ,Driver Driver);

    Page<Ride> getAllRidesOfRider(Rider rider,PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);


}
