package com.duoc.mssucursales.repository;

import com.duoc.mssucursales.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
    @Query(value = "SELECT * FROM sucursales WHERE operativa = true ORDER BY nombre ASC", nativeQuery = true)
    List<Sucursal> buscarOperativas();
}
