package com.coding.project.uber.uberApp.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.dto.DriverDto;
import com.coding.project.uber.uberApp.dto.RideDto;
import com.coding.project.uber.uberApp.dto.RideStartDto;
import com.coding.project.uber.uberApp.dto.RiderDto;
import com.coding.project.uber.uberApp.enities.Driver;
import com.coding.project.uber.uberApp.enities.Ride;
import com.coding.project.uber.uberApp.enities.RideRequest;
import com.coding.project.uber.uberApp.enities.enums.RideRequestStatus;
import com.coding.project.uber.uberApp.enities.enums.RideStatus;
import com.coding.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.coding.project.uber.uberApp.repositories.DriverRepository;
import com.coding.project.uber.uberApp.services.DriverService;
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
    public RideDto cancelRide(Long rideId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelRide'");
    }

    @Override
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
        if (rideStartDto.getOtp().equals(ride.getOtp())) {
            throw new RuntimeException("Otp is not correct");
        }
        ride.setStartedTime(LocalDateTime.now());
        Ride startedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);
        return modelMapper.map(startedRide, RideDto.class);
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

    @Override
    public Driver getCurrentDriver() {
        // Implement by Spring Security
        return driverRepository.findById(2L)
                .orElseThrow(() -> new ResourceNotFoundException("Driver Not Found with Id " + 2));
    }

}
