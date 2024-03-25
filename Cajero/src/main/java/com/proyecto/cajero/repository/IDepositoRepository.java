package com.proyecto.cajero.repository;


import com.proyecto.cajero.model.Deposito;

import java.math.BigDecimal;
import java.util.List;

public interface IDepositoRepository {
    int save(Deposito deposito);
}