package com.coding.project.uber.uberApp.services.impl;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.dto.DriverDto;
import com.coding.project.uber.uberApp.dto.SignupDto;
import com.coding.project.uber.uberApp.dto.UserDto;
import com.coding.project.uber.uberApp.enities.Driver;
import com.coding.project.uber.uberApp.enities.User;
import com.coding.project.uber.uberApp.enities.enums.Role;
import com.coding.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.coding.project.uber.uberApp.exceptions.RuntimeConfilictException;
import com.coding.project.uber.uberApp.repositories.UserRepository;
import com.coding.project.uber.uberApp.services.AuthService;
import com.coding.project.uber.uberApp.services.DriverService;
import com.coding.project.uber.uberApp.services.RiderService;
import com.coding.project.uber.uberApp.services.WalletService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;

    @Override
    public String login(String email, String password) {
        return null;
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        userRepository.findByEmail(signupDto.getEmail()).orElseThrow(
                () -> new RuntimeConfilictException(
                        "Cannot SignUp, User Already Exists with Email " + signupDto.getEmail()));

        User Mappeduser = modelMapper.map(signupDto, User.class);
        Mappeduser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(Mappeduser);
        riderService.CreateNewRider(savedUser);
        walletService.createNewWallet(savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    @Transactional
    public DriverDto onBoardDriver(Long userId, String vehicleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not found with userId =" + userId));
        if (user.getRoles().contains(Role.DRIVER)) {
            throw new RuntimeConfilictException("User with user Id =" + userId + " is Already a Driver");
        }

        Driver driver = Driver.builder()
                .user(user).rating(0.0)
                .vehicleId(null)
                .available(true)
                .build();

        user.getRoles().add(Role.DRIVER);
        userRepository.save(user);
        Driver savedDriver = driverService.CreateNewDriver(driver);
        return modelMapper.map(savedDriver, DriverDto.class);
    }

}
