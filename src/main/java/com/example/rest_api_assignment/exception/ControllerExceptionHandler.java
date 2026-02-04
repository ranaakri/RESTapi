package com.example.rest_api_assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    //Custom Exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ErrorResponseMessage message = new ErrorResponseMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorResponseMessage>(message, HttpStatus.NOT_FOUND);
    }

    //Generic Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorResponseMessage message = new ErrorResponseMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorResponseMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
