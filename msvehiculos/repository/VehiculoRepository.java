package com.duoc.msvehiculos.repository;

import com.duoc.msvehiculos.modelo.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehiculoRepository extends JpaRepository< Vehiculo, Long> {

//Aqui se puede agregar los metodos personalizados si es que necesito buscar las adelante
    // por ejemplo: si es que quiero buscar por vehiculos por patente.

}
