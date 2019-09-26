package ru.bellintegrator.weather_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "ru.bellintegrator.weather_service.controller")
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(ExceptionsHandler.class);

    @ExceptionHandler({WeatherNotFoundException.class})
    protected @ResponseBody
    ResponseEntity<?> handleCustomExceptions(RuntimeException e) {
        log.error(e.getMessage(), e.getCause());
        return new ResponseEntity<>(new ErrorResponse("Weather for this location not found in the database" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    protected @ResponseBody
    ResponseEntity<?> handleAllOtherExceptions(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorResponse("Internal Server Error" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

