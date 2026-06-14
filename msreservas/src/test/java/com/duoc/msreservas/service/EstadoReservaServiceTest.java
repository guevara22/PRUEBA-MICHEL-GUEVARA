package com.duoc.msreservas.service;

import com.duoc.msreservas.dto.EstadoReservaDTO;
import com.duoc.msreservas.dto.EstadoReservaRequestDTO;
import com.duoc.msreservas.exception.ResourceNotFoundException;
import com.duoc.msreservas.mapper.EstadoReservaMapper;
import com.duoc.msreservas.model.EstadoReserva;
import com.duoc.msreservas.repository.EstadoReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EstadoReservaServiceTest {
    @Mock
    private EstadoReservaRepository repository;
    @Mock
    private EstadoReservaMapper mapper;
    @InjectMocks
    private EstadoReservaService service;

    private EstadoReserva entity;
    private EstadoReservaDTO response;
    private EstadoReservaRequestDTO request;

    @BeforeEach
    void setUp() {
        entity = new EstadoReserva();
        entity.setId(1);
        response = EstadoReservaDTO.builder().id(1).nombre("PENDIENTE").build();
        request = new EstadoReservaRequestDTO();
    }

    @Test
    void findAllRetornaEstadosMapeados() {
        // Given
        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.toDTO(entity)).thenReturn(response);

        // When
        List<EstadoReservaDTO> result = service.findAll();

        // Then
        assertEquals(1, result.size());
        assertEquals("PENDIENTE", result.get(0).getNombre());
    }

    @Test
    void findByIdRetornaEstadoCuandoExiste() {
        // Given
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.toDTO(entity)).thenReturn(response);

        // When
        EstadoReservaDTO result = service.findById(1);

        // Then
        assertEquals(1, result.getId());
    }

    @Test
    void findByIdLanzaExcepcionCuandoNoExiste() {
        // Given
        when(repository.findById(99)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ResourceNotFoundException.class, () -> service.findById(99));
    }

    @Test
    void saveGuardaYMapeaEstado() {
        // Given
        when(mapper.toEntity(request)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDTO(entity)).thenReturn(response);

        // When
        EstadoReservaDTO result = service.save(request);

        // Then
        assertEquals(1, result.getId());
        verify(repository).save(entity);
    }

    @Test
    void updateModificaEstadoExistente() {
        // Given
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDTO(entity)).thenReturn(response);

        // When
        EstadoReservaDTO result = service.update(1, request);

        // Then
        assertEquals("PENDIENTE", result.getNombre());
        verify(mapper).update(entity, request);
    }

    @Test
    void deleteEliminaEstadoExistente() {
        // Given
        when(repository.findById(1)).thenReturn(Optional.of(entity));

        // When
        service.delete(1);

        // Then
        verify(repository).delete(entity);
    }
}
