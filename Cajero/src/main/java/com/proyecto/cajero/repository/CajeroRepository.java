package com.proyecto.cajero.repository;
import com.proyecto.cajero.model.Cajero;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;



@Service
public class CajeroRepository implements ICajeroRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Cajero> findAll() {
        String SQL = "SELECT * FROM Cajero";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Cajero.class));
    }

    @Override
    public int save(Cajero cajero) {
        String SQL = "INSERT INTO Cajero (id, cantidad, fecha, descripcion) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(SQL, cajero.getId(), cajero.getCantidad(), cajero.getFecha(), cajero.getDescripcion());
    }

    @Override
    public int update(Cajero cajero) {
        String SQL = "UPDATE Cajero SET cantidad=?, fecha=?, descripcion=? WHERE id=?";
        return jdbcTemplate.update(SQL, cajero.getCantidad(), cajero.getFecha(), cajero.getDescripcion(), cajero.getId());
    }

    @Override
    public int deleteById(int id) {
        String SQL = "DELETE FROM Cajero WHERE id=?";
        return jdbcTemplate.update(SQL, id);
    }
}
