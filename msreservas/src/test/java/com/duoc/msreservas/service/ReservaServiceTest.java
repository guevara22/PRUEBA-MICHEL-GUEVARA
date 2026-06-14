package com.duoc.msreservas.service;

import com.duoc.msreservas.client.ClienteClient;
import com.duoc.msreservas.client.ClienteRemotoDTO;
import com.duoc.msreservas.client.VehiculoClient;
import com.duoc.msreservas.client.VehiculoRemotoDTO;
import com.duoc.msreservas.dto.ReservaDTO;
import com.duoc.msreservas.dto.ReservaRequestDTO;
import com.duoc.msreservas.exception.ResourceNotFoundException;
import com.duoc.msreservas.mapper.ReservaMapper;
import com.duoc.msreservas.model.EstadoReserva;
import com.duoc.msreservas.model.Reserva;
import com.duoc.msreservas.repository.EstadoReservaRepository;
import com.duoc.msreservas.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {
    @Mock
    private ReservaRepository repository;
    @Mock
    private ReservaMapper mapper;
    @Mock
    private EstadoReservaRepository estadoReservaRepository;
    @Mock
    private ClienteClient clienteClient;
    @Mock
    private VehiculoClient vehiculoClient;
    @InjectMocks
    private ReservaService service;

    private ReservaRequestDTO request;
    private Reserva entity;
    private EstadoReserva estado;
    private ReservaDTO response;

    @BeforeEach
    void setUp() {
        request = new ReservaRequestDTO();
        request.setClienteId(1);
        request.setVehiculoId(2);
        request.setFechaInicio(LocalDate.now().plusDays(1));
        request.setFechaFin(LocalDate.now().plusDays(4));
        request.setMontoTotal(new BigDecimal("120000"));
        request.setEstadoReservaId(1);

        estado = new EstadoReserva();
        estado.setId(1);
        entity = new Reserva();
        entity.setId(10);
        response = ReservaDTO.builder().id(10).montoTotal(new BigDecimal("120000")).build();
    }

    @Test
    void findAllRetornaReservasMapeadas() {
        // Given
        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.toDTO(entity)).thenReturn(response);

        // When
        List<ReservaDTO> result = service.findAll();

        // Then
        assertEquals(1, result.size());
        assertEquals(10, result.get(0).getId());
    }

    @Test
    void findByIdRetornaReservaCuandoExiste() {
        // Given
        when(repository.findById(10)).thenReturn(Optional.of(entity));
        when(mapper.toDTO(entity)).thenReturn(response);

        // When
        ReservaDTO result = service.findById(10);

        // Then
        assertEquals(10, result.getId());
    }

    @Test
    void findByIdLanzaExcepcionCuandoNoExiste() {
        // Given
        when(repository.findById(99)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ResourceNotFoundException.class, () -> service.findById(99));
    }

    @Test
    void saveValidaServiciosRemotosYGuarda() {
        // Given
        when(clienteClient.findById(1)).thenReturn(new ClienteRemotoDTO(1, "Michel", "michel@email.cl"));
        when(vehiculoClient.findById(2))
                .thenReturn(new VehiculoRemotoDTO(2, "ABCD12", new BigDecimal("40000"), true));
        when(estadoReservaRepository.findById(1)).thenReturn(Optional.of(estado));
        when(mapper.toEntity(request, estado)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDTO(entity)).thenReturn(response);

        // When
        ReservaDTO result = service.save(request);

        // Then
        assertEquals(10, result.getId());
        verify(repository).save(entity);
    }

    @Test
    void saveRechazaFechaFinAnterior() {
        // Given
        request.setFechaFin(request.getFechaInicio());

        // When / Then
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> service.save(request));
        assertEquals("La fecha de fin debe ser posterior a la fecha de inicio", exception.getMessage());
        verify(clienteClient, never()).findById(1);
    }

    @Test
    void saveRechazaVehiculoNoDisponible() {
        // Given
        when(clienteClient.findById(1)).thenReturn(new ClienteRemotoDTO(1, "Michel", "michel@email.cl"));
        when(vehiculoClient.findById(2))
                .thenReturn(new VehiculoRemotoDTO(2, "ABCD12", new BigDecimal("40000"), false));

        // When / Then
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> service.save(request));
        assertEquals("El vehiculo seleccionado no esta disponible", exception.getMessage());
        verify(repository, never()).save(entity);
    }

    @Test
    void saveRechazaEstadoInexistente() {
        // Given
        when(clienteClient.findById(1)).thenReturn(new ClienteRemotoDTO(1, "Michel", "michel@email.cl"));
        when(vehiculoClient.findById(2))
                .thenReturn(new VehiculoRemotoDTO(2, "ABCD12", new BigDecimal("40000"), true));
        when(estadoReservaRepository.findById(1)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ResourceNotFoundException.class, () -> service.save(request));
    }

    @Test
    void updateModificaReservaExistente() {
        // Given
        when(clienteClient.findById(1)).thenReturn(new ClienteRemotoDTO(1, "Michel", "michel@email.cl"));
        when(vehiculoClient.findById(2))
                .thenReturn(new VehiculoRemotoDTO(2, "ABCD12", new BigDecimal("40000"), true));
        when(repository.findById(10)).thenReturn(Optional.of(entity));
        when(estadoReservaRepository.findById(1)).thenReturn(Optional.of(estado));
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDTO(entity)).thenReturn(response);

        // When
        ReservaDTO result = service.update(10, request);

        // Then
        assertEquals(10, result.getId());
        verify(mapper).update(entity, request, estado);
    }

    @Test
    void deleteEliminaReservaExistente() {
        // Given
        when(repository.findById(10)).thenReturn(Optional.of(entity));

        // When
        service.delete(10);

        // Then
        verify(repository).delete(entity);
    }

    @Test
    void buscarDesdeFechaRetornaCoincidencias() {
        // Given
        LocalDate fecha = LocalDate.now();
        when(repository.buscarDesdeFecha(fecha)).thenReturn(List.of(entity));
        when(mapper.toDTO(entity)).thenReturn(response);

        // When
        List<ReservaDTO> result = service.buscarDesdeFecha(fecha);

        // Then
        assertEquals(1, result.size());
        verify(repository).buscarDesdeFecha(fecha);
    }
}
