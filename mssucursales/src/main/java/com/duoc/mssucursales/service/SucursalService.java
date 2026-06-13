package com.duoc.mssucursales.service;

import com.duoc.mssucursales.dto.SucursalDTO;
import com.duoc.mssucursales.dto.SucursalRequestDTO;
import com.duoc.mssucursales.exception.ResourceNotFoundException;
import com.duoc.mssucursales.mapper.SucursalMapper;
import com.duoc.mssucursales.model.Sucursal;
import com.duoc.mssucursales.repository.SucursalRepository;
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
public class SucursalService {
    private final SucursalRepository repository;
    private final SucursalMapper mapper;
    private final RegionRepository regionRepository;

    @Transactional(readOnly = true)
    public List<SucursalDTO> findAll() {
        log.info("Listando sucursales");
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public SucursalDTO findById(Integer id) {
        log.info("Buscando Sucursal con id {}", id);
        return mapper.toDTO(getEntity(id));
    }

    public SucursalDTO save(SucursalRequestDTO dto) {
        try {
        Region region = regionRepository.findById(dto.getRegionId())
                .orElseThrow(() -> new ResourceNotFoundException("Region", dto.getRegionId()));
            Sucursal saved = repository.save(mapper.toEntity(dto, region));
            log.info("Sucursal creado con id {}", saved.getId());
            return mapper.toDTO(saved);
        } catch (RuntimeException ex) {
            log.error("Error al crear Sucursal: {}", ex.getMessage());
            throw ex;
        }
    }

    public SucursalDTO update(Integer id, SucursalRequestDTO dto) {
        Sucursal entity = getEntity(id);
        Region region = regionRepository.findById(dto.getRegionId())
                .orElseThrow(() -> new ResourceNotFoundException("Region", dto.getRegionId()));
        mapper.update(entity, dto, region);
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.delete(getEntity(id));
        log.info("Sucursal eliminado con id {}", id);
    }

    private Sucursal getEntity(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sucursal", id));
    }
}
