package com.example.springboot23.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleNotFoundException(ResourceNotFoundException ex) {
        ProblemDetail problemDetails = ProblemDetail
                .forStatusAndDetail
                        (HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        problemDetails.setType(URI.create("https://example.com/documentation/errors/city-not-found"));
        problemDetails.setTitle("City Not Found");
        problemDetails.setDetail("City with id " + ex.getId() + " not found.");
        // Adding non-standard property
        problemDetails.setProperty("customerId", ex.getId());
        return problemDetails;
    }
}
