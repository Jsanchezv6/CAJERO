package com.proyecto.cajero.service;

import com.proyecto.cajero.model.Cajero;


import com.proyecto.cajero.repository.ICajeroRepository;
import com.proyecto.cajero.service.ICajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CajeroService implements ICajeroService {

    @Autowired
    private ICajeroRepository cajeroRepository;

    @Override
    public List<Cajero> findAll() {
        try {
            return cajeroRepository.findAll();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public int save(Cajero cajero) {
        try {
            return cajeroRepository.save(cajero);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public int update(Cajero cajero) {
        try {
            return cajeroRepository.update(cajero);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public int deleteById(int id) {
        try {
            return cajeroRepository.deleteById(id);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
