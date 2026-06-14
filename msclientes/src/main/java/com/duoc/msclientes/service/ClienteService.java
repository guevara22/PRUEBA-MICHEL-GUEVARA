package com.duoc.msclientes.service;

import com.duoc.msclientes.dto.ClienteDTO;
import com.duoc.msclientes.dto.ClienteRequestDTO;
import com.duoc.msclientes.exception.ResourceNotFoundException;
import com.duoc.msclientes.mapper.ClienteMapper;
import com.duoc.msclientes.model.Cliente;
import com.duoc.msclientes.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ClienteService {
    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        log.info("Listando clientes");
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public ClienteDTO findById(Integer id) {
        log.info("Buscando Cliente con id {}", id);
        return mapper.toDTO(getEntity(id));
    }

    public ClienteDTO save(ClienteRequestDTO dto) {
        try {
            Cliente saved = repository.save(mapper.toEntity(dto));
            log.info("Cliente creado con id {}", saved.getId());
            return mapper.toDTO(saved);
        } catch (RuntimeException ex) {
            log.error("Error al crear Cliente: {}", ex.getMessage());
            throw ex;
        }
    }

    public ClienteDTO update(Integer id, ClienteRequestDTO dto) {
        Cliente entity = getEntity(id);
        mapper.update(entity, dto);
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.delete(getEntity(id));
        log.info("Cliente eliminado con id {}", id);
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> buscarPorEmail(String email) {
        log.info("Buscando clientes por email {}", email);
        return repository.findByEmailContainingIgnoreCase(email).stream()
                .map(mapper::toDTO)
                .toList();
    }

    private Cliente getEntity(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
    }
}
