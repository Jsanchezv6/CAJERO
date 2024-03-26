package com.proyecto.cajero.service;


import com.proyecto.cajero.model.Transaccion;


import com.proyecto.cajero.repository.ITransaccionRepository;
import com.proyecto.cajero.service.ITransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransaccionService implements ITransaccionService {

    @Autowired
    private ITransaccionRepository transaccionRepository;

    @Override
    public List<Transaccion> findAll() {
        try {
            return transaccionRepository.findAll();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
