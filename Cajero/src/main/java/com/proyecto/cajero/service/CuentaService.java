package com.proyecto.cajero.service;

import com.proyecto.cajero.model.Cuenta;


import com.proyecto.cajero.repository.ICuentaRepository;
import com.proyecto.cajero.service.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CuentaService implements ICuentaService {

    @Autowired
    private ICuentaRepository cuentaRepository;

    @Override
    public List<Cuenta> findAll() {
        try {
            return cuentaRepository.findAll();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public int save(Cuenta cuenta) {
        try {
            return cuentaRepository.save(cuenta);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public int update(Cuenta cuenta) {
        try {
            return cuentaRepository.update(cuenta);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public int deleteById(int id) {
        try {
            return cuentaRepository.deleteById(id);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
