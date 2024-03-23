package com.proyecto.cajero.repository;

import com.proyecto.cajero.model.Usuario;
import java.util.List;
import java.util.Optional;

import com.proyecto.cajero.model.Usuario;

import java.util.List;

public interface IUsuarioRepository {
    List<Usuario> findAll();
    int save(Usuario usuario);
    int update(Usuario usuario);
    int deleteById(int id);

    boolean validateUser(String username, String pin);
}
