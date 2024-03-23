package com.proyecto.cajero.service;

import com.proyecto.cajero.model.Usuario;
import com.proyecto.cajero.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        try {
            return usuarioRepository.findAll();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public int save(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public int update(Usuario usuario) {
        try {
            return usuarioRepository.update(usuario);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public int deleteById(int id) {
        try {
            return usuarioRepository.deleteById(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    // Método para validar el usuario
    public boolean validateUser(String nombreUsuario, String pin) {
        try {
            return usuarioRepository.validateUser(nombreUsuario, pin);
        } catch (Exception ex) {
            // Si ocurre alguna excepción, relanzarla para que sea manejada en un nivel superior
            throw ex;
        }
    }

}
