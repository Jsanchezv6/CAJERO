package com.proyecto.cajero.repository;

import com.proyecto.cajero.model.TCelular;
import com.proyecto.cajero.repository.ITCelularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.proyecto.cajero.model.TCelular;

@Repository
public class TCelularRepository implements ITCelularRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(TCelular tCelular) {
        String storedProcedure = "EXEC [dbo].[sp_TransferenciaCelular] ?, ?, ?, ?";
        return jdbcTemplate.update(storedProcedure, tCelular.getCuentaOrigen(), tCelular.getPin(), tCelular.getTelefonoDestino(), tCelular.getMonto());
    }
}