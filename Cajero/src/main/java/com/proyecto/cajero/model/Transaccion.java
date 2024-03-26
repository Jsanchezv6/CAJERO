package com.proyecto.cajero.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaccion {
    int idTransaccion;
    String tipoTransaccion;
    double monto;
    LocalDateTime fechahora;
    String numeroCuentaOrigen;
    String numeroCuentaDestino;
    String numeroTelefonoDestino;


}
