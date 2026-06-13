package com.duoc.msempleados.service;

import com.duoc.msempleados.dto.EmpleadoDTO;
import com.duoc.msempleados.dto.EmpleadoRequestDTO;
import com.duoc.msempleados.exception.ResourceNotFoundException;
import com.duoc.msempleados.mapper.EmpleadoMapper;
import com.duoc.msempleados.model.Empleado;
import com.duoc.msempleados.repository.EmpleadoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EmpleadoService {
    private final EmpleadoRepository repository;
    private final EmpleadoMapper mapper;

    @Transactional(readOnly = true)
    public List<EmpleadoDTO> findAll() {
        log.info("Listando empleados");
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public EmpleadoDTO findById(Integer id) {
        log.info("Buscando Empleado con id {}", id);
        return mapper.toDTO(getEntity(id));
    }

    public EmpleadoDTO save(EmpleadoRequestDTO dto) {
        try {
            Empleado saved = repository.save(mapper.toEntity(dto));
            log.info("Empleado creado con id {}", saved.getId());
            return mapper.toDTO(saved);
        } catch (RuntimeException ex) {
            log.error("Error al crear Empleado: {}", ex.getMessage());
            throw ex;
        }
    }

    public EmpleadoDTO update(Integer id, EmpleadoRequestDTO dto) {
        Empleado entity = getEntity(id);
        mapper.update(entity, dto);
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.delete(getEntity(id));
        log.info("Empleado eliminado con id {}", id);
    }

    private Empleado getEntity(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", id));
    }
}
