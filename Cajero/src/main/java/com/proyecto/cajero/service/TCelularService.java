package com.proyecto.cajero.service;


import com.proyecto.cajero.model.Retiro;
import com.proyecto.cajero.model.TCelular;
import com.proyecto.cajero.repository.ITCelularRepository;
import com.proyecto.cajero.repository.ITCelularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TCelularService implements ITCelularService {

    @Autowired
    private ITCelularRepository tCelularRepository;


    @Override
    public int save(TCelular tCelular) {
        try {
            return tCelularRepository.save(tCelular);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
