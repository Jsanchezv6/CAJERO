package com.proyecto.cajero.repository;

import com.proyecto.cajero.model.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TransaccionRepository implements ITransaccionRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Transaccion> findAll() {
        return null;
    }

    @Override
    public Transaccion findById(int idTransaccion) {
        return null;
    }

    @Override
    public Transaccion save(Transaccion transaccion) {
        return null;
    }

    @Override
    public void deleteById(int idTransaccion) {

    }

    @Override
    public void deposito(Transaccion transaccion) {
        try (Connection connection = dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall("{call sp_Deposito(?, ?)}")) {

            stmt.setString(1, transaccion.getNumeroCuentaOrigen());
            stmt.setDouble(2, transaccion.getMonto());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al realizar el depósito.");
        }
    }

    @Override
    public void retiro(Transaccion transaccion) {
        try (Connection connection = dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall("{call sp_Retiro(?, ?)}")) {

            stmt.setString(1, transaccion.getNumeroCuentaOrigen());
            stmt.setDouble(2, transaccion.getMonto());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al realizar el retiro.");
        }
    }

    @Override
    public void transferenciaCuenta(Transaccion transaccion) {
        try (Connection connection = dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall("{call sp_TransferenciaCuenta(?, ?, ?)}")) {

            stmt.setString(1, transaccion.getNumeroCuentaOrigen());
            stmt.setString(2, transaccion.getNumeroCuentaDestino());
            stmt.setDouble(3, transaccion.getMonto());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al realizar la transferencia a otra cuenta.");
        }
    }

    @Override
    public void transferenciaTelefono(Transaccion transaccion) {
        try (Connection connection = dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall("{call sp_TransferenciaCelular(?, ?, ?)}")) {

            stmt.setString(1, transaccion.getNumeroCuentaOrigen());
            stmt.setInt(2, transaccion.getNumeroTelefonoDestino());
            stmt.setDouble(3, transaccion.getMonto());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al realizar la transferencia a través de teléfono celular.");
        }
    }

    // Implementaciones restantes de métodos de ITransaccionRepository
}
