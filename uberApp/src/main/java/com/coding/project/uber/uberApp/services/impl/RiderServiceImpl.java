package com.coding.project.uber.uberApp.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.dto.DriverDto;
import com.coding.project.uber.uberApp.dto.RideDto;
import com.coding.project.uber.uberApp.dto.RideRequestDto;
import com.coding.project.uber.uberApp.dto.RiderDto;
import com.coding.project.uber.uberApp.enities.RideRequest;
import com.coding.project.uber.uberApp.enities.Rider;
import com.coding.project.uber.uberApp.enities.User;
import com.coding.project.uber.uberApp.enities.enums.RideRequestStatus;
import com.coding.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.coding.project.uber.uberApp.repositories.RideRequestRepository;
import com.coding.project.uber.uberApp.repositories.RiderRepository;
import com.coding.project.uber.uberApp.services.RiderService;
import com.coding.project.uber.uberApp.strategies.RideStrategyManager;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService{

    private final ModelMapper modalMapper;

    private final RideStrategyManager rideStrategyManager;

    private final RideRequestRepository rideRequestRepository;

    private final RiderRepository riderRepository;

    @Override
    @Transactional
    public RideRequestDto createRideRequest(RideRequestDto rideRequestDto) {

        Rider rider = getCurrentRider();
        RideRequest rideRequest = modalMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);
        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);
        RideRequest savedRideRequest =rideRequestRepository.save(rideRequest);
        rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(savedRideRequest);
        return modalMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelRide'");
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rateDriver'");
    }

    @Override
    public RiderDto getmyprofile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getmyprofile'");
    }

    @Override
    public List<RideDto> getAllMyRides() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllMyRides'");
    }

    @Override
    public Rider CreateNewRider(User user) {
        Rider rider = Rider.builder().user(user).rating(0.0).build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        return riderRepository.findById(1L).orElseThrow(()-> new ResourceNotFoundException("Rider Not Found With riderId"+1));
    }

  

}
