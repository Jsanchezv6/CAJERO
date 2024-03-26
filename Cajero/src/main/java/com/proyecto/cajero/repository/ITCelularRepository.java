package com.proyecto.cajero.repository;




import com.proyecto.cajero.model.TCelular;

import java.math.BigDecimal;
import java.util.List;

public interface ITCelularRepository {
    int save(TCelular tCelular);
}