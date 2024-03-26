package com.proyecto.cajero.service;

import com.proyecto.cajero.model.Transferencia;
import com.proyecto.cajero.repository.ITransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService implements ITransferenciaService {

    @Autowired
    private ITransferenciaRepository transferenciaRepository;

    @Override
    public int save(Transferencia transferencia) {
        try {
            return transferenciaRepository.save(transferencia);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
