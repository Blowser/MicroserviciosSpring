package com.example.bdmovies.controller;

import com.example.bdmovies.dto.BdmoviesRequestDTO;
import com.example.bdmovies.dto.BdmoviesResponseDTO;
import com.example.bdmovies.service.BdmoviesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class BdmoviesController {

    private final BdmoviesService service;

    public BdmoviesController(BdmoviesService service) {
        this.service = service;
    }

    // ============================
    // CRUD BÁSICO
    // ============================

    @GetMapping
    public ResponseEntity<List<BdmoviesResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        BdmoviesResponseDTO dto = service.obtenerPorId(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<BdmoviesResponseDTO> crear(@Valid @RequestBody BdmoviesRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody BdmoviesRequestDTO dto) {
        BdmoviesResponseDTO actualizado = service.actualizar(id, dto);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        boolean eliminado = service.eliminar(id);
        if (!eliminado) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    // ============================
    // SOFT DELETE
    // ============================

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<?> desactivar(@PathVariable Long id) {
        boolean ok = service.desactivar(id);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    // ============================
    // BÚSQUEDAS AVANZADAS
    // ============================

    // Buscar por título exacto
    @GetMapping("/buscar")
    public ResponseEntity<List<BdmoviesResponseDTO>> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(service.buscarPorTitulo(titulo));
    }

    // Buscar por título parcial
    @GetMapping("/buscar-parcial")
    public ResponseEntity<List<BdmoviesResponseDTO>> buscarPorTituloParcial(@RequestParam String titulo) {
        return ResponseEntity.ok(service.buscarPorTituloParcial(titulo));
    }

    // Buscar por género
    @GetMapping("/genero")
    public ResponseEntity<List<BdmoviesResponseDTO>> buscarPorGenero(@RequestParam String genero) {
        return ResponseEntity.ok(service.buscarPorGenero(genero));
    }

    // Buscar por director parcial
    @GetMapping("/director-parcial")
    public ResponseEntity<List<BdmoviesResponseDTO>> buscarPorDirector(@RequestParam String director) {
        return ResponseEntity.ok(service.buscarPorDirectorParcial(director));
    }

    // Buscar por rango de rating
    @GetMapping("/rating")
    public ResponseEntity<List<BdmoviesResponseDTO>> buscarPorRating(
            @RequestParam Double min,
            @RequestParam Double max) {

        return ResponseEntity.ok(service.buscarPorRating(min, max));
    }
}
