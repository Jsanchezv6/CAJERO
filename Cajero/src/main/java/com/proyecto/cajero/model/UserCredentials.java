package com.proyecto.cajero.model;

import lombok.Data;
public class UserCredentials {
    private String username;
    private String pin;

    // Constructor
    public UserCredentials() {}

    // Getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
