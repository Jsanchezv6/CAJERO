package com.proyecto.cajero.controller;

import com.proyecto.cajero.model.UserCredentials;
import com.proyecto.cajero.model.Usuario;
import com.proyecto.cajero.model.ServiceResponse;
import com.proyecto.cajero.service.UsuarioService;
import com.proyecto.cajero.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/usuario")
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/list")
    public ResponseEntity<List<Usuario>> list() {
        List<Usuario> result = usuarioService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ServiceResponse> save(@RequestBody Usuario usuario) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = usuarioService.save(usuario);
        if (result == 1) {
            serviceResponse.setMessage("Completado");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ServiceResponse> update(@RequestBody Usuario usuario) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = usuarioService.update(usuario);
        if (result == 1) {
            serviceResponse.setMessage("Actualización exitosa");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @GetMapping("/validate/{nombreUsuario}/{pin}")
    public ResponseEntity<String> validateUserAndPin(@PathVariable String nombreUsuario, @PathVariable String pin) {
        boolean isValid = usuarioService.validateUser(nombreUsuario, pin);
        if (isValid) {
            return ResponseEntity.ok("Usuario y PIN válidos");
        } else {
            return ResponseEntity.ok("Usuario y/o PIN inválidos");
        }
    }



    @GetMapping("/delete/{id}")
    public ResponseEntity<ServiceResponse> delete(@PathVariable int id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = usuarioService.deleteById(id);

        if (result == 1) {
            serviceResponse.setMessage("Registro eliminado exitosamente");
        } else {
            serviceResponse.setMessage("Registro no encontrado o no se pudo eliminar");
        }

        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
}
