package com.duoc.mspagos.repository;

import com.duoc.mspagos.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
    @Query("SELECT p FROM Pago p WHERE p.monto BETWEEN :minimo AND :maximo ORDER BY p.fechaPago DESC")
    List<Pago> buscarPorRango(@Param("minimo") BigDecimal minimo, @Param("maximo") BigDecimal maximo);
}
