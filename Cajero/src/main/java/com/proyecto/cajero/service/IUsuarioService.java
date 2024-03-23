package com.proyecto.cajero.service;

import com.proyecto.cajero.model.Usuario;

import java.util.List;



public interface IUsuarioService {
    List<Usuario> findAll();
    int save(Usuario usuario);
    int update(Usuario usuario);
    int deleteById(int id);
    boolean validateUser(String nombreUsuario, String pin); // Método de validación de usuario
}
