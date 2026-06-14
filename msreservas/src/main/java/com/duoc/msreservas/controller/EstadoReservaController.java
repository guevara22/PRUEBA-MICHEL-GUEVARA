package com.duoc.msreservas.controller;

import com.duoc.msreservas.dto.EstadoReservaDTO;
import com.duoc.msreservas.dto.EstadoReservaRequestDTO;
import com.duoc.msreservas.service.EstadoReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/v1/estados-reserva")
@RequiredArgsConstructor
@Tag(name = "Estados de reserva", description = "Estados disponibles para el ciclo de una reserva")
public class EstadoReservaController {
    private final EstadoReservaService service;

    @GetMapping
    @Operation(summary = "Listar estados de reserva")
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    public ResponseEntity<List<EstadoReservaDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar estado de reserva por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado encontrado con enlaces HATEOAS"),
            @ApiResponse(responseCode = "404", description = "Estado no encontrado")
    })
    public ResponseEntity<EntityModel<EstadoReservaDTO>> findById(
            @Parameter(description = "Identificador del estado", example = "1")
            @PathVariable Integer id) {
        return ResponseEntity.ok(toModel(service.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Crear estado de reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Estado creado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    public ResponseEntity<EntityModel<EstadoReservaDTO>> create(@Valid @RequestBody EstadoReservaRequestDTO dto) {
        EstadoReservaDTO created = service.save(dto);
        return ResponseEntity.created(URI.create("/api/v1/estados-reserva/" + created.getId()))
                .body(toModel(created));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar estado de reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "404", description = "Estado no encontrado")
    })
    public ResponseEntity<EntityModel<EstadoReservaDTO>> update(
            @Parameter(description = "Identificador del estado", example = "1")
            @PathVariable Integer id,
            @Valid @RequestBody EstadoReservaRequestDTO dto) {
        return ResponseEntity.ok(toModel(service.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar estado de reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Estado eliminado"),
            @ApiResponse(responseCode = "404", description = "Estado no encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private EntityModel<EstadoReservaDTO> toModel(EstadoReservaDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(EstadoReservaController.class).findById(dto.getId())).withSelfRel(),
                linkTo(methodOn(EstadoReservaController.class).findAll()).withRel("estados-reserva"),
                linkTo(methodOn(ReservaController.class).findAll()).withRel("reservas"));
    }
}
