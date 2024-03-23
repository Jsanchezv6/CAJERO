package com.proyecto.cajero.controller;

import com.proyecto.cajero.model.Cajero;
import com.proyecto.cajero.model.ServiceResponse;
import com.proyecto.cajero.service.CajeroService;
import com.proyecto.cajero.service.ICajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/cajero")
@CrossOrigin("*")
public class CajeroController {
    @Autowired
    private ICajeroService cajeroService;

    @GetMapping("/list")
    public ResponseEntity<List<Cajero>> list() {
        List<Cajero> result = cajeroService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ServiceResponse> save(@RequestBody Cajero cajero) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = cajeroService.save(cajero);
        if (result == 1) {
            serviceResponse.setMessage("Completado");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ServiceResponse> update(@RequestBody Cajero cajero) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = cajeroService.update(cajero);
        if (result == 1) {
            serviceResponse.setMessage("Actualización exitosa");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<ServiceResponse> delete(@PathVariable int id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = cajeroService.deleteById(id);

        if (result == 1) {
            serviceResponse.setMessage("Registro eliminado exitosamente");
        } else {
            serviceResponse.setMessage("Registro no encontrado o no se pudo eliminar");
        }

        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
}
