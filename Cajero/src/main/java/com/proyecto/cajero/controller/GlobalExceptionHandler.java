package com.proyecto.cajero.controller;

import com.proyecto.cajero.model.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServiceResponse> handleException(Exception e) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setMessage("No se pudo realizar el retiro, verifique su datos.");
        return new ResponseEntity<>(serviceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
