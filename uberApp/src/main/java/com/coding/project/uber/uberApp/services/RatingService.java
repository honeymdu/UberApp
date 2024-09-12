package com.coding.project.uber.uberApp.services;

import com.coding.project.uber.uberApp.dto.DriverDto;
import com.coding.project.uber.uberApp.dto.RiderDto;
import com.coding.project.uber.uberApp.enities.Ride;

public interface RatingService {

    RiderDto rateRider(Ride ride, Integer Rating);

    DriverDto rateDriver(Ride ride, Integer Rating);

    void CreateNewRating(Ride ride);

}
