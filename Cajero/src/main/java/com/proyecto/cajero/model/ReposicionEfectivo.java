package com.proyecto.cajero.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReposicionEfectivo {
    int id;
    private LocalDateTime fechahora;
    int billetesDe100;
    int billetesDe50;
    double total;
    Usuario administrador;


}
