package com.proyecto.cajero.repository;

import com.proyecto.cajero.model.Cajero;

import java.util.List;
import java.util.Optional;

import com.proyecto.cajero.model.Cajero;

import java.util.List;

public interface ICajeroRepository {
    List<Cajero> findAll();
    int save(Cajero cajero);
    int update(Cajero cajero);
    int deleteById(int id);

}
