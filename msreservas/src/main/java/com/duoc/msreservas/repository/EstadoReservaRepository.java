package com.duoc.msreservas.repository;

import com.duoc.msreservas.model.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstadoReservaRepository extends JpaRepository<EstadoReserva, Integer> {
    boolean existsByNombreIgnoreCase(String nombre);
}
