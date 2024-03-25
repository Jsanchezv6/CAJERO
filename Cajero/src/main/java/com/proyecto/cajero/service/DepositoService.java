package com.proyecto.cajero.service;


import com.proyecto.cajero.model.Deposito;
import com.proyecto.cajero.repository.IDepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositoService implements IDepositoService {

    @Autowired
    private IDepositoRepository depositoRepository;


    @Override
    public int save(Deposito deposito) {
        try {
            return depositoRepository.save(deposito);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
