package com.proyecto.cajero.repository;

import com.proyecto.cajero.model.Cajero;
import com.proyecto.cajero.model.Cuenta;
import com.proyecto.cajero.model.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TransaccionRepository implements ITransaccionRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TransaccionRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Transaccion> findAll() {
        String SQL = "SELECT * FROM Transaccion";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Transaccion.class));
    }

}
