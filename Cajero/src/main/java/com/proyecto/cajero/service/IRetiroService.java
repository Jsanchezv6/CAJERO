package com.proyecto.cajero.service;
import com.proyecto.cajero.model.Retiro;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public interface IRetiroService {

    int save(Retiro retiro);
}
