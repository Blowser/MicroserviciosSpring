package com.example.bdmovies.controller;

import com.example.bdmovies.dto.BdmoviesRequestDTO;
import com.example.bdmovies.dto.BdmoviesResponseDTO;
import com.example.bdmovies.service.BdmoviesService;
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

    @GetMapping
    public ResponseEntity<List<BdmoviesResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BdmoviesResponseDTO> obtenerPorId(@PathVariable Long id) {
        BdmoviesResponseDTO dto = service.obtenerPorId(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<BdmoviesResponseDTO> crear(@RequestBody BdmoviesRequestDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BdmoviesResponseDTO> actualizar(@PathVariable Long id, @RequestBody BdmoviesRequestDTO dto) {
        BdmoviesResponseDTO actualizado = service.actualizar(id, dto);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = service.eliminar(id);
        if (!eliminado) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}