package com.duoc.mssucursales.service;

import com.duoc.mssucursales.dto.RegionDTO;
import com.duoc.mssucursales.dto.RegionRequestDTO;
import com.duoc.mssucursales.exception.ResourceNotFoundException;
import com.duoc.mssucursales.mapper.RegionMapper;
import com.duoc.mssucursales.model.Region;
import com.duoc.mssucursales.repository.RegionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RegionService {
    private final RegionRepository repository;
    private final RegionMapper mapper;

    @Transactional(readOnly = true)
    public List<RegionDTO> findAll() {
        log.info("Listando regiones");
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public RegionDTO findById(Integer id) {
        log.info("Buscando Region con id {}", id);
        return mapper.toDTO(getEntity(id));
    }

    public RegionDTO save(RegionRequestDTO dto) {
        try {
            Region saved = repository.save(mapper.toEntity(dto));
            log.info("Region creado con id {}", saved.getId());
            return mapper.toDTO(saved);
        } catch (RuntimeException ex) {
            log.error("Error al crear Region: {}", ex.getMessage());
            throw ex;
        }
    }

    public RegionDTO update(Integer id, RegionRequestDTO dto) {
        Region entity = getEntity(id);
        mapper.update(entity, dto);
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.delete(getEntity(id));
        log.info("Region eliminado con id {}", id);
    }

    private Region getEntity(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region", id));
    }
}
