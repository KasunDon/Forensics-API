package uk.co.which.api.forensics.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uk.co.which.api.forensics.exception.UserPredictionExceededException;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UserPredictionExceededException.class)
    @ResponseStatus(NOT_ACCEPTABLE)
    @ResponseBody
    public ResponseEntity<?> userPredictionException() {
        return new ResponseEntity<>(NOT_ACCEPTABLE);

    }
}