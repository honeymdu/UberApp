package com.coding.project.uber.uberApp.advices;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.coding.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.coding.project.uber.uberApp.exceptions.RuntimeConfilictException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    //Handle Predeinfed Exception 'NoSuchElementException' globally
    // @ExceptionHandler(NoSuchElementException.class)
    // public ResponseEntity<ApiError> handelResourceNotFoundException(NoSuchElementException exception){
    //     ApiError apiError = ApiError.builder()
    //     .httpStatus(HttpStatus.NOT_FOUND)
    //     .message("Resource Not Found")
    //     .build();
    //     return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    // }


    //Handle User-deinfed Exception 'ResourceNotFoundException' globally
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponce<?>> handelResourceNotFoundException(ResourceNotFoundException exception){
        ApiError apiError = ApiError.builder()
        .httpStatus(HttpStatus.NOT_FOUND)
        .message(exception.getMessage())
        .build();
        return buildErrorResponceEntity(apiError);
    }

    @ExceptionHandler(RuntimeConfilictException.class)
    public ResponseEntity<ApiResponce<?>> handelRuntimeConflictException(RuntimeConfilictException exception){
        ApiError apiError = ApiError.builder()
        .httpStatus(HttpStatus.CONFLICT)
        .message(exception.getMessage())
        .build();
        return buildErrorResponceEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponce<?>> handelInternalServerError(Exception exception){
        ApiError apiError = ApiError.builder()
        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        .message(exception.getMessage())
        .build();
        return buildErrorResponceEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponce<?>> handelInputValidationError(MethodArgumentNotValidException exception){

        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error ->error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = ApiError.builder()
        .httpStatus(HttpStatus.BAD_REQUEST)
        .message(errors.toString())
        .build();
        return buildErrorResponceEntity(apiError);
    }

    private ResponseEntity<ApiResponce<?>> buildErrorResponceEntity(ApiError apiError){
        return new ResponseEntity<>( new ApiResponce<>(apiError),apiError.getHttpStatus());

    }




}
