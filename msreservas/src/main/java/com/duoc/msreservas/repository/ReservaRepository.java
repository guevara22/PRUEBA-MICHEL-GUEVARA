package com.duoc.msreservas.repository;

import com.duoc.msreservas.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    @Query("SELECT r FROM Reserva r WHERE r.fechaInicio >= :fecha ORDER BY r.fechaInicio DESC")
    List<Reserva> buscarDesdeFecha(@Param("fecha") LocalDate fecha);
}
