package com.proyecto.cajero.controller;

import com.proyecto.cajero.model.Transferencia;
import com.proyecto.cajero.model.ServiceResponse;

import com.proyecto.cajero.service.ITransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transferencia")
@CrossOrigin("*")
public class TransferenciaController {

    @Autowired
    private ITransferenciaService transferenciaService;

    @PostMapping("/save")
    public ResponseEntity<ServiceResponse> realizarTransferencia(@RequestBody Transferencia transferencia) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = transferenciaService.save(transferencia);
        if (result == 1) {
            serviceResponse.setMessage("Transferencia realizada exitosamente");
        } else {
            serviceResponse.setMessage("No se pudo realizar la transferencia");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
}
