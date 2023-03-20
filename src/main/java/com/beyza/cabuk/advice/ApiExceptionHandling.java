package com.beyza.cabuk.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ExceptionResponse> illegalException (Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),"1000",exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(UserNotFound.class)
    public final ResponseEntity<ExceptionResponse> UserNotFound (Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),"2000",exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(Exception.class)      // to throw other unexpected exceptions
    public final ResponseEntity<ExceptionResponse> exception (Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),"3000",exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.EXPECTATION_FAILED);
    }


}
