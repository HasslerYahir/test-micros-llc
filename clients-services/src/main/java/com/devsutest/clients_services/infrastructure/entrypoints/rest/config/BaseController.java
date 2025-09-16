package com.devsutest.clients_services.infrastructure.entrypoints.rest.config;

import com.devsutest.clients_services.application.exceptions.UserExistException;
import com.devsutest.clients_services.application.exceptions.UserNotFoundException;
import com.devsutest.clients_services.infrastructure.entrypoints.rest.data.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Error fields required")
                .fieldErrors(ex.getBindingResult().getFieldErrors()
                        .stream().map(error -> error.getField()
                                .concat(error.getDefaultMessage() != null ? error.getDefaultMessage() : "")
                        ).toList())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Error fields required")
                .fieldErrors(ex.getConstraintViolations()
                        .stream().map(violation -> violation.getPropertyPath().toString()
                                .concat(violation.getMessage())
                        ).toList())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<ErrorResponse> handleUserExits(UserExistException ex) {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

}
