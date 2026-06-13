package com.duoc.msvehiculos.repository;

import com.duoc.msvehiculos.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.math.BigDecimal;



public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    List<Vehiculo> findByDisponibleTrueAndPrecioArriendoDiarioLessThan(BigDecimal precioMaximo);
}
