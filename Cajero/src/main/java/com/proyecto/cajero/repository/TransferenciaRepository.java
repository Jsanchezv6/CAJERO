package com.proyecto.cajero.repository;

import com.proyecto.cajero.model.Transferencia;
import com.proyecto.cajero.repository.ITransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransferenciaRepository implements ITransferenciaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Transferencia transferencia) {
        String storedProcedure = "EXEC [dbo].[sp_TransferenciaCuenta] ?, ?, ?, ?";
        return jdbcTemplate.update(storedProcedure, transferencia.getCuentaOrigen(), transferencia.getPin(), transferencia.getCuentaDestino(), transferencia.getMonto());
    }
}
