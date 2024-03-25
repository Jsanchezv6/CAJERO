package com.proyecto.cajero.controller;

import com.proyecto.cajero.model.Retiro;
import com.proyecto.cajero.model.ServiceResponse;
import com.proyecto.cajero.service.IRetiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/retiro")
@CrossOrigin("*")
public class RetiroController {

    @Autowired
    private IRetiroService retiroService;

    @PostMapping("/save")
    public ResponseEntity<ServiceResponse> realizarRetiro(@RequestBody Retiro retiro) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = retiroService.save(retiro);
        if (result == 1) {
            serviceResponse.setMessage("Retiro realizado exitosamente");
        } else {
            serviceResponse.setMessage("No se pudo realizar el retiro, verifique su datos");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
}
