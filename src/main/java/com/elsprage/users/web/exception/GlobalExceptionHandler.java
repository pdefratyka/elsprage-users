package com.elsprage.users.web.exception;

import com.elsprage.users.common.exception.UserValidationException;
import com.elsprage.users.common.exception.WrongCredentialsException;
import com.elsprage.users.model.response.ExceptionResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(UserValidationException.class)
    public ResponseEntity<ExceptionResponse> handleUserValidationException(UserValidationException ex) {
        log.error(ex.getMessage(), ex);
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleWrongCredentialsException(WrongCredentialsException ex) {
        log.error(ex.getMessage(), ex);
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(response);
    }
}