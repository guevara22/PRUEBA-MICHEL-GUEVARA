package com.duoc.mspagos.controller;

import com.duoc.mspagos.dto.PagoDTO;
import com.duoc.mspagos.dto.PagoRequestDTO;
import com.duoc.mspagos.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.math.BigDecimal;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/pagos")
@RequiredArgsConstructor
@Tag(name = "Pagos", description = "Pagos asociados a las reservas")
public class PagoController {
    private final PagoService service;

    @GetMapping
    @Operation(summary = "Listar pagos")
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    public ResponseEntity<List<PagoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pago por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pago encontrado con enlaces HATEOAS"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    public ResponseEntity<EntityModel<PagoDTO>> findById(
            @Parameter(description = "Identificador del pago", example = "1")
            @PathVariable Integer id) {
        return ResponseEntity.ok(toModel(service.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Registrar pago", description = "Valida la reserva remota y que el monto no la supere")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pago creado"),
            @ApiResponse(responseCode = "400", description = "Datos o monto invalidos")
    })
    public ResponseEntity<EntityModel<PagoDTO>> create(@Valid @RequestBody PagoRequestDTO dto) {
        PagoDTO created = service.save(dto);
        return ResponseEntity.created(URI.create("/api/v1/pagos/" + created.getId()))
                .body(toModel(created));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar pago")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pago actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    public ResponseEntity<EntityModel<PagoDTO>> update(
            @Parameter(description = "Identificador del pago", example = "1")
            @PathVariable Integer id,
            @Valid @RequestBody PagoRequestDTO dto) {
        return ResponseEntity.ok(toModel(service.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pago")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pago eliminado"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar pagos por rango de monto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busqueda realizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Rango invalido")
    })
    public ResponseEntity<List<PagoDTO>> buscar(
            @Parameter(description = "Monto minimo", example = "10000")
            @RequestParam BigDecimal montoMin,
            @Parameter(description = "Monto maximo", example = "250000")
            @RequestParam BigDecimal montoMax) {
        return ResponseEntity.ok(service.buscarPorRango(montoMin, montoMax));
    }

    private EntityModel<PagoDTO> toModel(PagoDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(PagoController.class).findById(dto.getId())).withSelfRel(),
                linkTo(methodOn(PagoController.class).findAll()).withRel("pagos"),
                Link.of("/api/v1/reservas/" + dto.getReservaId(), "reserva"));
    }
}
