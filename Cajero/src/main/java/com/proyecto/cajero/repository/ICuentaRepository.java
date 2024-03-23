package com.proyecto.cajero.repository;

import com.proyecto.cajero.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface ICuentaRepository {
    List<Cuenta> findAll();
    int save(Cuenta cuenta);
    int update(Cuenta cuenta);
    int deleteById(int id);
}
