package com.proyecto.cajero.controller;
import com.proyecto.cajero.model.Transaccion;
import com.proyecto.cajero.service.ITransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/transaccion")
@CrossOrigin("*")
public class TransaccionController {
    @Autowired
    private ITransaccionService transaccionService;

    @GetMapping("/list")
    public ResponseEntity<List<Transaccion>> list() {
        List<Transaccion> result = transaccionService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}


