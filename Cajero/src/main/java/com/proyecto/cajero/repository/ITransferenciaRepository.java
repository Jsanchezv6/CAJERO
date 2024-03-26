package com.proyecto.cajero.repository;




import com.proyecto.cajero.model.Transferencia;

public interface ITransferenciaRepository {
    int save(Transferencia transferencia);
}