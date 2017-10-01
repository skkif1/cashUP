package com.omotyliu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "CHECK YOUR REQUEST PARAMETERS")
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public void jacksonExceptionHandler(Exception ex) throws Exception {

    }
}
