package com.proyecto.cajero.repository;

import com.proyecto.cajero.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioRepository implements IUsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Usuario> findAll() {
        String SQL = "SELECT * FROM Usuario";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Usuario.class));
    }

    @Override
    public int save(Usuario usuario) {
        String SQL = "INSERT INTO Usuario (nombreUsuario, pin, rol) VALUES (?, ?, ?)";
        return jdbcTemplate.update(SQL, usuario.getNombreUsuario(), usuario.getPin(), usuario.getRol());
    }

    @Override
    public int update(Usuario usuario) {
        String SQL = "UPDATE Usuario SET nombreUsuario=?, pin=?, rol=? WHERE idUsuario=?";
        return jdbcTemplate.update(SQL, usuario.getNombreUsuario(), usuario.getPin(), usuario.getRol(), usuario.getIdUsuario());
    }

    @Override
    public int deleteById(int id) {
        String SQL = "DELETE FROM Usuario WHERE idUsuario=?";
        return jdbcTemplate.update(SQL, id);
    }

    @Override
    public boolean validateUser(String username, String pin) {
        String SQL = "SELECT COUNT(*) FROM Usuario WHERE nombreUsuario=? AND pin=?";
        int count = jdbcTemplate.queryForObject(SQL, Integer.class, username, pin);
        return count == 1;
    }

}
