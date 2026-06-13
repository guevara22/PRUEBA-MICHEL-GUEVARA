package com.duoc.msreservas.service;

import com.duoc.msreservas.dto.EstadoReservaDTO;
import com.duoc.msreservas.dto.EstadoReservaRequestDTO;
import com.duoc.msreservas.exception.ResourceNotFoundException;
import com.duoc.msreservas.mapper.EstadoReservaMapper;
import com.duoc.msreservas.model.EstadoReserva;
import com.duoc.msreservas.repository.EstadoReservaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EstadoReservaService {
    private final EstadoReservaRepository repository;
    private final EstadoReservaMapper mapper;

    @Transactional(readOnly = true)
    public List<EstadoReservaDTO> findAll() {
        log.info("Listando estados-reserva");
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public EstadoReservaDTO findById(Integer id) {
        log.info("Buscando EstadoReserva con id {}", id);
        return mapper.toDTO(getEntity(id));
    }

    public EstadoReservaDTO save(EstadoReservaRequestDTO dto) {
        try {
            EstadoReserva saved = repository.save(mapper.toEntity(dto));
            log.info("EstadoReserva creado con id {}", saved.getId());
            return mapper.toDTO(saved);
        } catch (RuntimeException ex) {
            log.error("Error al crear EstadoReserva: {}", ex.getMessage());
            throw ex;
        }
    }

    public EstadoReservaDTO update(Integer id, EstadoReservaRequestDTO dto) {
        EstadoReserva entity = getEntity(id);
        mapper.update(entity, dto);
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.delete(getEntity(id));
        log.info("EstadoReserva eliminado con id {}", id);
    }

    private EstadoReserva getEntity(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoReserva", id));
    }
}
