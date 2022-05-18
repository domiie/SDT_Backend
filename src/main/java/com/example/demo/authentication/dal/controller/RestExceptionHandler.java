package com.example.demo.authentication.dal.controller;
import com.example.demo.authentication.dal.service.ErrorMessageDto;
import com.example.demo.authentication.dal.service.UserServiceException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<String> handleException(UserServiceException userServiceException) {
        return ResponseEntity.status(444)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userServiceException.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException validationException) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError  fieldError : validationException.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(456)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errors);
    }

}
