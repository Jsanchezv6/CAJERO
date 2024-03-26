package com.proyecto.cajero.controller;

import com.proyecto.cajero.model.TCelular;
import com.proyecto.cajero.model.ServiceResponse;

import com.proyecto.cajero.service.ITCelularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tcelular")
@CrossOrigin("*")
public class TCelularController {

    @Autowired
    private ITCelularService tCelularService;

    @PostMapping("/save")
    public ResponseEntity<ServiceResponse> realizarTCelular(@RequestBody TCelular tCelular) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = tCelularService.save(tCelular);
        if (result == 1) {
            serviceResponse.setMessage("Transferencia realizada exitosamente");
        } else {
            serviceResponse.setMessage("No se pudo realizar la transferencia");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
}

