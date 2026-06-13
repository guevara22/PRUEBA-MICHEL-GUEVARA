package com.duoc.msempleados.repository;

import com.duoc.msempleados.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    @Query(value = "SELECT * FROM empleados WHERE activo = true AND YEAR(fecha_contratacion) = :anio ORDER BY nombre_completo", nativeQuery = true)
    List<Empleado> buscarActivosPorAnio(@Param("anio") int anio);
}
