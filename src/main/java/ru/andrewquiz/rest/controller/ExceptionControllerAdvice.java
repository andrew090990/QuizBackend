package ru.andrewquiz.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.andrewquiz.dto.ExceptionResponse;
import ru.andrewquiz.rest.exception.RestException;
import ru.andrewquiz.rest.exception.UnknownResourceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Andrew on 09.04.2017.
 */

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = UnknownResourceException.class)
    protected ResponseEntity<ExceptionResponse> handleUnknownResourceException(RestException ex, HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse(ex);
        response.setMessage("Resource not found: " + request.getRequestURI());
        response.setDeveloperMessage("Resource not found: " + request.getRequestURI());

        return new ResponseEntity<ExceptionResponse>(response, ex.getStatus());
    }
}
