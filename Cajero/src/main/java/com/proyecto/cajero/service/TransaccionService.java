package com.proyecto.cajero.service;

import com.proyecto.cajero.model.Transaccion;
import com.proyecto.cajero.repository.ITransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionService implements ITransaccionService {

    @Autowired
    private ITransaccionRepository transaccionRepository;

    @Override
    public List<Transaccion> obtenerTodasLasTransacciones() {
        return transaccionRepository.findAll();
    }

    @Override
    public Transaccion obtenerTransaccionPorId(int idTransaccion) {
        return transaccionRepository.findById(idTransaccion);
    }

    @Override
    public Transaccion crearTransaccion(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    @Override
    public Transaccion actualizarTransaccion(int idTransaccion, Transaccion transaccion) {
        if (transaccionRepository.findById(idTransaccion) != null) {
            transaccion.setIdTransaccion(idTransaccion);
            return transaccionRepository.save(transaccion);
        }
        return null;
    }

    @Override
    public void eliminarTransaccion(int idTransaccion) {
        transaccionRepository.deleteById(idTransaccion);
    }

    @Override
    public void deposito(Transaccion transaccion) {
        transaccionRepository.deposito(transaccion);
    }

    @Override
    public void retiro(Transaccion transaccion) {
        transaccionRepository.retiro(transaccion);
    }

    @Override
    public void transferenciaCuenta(Transaccion transaccion) {
        transaccionRepository.transferenciaCuenta(transaccion);
    }

    @Override
    public void transferenciaTelefono(Transaccion transaccion) {
        transaccionRepository.transferenciaTelefono(transaccion);
    }
}
