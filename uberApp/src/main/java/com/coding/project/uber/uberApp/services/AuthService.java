package com.coding.project.uber.uberApp.services;

import com.coding.project.uber.uberApp.dto.DriverDto;
import com.coding.project.uber.uberApp.dto.UserDto;

public interface AuthService {

    String login(String email,String password);
    
    UserDto signup();

    DriverDto onBoardDriver(Long userId);


}
