package com.duoc.msreservas.controller;

import com.duoc.msreservas.dto.ReservaDTO;
import com.duoc.msreservas.dto.ReservaRequestDTO;
import com.duoc.msreservas.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/reservas")
@RequiredArgsConstructor
@Tag(name = "Reservas", description = "Administracion de reservas de vehiculos")
public class ReservaController {
    private final ReservaService service;

    @GetMapping
    @Operation(summary = "Listar reservas", description = "Retorna todas las reservas registradas")
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    public ResponseEntity<List<ReservaDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar reserva por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reserva encontrada con enlaces HATEOAS"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<EntityModel<ReservaDTO>> findById(
            @Parameter(description = "Identificador de la reserva", example = "1")
            @PathVariable Integer id) {
        return ResponseEntity.ok(toModel(service.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Crear reserva", description = "Valida cliente, vehiculo disponible y estado antes de guardar")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reserva creada"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos o vehiculo no disponible")
    })
    public ResponseEntity<EntityModel<ReservaDTO>> create(@Valid @RequestBody ReservaRequestDTO dto) {
        ReservaDTO created = service.save(dto);
        return ResponseEntity.created(URI.create("/api/v1/reservas/" + created.getId()))
                .body(toModel(created));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reserva actualizada"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<EntityModel<ReservaDTO>> update(
            @Parameter(description = "Identificador de la reserva", example = "1")
            @PathVariable Integer id,
            @Valid @RequestBody ReservaRequestDTO dto) {
        return ResponseEntity.ok(toModel(service.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Reserva eliminada"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar reservas desde una fecha")
    @ApiResponse(responseCode = "200", description = "Busqueda realizada correctamente")
    public ResponseEntity<List<ReservaDTO>> buscar(
            @Parameter(description = "Fecha inicial en formato AAAA-MM-DD", example = "2026-06-15")
            @RequestParam LocalDate fechaDesde) {
        return ResponseEntity.ok(service.buscarDesdeFecha(fechaDesde));
    }

    private EntityModel<ReservaDTO> toModel(ReservaDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(ReservaController.class).findById(dto.getId())).withSelfRel(),
                linkTo(methodOn(ReservaController.class).findAll()).withRel("reservas"),
                linkTo(methodOn(EstadoReservaController.class)
                        .findById(dto.getEstadoReservaId())).withRel("estado-reserva"));
    }
}
