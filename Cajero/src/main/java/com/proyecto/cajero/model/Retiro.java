package com.proyecto.cajero.model;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Retiro {

    private String cuenta;
    private String pin;
    private BigDecimal monto;

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
