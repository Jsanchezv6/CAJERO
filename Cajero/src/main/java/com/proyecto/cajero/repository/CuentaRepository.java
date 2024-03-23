package com.proyecto.cajero.repository;
import com.proyecto.cajero.model.Cuenta;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;




@Service
public class CuentaRepository implements ICuentaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Cuenta> findAll() {
        String SQL = "SELECT * FROM Cuenta";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Cuenta.class));
    }

    @Override
    public int save(Cuenta cuenta) {
        String SQL = "INSERT INTO Cuenta (cuenta, saldo, idUsuario, telefono) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(SQL, cuenta.getCuenta(), cuenta.getSaldo(), cuenta.getIdUsuario(), cuenta.getTelefono());
    }

    @Override
    public int update(Cuenta cuenta) {
        String SQL = "UPDATE Cuenta SET saldo=?, idUsuario=?, telefono=? WHERE cuenta=?";
        return jdbcTemplate.update(SQL, cuenta.getSaldo(), cuenta.getIdUsuario(), cuenta.getTelefono(), cuenta.getCuenta());
    }

    @Override
    public int deleteById(int id) {
        String SQL = "DELETE FROM Cuenta WHERE cuenta=?";
        return jdbcTemplate.update(SQL, id);
    }
}
