package com.semana3.citasmedicas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.semana3.citasmedicas.dto.CrearCitaRequestDTO;
import com.semana3.citasmedicas.dto.CitaMedicaResponseDTO;
import com.semana3.citasmedicas.service.CitaMedicaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/citas")
public class CitaMedicaController {

    private final CitaMedicaService service;

    public CitaMedicaController(CitaMedicaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CitaMedicaResponseDTO>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable String id) {

        CitaMedicaResponseDTO cita = service.obtenerPorId(id);

        if (cita == null) {
            return ResponseEntity.status(404).body("No se encontró la cita con id: " + id);
        }

        return ResponseEntity.ok(cita);
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody CrearCitaRequestDTO request) {
        return ResponseEntity.ok(service.crear(request));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable String id) {

        CitaMedicaResponseDTO cancelada = service.cancelar(id);

        if (cancelada == null) {
            return ResponseEntity.status(404).body("No se encontró la cita con id: " + id);
        }

        return ResponseEntity.ok(cancelada);
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<List<CitaMedicaResponseDTO>> consultarDisponibilidad(
            @RequestParam String medico,
            @RequestParam String fecha) {

        return ResponseEntity.ok(service.consultarDisponibilidad(medico, fecha));
    }
}
