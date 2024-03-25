package com.proyecto.cajero.model;


import java.math.BigDecimal;
import lombok.Data;

@Data
public class Deposito {

    private String cuenta;
    private BigDecimal monto;

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
