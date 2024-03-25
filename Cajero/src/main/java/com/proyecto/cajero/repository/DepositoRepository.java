package com.proyecto.cajero.repository;

import com.proyecto.cajero.repository.IDepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.proyecto.cajero.model.Deposito;

@Repository
public class DepositoRepository implements IDepositoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Deposito deposito) {
        String storedProcedure = "EXEC [dbo].[sp_Deposito] ?, ?";
        return jdbcTemplate.update(storedProcedure, deposito.getCuenta(), deposito.getMonto());
    }
}
