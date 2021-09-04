package com.anytimemeals.bff.controller_advice;

import com.anytimemeals.bff.exceptions.UserCreationFailedException;
import com.anytimemeals.bff.exceptions.UserNotFoundException;
import com.anytimemeals.bff.exceptions.UserPasswordMismatchException;
import com.anytimemeals.bff.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundExceptionHandler(UserNotFoundException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error("User does not exists")
                .message(exception.getMessage())
                .build();

        log.error(String.valueOf(errorResponse));

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(value = UserPasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> userPasswordMismatchExceptionHandler(UserPasswordMismatchException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("Invalid password !!")
                .message(exception.getMessage())
                .build();

        log.error(String.valueOf(errorResponse));

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
    }

    @ExceptionHandler(value = UserCreationFailedException.class)
    public ResponseEntity<ErrorResponse> userCreationFailedExceptionHandler(UserCreationFailedException exception){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.FAILED_DEPENDENCY.value())
                .error("Failed to create user")
                .message(exception.getMessage())
                .build();

        log.error(String.valueOf(errorResponse));

        return ResponseEntity
                .status(HttpStatus.FAILED_DEPENDENCY)
                .body(errorResponse);
    }
}
