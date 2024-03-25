package com.proyecto.cajero.repository;

import com.proyecto.cajero.model.Retiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RetiroRepository implements IRetiroRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Retiro retiro) {
        String storedProcedure = "EXEC [dbo].[sp_retiro] ?, ?, ?";
        return jdbcTemplate.update(storedProcedure, retiro.getCuenta(), retiro.getPin(), retiro.getMonto());
    }
}
