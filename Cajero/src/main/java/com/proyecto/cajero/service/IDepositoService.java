package com.proyecto.cajero.service;
import com.proyecto.cajero.model.Deposito;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public interface IDepositoService {

    int save(Deposito deposito);
}
