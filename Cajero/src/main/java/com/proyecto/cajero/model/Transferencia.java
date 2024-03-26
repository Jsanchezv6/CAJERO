package com.proyecto.cajero.model;

import java.math.BigDecimal;
import lombok.Data;


@Data
public class Transferencia {
    private String cuentaOrigen;
    private String pin;
    private String CuentaDestino;
    private BigDecimal monto;

}
