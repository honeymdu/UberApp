package com.coding.project.uber.uberApp.services.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.dto.RideRequestDto;
import com.coding.project.uber.uberApp.enities.Driver;
import com.coding.project.uber.uberApp.enities.Ride;
import com.coding.project.uber.uberApp.enities.RideRequest;
import com.coding.project.uber.uberApp.enities.enums.RideRequestStatus;
import com.coding.project.uber.uberApp.enities.enums.RideStatus;
import com.coding.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.coding.project.uber.uberApp.repositories.RideRepository;
import com.coding.project.uber.uberApp.services.RideRequestService;
import com.coding.project.uber.uberApp.services.RideService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final ModelMapper modelMapper;
    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;

    @Override
    public Ride creteNewRide(RideRequest rideRequest, Driver Driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        Ride ride = modelMapper.map(rideRequest, Ride.class);
        ride.setDriver(Driver);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setOtp(generateRandomOtp());
        ride.setId(null);
        ride.setStartedAt(LocalDateTime.now());
        rideRequestService.updateRideRequest(rideRequest);
        return rideRepository.save(ride);
    }

    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(() -> new ResourceNotFoundException("Ride is not found with id =" + rideId));
    }

    @Override
    public void matchWithDrivers(RideRequestDto requestDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matchWithDrivers'");
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

    private String generateRandomOtp() {
        Random random = new Random();
        int randomnumber = random.nextInt(10000);
        return String.format("%04d", randomnumber);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

}
