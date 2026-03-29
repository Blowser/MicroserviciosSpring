package com.semana3.mascotas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.semana3.mascotas.dto.OrdenCompraDTO;
import com.semana3.mascotas.service.OrdenCompraService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordenes")
public class OrdenCompraController {

    private final OrdenCompraService service;

    public OrdenCompraController(OrdenCompraService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrdenCompraDTO>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable String id) {
        OrdenCompraDTO orden = service.obtenerPorId(id);

        if (orden == null) {
            return ResponseEntity.status(404).body("No se encontró la orden con id: " + id);
        }

        return ResponseEntity.ok(orden);
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody OrdenCompraDTO request) {
        return ResponseEntity.ok(service.crear(request));
    }

    @GetMapping("/{id}/estado")
    public ResponseEntity<?> obtenerEstado(@PathVariable String id) {
        String estado = service.obtenerEstado(id);

        if (estado == null) {
            return ResponseEntity.status(404).body("No se encontró la orden con id: " + id);
        }

        return ResponseEntity.ok(estado);
    }
}