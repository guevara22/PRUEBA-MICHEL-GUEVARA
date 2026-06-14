package com.duoc.msvehiculos.service;

import com.duoc.msvehiculos.dto.VehiculoDTO;
import com.duoc.msvehiculos.dto.VehiculoRequestDTO;
import com.duoc.msvehiculos.exception.ResourceNotFoundException;
import com.duoc.msvehiculos.mapper.VehiculoMapper;
import com.duoc.msvehiculos.model.Vehiculo;
import com.duoc.msvehiculos.repository.VehiculoRepository;
import com.duoc.msvehiculos.model.Categoria;
import com.duoc.msvehiculos.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class VehiculoService {
    private final VehiculoRepository repository;
    private final VehiculoMapper mapper;
    private final CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<VehiculoDTO> findAll() {
        log.info("Listando vehiculos");
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public VehiculoDTO findById(Integer id) {
        log.info("Buscando Vehiculo con id {}", id);
        return mapper.toDTO(getEntity(id));
    }

    public VehiculoDTO save(VehiculoRequestDTO dto) {
        try {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", dto.getCategoriaId()));
            Vehiculo saved = repository.save(mapper.toEntity(dto, categoria));
            log.info("Vehiculo creado con id {}", saved.getId());
            return mapper.toDTO(saved);
        } catch (RuntimeException ex) {
            log.error("Error al crear Vehiculo: {}", ex.getMessage());
            throw ex;
        }
    }

    public VehiculoDTO update(Integer id, VehiculoRequestDTO dto) {
        Vehiculo entity = getEntity(id);
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", dto.getCategoriaId()));
        mapper.update(entity, dto, categoria);
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.delete(getEntity(id));
        log.info("Vehiculo eliminado con id {}", id);
    }

    @Transactional(readOnly = true)
    public List<VehiculoDTO> buscarDisponibles(java.math.BigDecimal precioMaximo) {
        log.info("Buscando vehiculos disponibles con precio maximo {}", precioMaximo);
        return repository.findByDisponibleTrueAndPrecioArriendoDiarioLessThan(precioMaximo).stream()
                .map(mapper::toDTO)
                .toList();
    }

    private Vehiculo getEntity(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehiculo", id));
    }
}
