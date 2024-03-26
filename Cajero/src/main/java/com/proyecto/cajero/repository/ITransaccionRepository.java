package com.proyecto.cajero.repository;

import com.proyecto.cajero.model.Transaccion;

import java.util.List;

public interface ITransaccionRepository {
    List<Transaccion> findAll();

}
