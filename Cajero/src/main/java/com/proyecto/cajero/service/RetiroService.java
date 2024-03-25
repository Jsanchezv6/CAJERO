package com.proyecto.cajero.service;


import com.proyecto.cajero.model.Retiro;
import com.proyecto.cajero.repository.IRetiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetiroService implements IRetiroService {

    @Autowired
    private IRetiroRepository retiroRepository;


    @Override
    public int save(Retiro retiro) {
        try {
            return retiroRepository.save(retiro);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
