package com.devsutest.accounts_services.infraestructure.entrypoints.rest.config;

import com.devsutest.accounts_services.application.exceptions.*;
import com.devsutest.accounts_services.infraestructure.entrypoints.rest.data.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.format.DateTimeParseException;

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

    @ExceptionHandler(DateNotValidException.class)
    public ResponseEntity<ErrorResponse> handleDateNotValidException(DateNotValidException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, DateTimeParseException.class})
    public ResponseEntity<ErrorResponse> handleInvalidDateFormat(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Invalid date format. Please use ISO-8601 (e.g. 2025-09-14T00:00:00Z)")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler({UserNotFoundException.class,
            AccountNotFoundException.class,
            TypeAccountNotFoundException.class,
            TypeMovementNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleUserNotFound(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NotAccountAvailableException.class,
            InsufficientBalanceException.class})
    public ResponseEntity<ErrorResponse> errorDomain(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
