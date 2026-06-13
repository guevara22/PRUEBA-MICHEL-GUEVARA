package com.duoc.msvehiculos.service;

import com.duoc.msvehiculos.dto.CategoriaDTO;
import com.duoc.msvehiculos.dto.CategoriaRequestDTO;
import com.duoc.msvehiculos.exception.ResourceNotFoundException;
import com.duoc.msvehiculos.mapper.CategoriaMapper;
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
public class CategoriaService {
    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll() {
        log.info("Listando categorias");
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findById(Integer id) {
        log.info("Buscando Categoria con id {}", id);
        return mapper.toDTO(getEntity(id));
    }

    public CategoriaDTO save(CategoriaRequestDTO dto) {
        try {
            Categoria saved = repository.save(mapper.toEntity(dto));
            log.info("Categoria creado con id {}", saved.getId());
            return mapper.toDTO(saved);
        } catch (RuntimeException ex) {
            log.error("Error al crear Categoria: {}", ex.getMessage());
            throw ex;
        }
    }

    public CategoriaDTO update(Integer id, CategoriaRequestDTO dto) {
        Categoria entity = getEntity(id);
        mapper.update(entity, dto);
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.delete(getEntity(id));
        log.info("Categoria eliminado con id {}", id);
    }

    private Categoria getEntity(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", id));
    }
}
