package com.coding.project.uber.uberApp.controllers;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.project.uber.uberApp.dto.DriverDto;
import com.coding.project.uber.uberApp.dto.LoginRequestDto;
import com.coding.project.uber.uberApp.dto.LoginResponceDto;
import com.coding.project.uber.uberApp.dto.OnBoardDriverDto;
import com.coding.project.uber.uberApp.dto.SignupDto;
import com.coding.project.uber.uberApp.dto.UserDto;
import com.coding.project.uber.uberApp.services.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto) {
        return new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/onBoardNewDriver/{userId}")
    public ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId,
            @RequestBody OnBoardDriverDto onBoardDriverDto) {
        return new ResponseEntity<>(authService.onBoardDriver(userId, onBoardDriverDto.getVehicleId()),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponceDto> login(@RequestBody LoginRequestDto LoginRequestDto,
            HttpServletResponse response) {
        String token[] = authService.login(LoginRequestDto.getEmail(), LoginRequestDto.getPassword());

        Cookie cookie = new Cookie("refreshToken", token[1]);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(new LoginResponceDto(token[0]));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the cookies"));
        String AccessToken = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(new LoginResponceDto(AccessToken));
    }

}
