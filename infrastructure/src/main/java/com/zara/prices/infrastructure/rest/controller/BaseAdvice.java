package com.zara.prices.infrastructure.rest.controller;


import com.zara.prices.application.exception.BadRequestException;
import com.zara.prices.application.exception.GenericException;
import com.zara.prices.application.exception.error.Error;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BaseAdvice {

    /**
     * Capture bad request exception
     *
     * @param ex a {@link BadRequestException} object.
     * @return a object.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Error> handleException(BadRequestException ex) {
        return new ResponseEntity<>(new Error(ex.getDescription()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Capture generic exception
     *
     * @param ex a {@link GenericException} object.
     * @return a object.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<Error> handleException(GenericException ex) {
        return new ResponseEntity<>(new Error(ex.getDescription()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
