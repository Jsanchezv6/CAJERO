package com.proyecto.cajero.service;

import com.proyecto.cajero.model.Transaccion;

import java.util.List;

public interface ITransaccionService {

    List<Transaccion> findAll();
}
