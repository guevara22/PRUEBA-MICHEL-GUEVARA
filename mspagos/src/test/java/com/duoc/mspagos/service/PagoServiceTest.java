package com.duoc.mspagos.service;

import com.duoc.mspagos.client.ReservaClient;
import com.duoc.mspagos.client.ReservaRemotaDTO;
import com.duoc.mspagos.dto.PagoDTO;
import com.duoc.mspagos.dto.PagoRequestDTO;
import com.duoc.mspagos.exception.ResourceNotFoundException;
import com.duoc.mspagos.mapper.PagoMapper;
import com.duoc.mspagos.model.Pago;
import com.duoc.mspagos.repository.PagoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {
    @Mock
    private PagoRepository repository;
    @Mock
    private PagoMapper mapper;
    @Mock
    private ReservaClient reservaClient;
    @InjectMocks
    private PagoService service;

    private PagoRequestDTO request;
    private Pago entity;
    private PagoDTO response;

    @BeforeEach
    void setUp() {
        request = new PagoRequestDTO();
        request.setReservaId(1);
        request.setMonto(new BigDecimal("100000"));
        entity = new Pago();
        entity.setId(5);
        response = PagoDTO.builder().id(5).reservaId(1).monto(new BigDecimal("100000")).build();
    }

    @Test
    void findAllRetornaPagosMapeados() {
        // Given
        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.toDTO(entity)).thenReturn(response);

        // When
        List<PagoDTO> result = service.findAll();

        // Then
        assertEquals(1, result.size());
        assertEquals(5, result.get(0).getId());
    }

    @Test
    void findByIdRetornaPagoCuandoExiste() {
        // Given
        when(repository.findById(5)).thenReturn(Optional.of(entity));
        when(mapper.toDTO(entity)).thenReturn(response);

        // When
        PagoDTO result = service.findById(5);

        // Then
        assertEquals(5, result.getId());
    }

    @Test
    void findByIdLanzaExcepcionCuandoNoExiste() {
        // Given
        when(repository.findById(99)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ResourceNotFoundException.class, () -> service.findById(99));
    }

    @Test
    void saveValidaReservaYGuardaPago() {
        // Given
        when(reservaClient.findById(1))
                .thenReturn(new ReservaRemotaDTO(1, new BigDecimal("120000"), true));
        when(mapper.toEntity(request)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDTO(entity)).thenReturn(response);

        // When
        PagoDTO result = service.save(request);

        // Then
        assertEquals(5, result.getId());
        verify(repository).save(entity);
    }

    @Test
    void saveRechazaMontoMayorAlTotalReserva() {
        // Given
        when(reservaClient.findById(1))
                .thenReturn(new ReservaRemotaDTO(1, new BigDecimal("90000"), true));

        // When / Then
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> service.save(request));
        assertEquals("El pago no puede superar el monto total de la reserva", exception.getMessage());
        verify(repository, never()).save(entity);
    }

    @Test
    void updateModificaPagoExistente() {
        // Given
        when(reservaClient.findById(1))
                .thenReturn(new ReservaRemotaDTO(1, new BigDecimal("120000"), true));
        when(repository.findById(5)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDTO(entity)).thenReturn(response);

        // When
        PagoDTO result = service.update(5, request);

        // Then
        assertEquals(5, result.getId());
        verify(mapper).update(entity, request);
    }

    @Test
    void deleteEliminaPagoExistente() {
        // Given
        when(repository.findById(5)).thenReturn(Optional.of(entity));

        // When
        service.delete(5);

        // Then
        verify(repository).delete(entity);
    }

    @Test
    void buscarPorRangoRetornaCoincidencias() {
        // Given
        BigDecimal minimo = new BigDecimal("10000");
        BigDecimal maximo = new BigDecimal("200000");
        when(repository.buscarPorRango(minimo, maximo)).thenReturn(List.of(entity));
        when(mapper.toDTO(entity)).thenReturn(response);

        // When
        List<PagoDTO> result = service.buscarPorRango(minimo, maximo);

        // Then
        assertEquals(1, result.size());
        verify(repository).buscarPorRango(minimo, maximo);
    }

    @Test
    void buscarPorRangoRechazaLimitesInvertidos() {
        // Given
        BigDecimal minimo = new BigDecimal("200000");
        BigDecimal maximo = new BigDecimal("10000");

        // When / Then
        assertThrows(IllegalArgumentException.class, () -> service.buscarPorRango(minimo, maximo));
        verify(repository, never()).buscarPorRango(minimo, maximo);
    }
}
