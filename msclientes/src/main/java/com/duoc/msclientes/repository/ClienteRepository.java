package com.duoc.msclientes.repository;


import com.duoc.msclientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
    // para personalizar para buscar por email
    List<Cliente> findByEmailContainingIgnoreCase(String email);

}
