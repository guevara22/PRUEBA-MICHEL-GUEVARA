package com.duoc.msvehiculos.service;

import com.duoc.msvehiculos.modelo.Vehiculo;

import java.util.List;

public interface VehiculoService {
    List<Vehiculo> listarTodo();
    Vehiculo guardar(Vehiculo vehiculo);
}
