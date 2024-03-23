package com.proyecto.cajero.repository;

import com.proyecto.cajero.model.Transaccion;

import java.util.List;

public interface ITransaccionRepository {
    List<Transaccion> findAll();
    Transaccion findById(int idTransaccion);
    Transaccion save(Transaccion transaccion);
    void deleteById(int idTransaccion);
    // Otros métodos específicos para la gestión de transacciones con JDBC
    void deposito(Transaccion transaccion);
    void retiro(Transaccion transaccion);
    void transferenciaCuenta(Transaccion transaccion);
    void transferenciaTelefono(Transaccion transaccion);
}
