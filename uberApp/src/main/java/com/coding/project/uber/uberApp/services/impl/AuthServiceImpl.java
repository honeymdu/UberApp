package com.coding.project.uber.uberApp.services.impl;

import org.springframework.stereotype.Service;

import com.coding.project.uber.uberApp.dto.DriverDto;
import com.coding.project.uber.uberApp.dto.UserDto;
import com.coding.project.uber.uberApp.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String login(String email, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public UserDto signup() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signup'");
    }

    @Override
    public DriverDto onBoardDriver(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onBoardDriver'");
    }

}
