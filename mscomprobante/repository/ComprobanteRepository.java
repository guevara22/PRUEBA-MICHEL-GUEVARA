package com.duoc.mscomprobante.repository;


import com.duoc.mscomprobante.entity.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {


}
