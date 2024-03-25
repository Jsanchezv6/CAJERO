package com.proyecto.cajero.controller;

import com.proyecto.cajero.model.Deposito;
import com.proyecto.cajero.model.ServiceResponse;
import com.proyecto.cajero.service.IDepositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/deposito")
@CrossOrigin("*")
public class DepositoController {

    @Autowired
    private IDepositoService depositoService;

    @PostMapping("/save")
    public ResponseEntity<ServiceResponse> realizarDeposito(@RequestBody Deposito deposito) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = depositoService.save(deposito);
        if (result == 1) {
            serviceResponse.setMessage("Depósito realizado exitosamente");
        } else {
            serviceResponse.setMessage("No se pudo realizar el depósito");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
}

