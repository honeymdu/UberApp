package com.coding.project.uber.uberApp.services.impl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.dto.DriverDto;
import com.coding.project.uber.uberApp.dto.RideDto;
import com.coding.project.uber.uberApp.dto.RideStartDto;
import com.coding.project.uber.uberApp.dto.RiderDto;
import com.coding.project.uber.uberApp.enities.Driver;
import com.coding.project.uber.uberApp.enities.Ride;
import com.coding.project.uber.uberApp.enities.RideRequest;
import com.coding.project.uber.uberApp.enities.User;
import com.coding.project.uber.uberApp.enities.enums.RideRequestStatus;
import com.coding.project.uber.uberApp.enities.enums.RideStatus;
import com.coding.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.coding.project.uber.uberApp.repositories.DriverRepository;
import com.coding.project.uber.uberApp.services.DriverService;
import com.coding.project.uber.uberApp.services.PaymentService;
import com.coding.project.uber.uberApp.services.RatingService;
import com.coding.project.uber.uberApp.services.RideRequestService;
import com.coding.project.uber.uberApp.services.RideService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;
    private final RatingService ratingService;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        if (!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
            throw new RuntimeException(
                    "RideRequest can not be accepted, Status is =" + rideRequest.getRideRequestStatus());
        }
        Driver driver = getCurrentDriver();
        if (!driver.getAvailable()) {
            throw new RuntimeException("Driver can not accept ride due to unavailablity");
        }
        driver.setAvailable(false);
        Driver SavedDriver = driverRepository.save(driver);
        Ride ride = rideService.creteNewRide(rideRequest, SavedDriver);
        return modelMapper.map(ride, RideDto.class);

    }

    @Override
    @Transactional
    public RideDto cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if (!driver.equals(ride.getDriver())) {
            throw new RuntimeException("Driver can not cancel ride as he not accept it earlier");
        }
        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride can not be canceled , Invalid Status " + ride.getRideStatus());
        }
        Ride canceledRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        updateDriverAvailability(driver, true);
        return modelMapper.map(canceledRide, RideDto.class);

    }

    @Override
    @Transactional
    public RideDto startRide(Long rideId, RideStartDto rideStartDto) {

        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if (!driver.equals(ride.getDriver())) {
            throw new RuntimeException("Driver can not start ride as he not accept it earlier");
        }
        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException(
                    "Ride Status not Confirmed Hence can not be started, Status =" + ride.getRideStatus());
        }
        if (!rideStartDto.getOtp().equals(ride.getOtp())) {
            throw new RuntimeException("Otp is not correct");
        }
        ride.setStartedTime(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);
        paymentService.CreateNewPayment(savedRide);
        ratingService.CreateNewRating(savedRide);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    @Transactional
    public RideDto endRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if (!driver.equals(ride.getDriver())) {
            throw new RuntimeException("Driver can not end ride as he not accept it earlier");
        }
        if (!ride.getRideStatus().equals(RideStatus.ONGOING)) {
            throw new RuntimeException(
                    "Ride can not be ended, Invalid Status =" + ride.getRideStatus());
        }
        ride.setEndedAt(LocalDateTime.now());
        Ride endedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
        updateDriverAvailability(driver, true);
        paymentService.ProcessPayment(endedRide);
        return modelMapper.map(endedRide, RideDto.class);
    }

    @Override
    public RiderDto raterider(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if (!driver.equals(ride.getDriver())) {
            throw new RuntimeException("Driver is not the owner of this ride hence can not be rate rider");
        }
        if (!ride.getRideStatus().equals(RideStatus.ENDED)) {
            throw new RuntimeException(
                    "Ride Status is not ended Hence can not be rate rider, Status =" + ride.getRideStatus());
        }

        return ratingService.rateRider(ride, rating);

    }

    @Override
    public DriverDto getmyprofile() {
        Driver driver = getCurrentDriver();
        return modelMapper.map(driver, DriverDto.class);
    }

    @Override
    public Driver getCurrentDriver() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return driverRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Driver Not Found assosiated with user with userId =" + user.getId()));
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean available) {
        driver.setAvailable(available);
        return driverRepository.save(driver);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Driver driver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(driver, pageRequest).map(
                ride -> modelMapper.map(ride, RideDto.class));
    }

    @Override
    public Driver CreateNewDriver(Driver driver) {
        return driverRepository.save(driver);
    }

}
