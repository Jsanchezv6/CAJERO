package com.proyecto.cajero.service;

import com.proyecto.cajero.model.Transaccion;

import java.util.List;

public interface ITransaccionService {
    List<Transaccion> obtenerTodasLasTransacciones();
    Transaccion obtenerTransaccionPorId(int idTransaccion);
    Transaccion crearTransaccion(Transaccion transaccion);
    Transaccion actualizarTransaccion(int idTransaccion, Transaccion transaccion);
    void eliminarTransaccion(int idTransaccion);
    void deposito(Transaccion transaccion);
    void retiro(Transaccion transaccion);
    void transferenciaCuenta(Transaccion transaccion);
    void transferenciaTelefono(Transaccion transaccion);
}
