package com.proyecto.cajero.controller;

import com.proyecto.cajero.model.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    private DataSource dataSource; // Inyectar el DataSource de la base de datos

    // Método para realizar un depósito
    @PostMapping("/deposito")
    public String deposito(@RequestBody Transaccion transaccion) {
        try (Connection connection = dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall("{call sp_Deposito(?, ?)}")) {

            stmt.setString(1, transaccion.getNumeroCuentaOrigen());
            stmt.setDouble(2, transaccion.getMonto());
            stmt.execute();

            return "Depósito realizado con éxito.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al realizar el depósito.";
        }
    }

    // Método para realizar un retiro
    @PostMapping("/retiro")
    public String retiro(@RequestBody Transaccion transaccion) {
        try (Connection connection = dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall("{call sp_Retiro(?, ?)}")) {

            stmt.setString(1, transaccion.getNumeroCuentaOrigen());
            stmt.setDouble(2, transaccion.getMonto());
            stmt.execute();

            return "Retiro realizado con éxito.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al realizar el retiro.";
        }
    }

    // Método para realizar una transferencia a otra cuenta
    @PostMapping("/transferenciaCuenta")
    public String transferenciaCuenta(@RequestBody Transaccion transaccion) {
        try (Connection connection = dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall("{call sp_TransferenciaCuenta(?, ?, ?)}")) {

            stmt.setString(1, transaccion.getNumeroCuentaOrigen());
            stmt.setString(2, transaccion.getNumeroCuentaDestino());
            stmt.setDouble(3, transaccion.getMonto());
            stmt.execute();

            return "Transferencia a otra cuenta realizada con éxito.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al realizar la transferencia a otra cuenta.";
        }
    }

    // Método para realizar una transferencia a través de un teléfono celular
    @PostMapping("/transferenciaTelefono")
    public String transferenciaTelefono(@RequestBody Transaccion transaccion) {
        try (Connection connection = dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall("{call sp_TransferenciaCelular(?, ?, ?)}")) {

            stmt.setString(1, transaccion.getNumeroCuentaOrigen());
            stmt.setInt(2, transaccion.getNumeroTelefonoDestino());
            stmt.setDouble(3, transaccion.getMonto());
            stmt.execute();

            return "Transferencia a través de teléfono celular realizada con éxito.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al realizar la transferencia a través de teléfono celular.";
        }
    }
}
