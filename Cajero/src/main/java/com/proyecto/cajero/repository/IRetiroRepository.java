package com.proyecto.cajero.repository;


import com.proyecto.cajero.model.Retiro;

import java.math.BigDecimal;
import java.util.List;

public interface IRetiroRepository {
    int save(Retiro retiro);
}