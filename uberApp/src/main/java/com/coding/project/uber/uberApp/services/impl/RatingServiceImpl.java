package com.coding.project.uber.uberApp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.dto.DriverDto;
import com.coding.project.uber.uberApp.dto.RiderDto;
import com.coding.project.uber.uberApp.enities.Driver;
import com.coding.project.uber.uberApp.enities.Rating;
import com.coding.project.uber.uberApp.enities.Ride;
import com.coding.project.uber.uberApp.enities.Rider;
import com.coding.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.coding.project.uber.uberApp.exceptions.RuntimeConfilictException;
import com.coding.project.uber.uberApp.repositories.DriverRepository;
import com.coding.project.uber.uberApp.repositories.RatingRepository;
import com.coding.project.uber.uberApp.repositories.RiderRepository;
import com.coding.project.uber.uberApp.services.RatingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

        private final RatingRepository ratingRepository;
        private final DriverRepository driverRepository;
        private final RiderRepository riderRepository;
        private final ModelMapper modelMapper;

        @Override
        public RiderDto rateRider(Ride ride, Integer Rating) {

                Rating rating = ratingRepository.findByRide(ride).orElseThrow(
                                () -> new ResourceNotFoundException(
                                                "Rating not found for ride with Ride_id =" + ride.getId()));
                Rider rider = ride.getRider();
                if (rider.getRating() != null) {
                        throw new RuntimeConfilictException("Rider has already be rated. can not be rate again");
                }
                rating.setRiderRating(Rating);
                ratingRepository.save(rating);
                double newRating = ratingRepository.findByRider(rider)
                                .stream()
                                .mapToDouble(rating1 -> rating1.getRiderRating())
                                .average()
                                .orElse(0.0);
                rider.setRating(newRating);
                Rider savedRider = riderRepository.save(rider);
                return modelMapper.map(savedRider, RiderDto.class);

        }

        @Override
        public DriverDto rateDriver(Ride ride, Integer Rating) {
                Rating rating = ratingRepository.findByRide(ride).orElseThrow(
                                () -> new ResourceNotFoundException(
                                                "Rating not found for ride with Ride_id =" + ride.getId()));
                Driver driver = ride.getDriver();
                if (driver.getRating() != null) {
                        throw new RuntimeConfilictException("Driver has already be rated. can not be rate again");
                }
                rating.setDriverRating(Rating);
                ratingRepository.save(rating);
                double newRating = ratingRepository.findByDriver(driver)
                                .stream()
                                .mapToDouble(rating1 -> rating1.getDriverRating())
                                .average()
                                .orElse(0.0);
                driver.setRating(newRating);
                Driver savedDriver = driverRepository.save(driver);
                return modelMapper.map(savedDriver, DriverDto.class);

        }

        @Override
        public void CreateNewRating(Ride ride) {
                Rating rating = Rating.builder()
                                .driver(ride.getDriver())
                                .rider(ride.getRider())
                                .ride(ride)
                                .build();

                ratingRepository.save(rating);

        }

}
