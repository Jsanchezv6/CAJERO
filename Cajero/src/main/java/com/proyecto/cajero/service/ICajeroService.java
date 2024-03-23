package com.proyecto.cajero.service;


import com.proyecto.cajero.model.Cajero;

import java.util.List;

public interface ICajeroService {
    List<Cajero> findAll();
    int save(Cajero cajero);
    int update(Cajero cajero);
    int deleteById(int id);
}
