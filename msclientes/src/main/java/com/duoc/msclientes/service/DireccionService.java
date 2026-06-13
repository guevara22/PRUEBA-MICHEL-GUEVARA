package com.duoc.msclientes.service;

import com.duoc.msclientes.dto.DireccionDTO;
import com.duoc.msclientes.dto.DireccionRequestDTO;
import com.duoc.msclientes.exception.ResourceNotFoundException;
import com.duoc.msclientes.mapper.DireccionMapper;
import com.duoc.msclientes.model.Direccion;
import com.duoc.msclientes.repository.DireccionRepository;
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
public class DireccionService {
    private final DireccionRepository repository;
    private final DireccionMapper mapper;
    private final ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<DireccionDTO> findAll() {
        log.info("Listando direcciones");
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public DireccionDTO findById(Integer id) {
        log.info("Buscando Direccion con id {}", id);
        return mapper.toDTO(getEntity(id));
    }

    public DireccionDTO save(DireccionRequestDTO dto) {
        try {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", dto.getClienteId()));
            Direccion saved = repository.save(mapper.toEntity(dto, cliente));
            log.info("Direccion creado con id {}", saved.getId());
            return mapper.toDTO(saved);
        } catch (RuntimeException ex) {
            log.error("Error al crear Direccion: {}", ex.getMessage());
            throw ex;
        }
    }

    public DireccionDTO update(Integer id, DireccionRequestDTO dto) {
        Direccion entity = getEntity(id);
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", dto.getClienteId()));
        mapper.update(entity, dto, cliente);
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.delete(getEntity(id));
        log.info("Direccion eliminado con id {}", id);
    }

    private Direccion getEntity(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Direccion", id));
    }
}
