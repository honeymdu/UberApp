package com.coding.project.uber.uberApp.advices;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {
    private HttpStatus httpStatus;
    private String message;

}
