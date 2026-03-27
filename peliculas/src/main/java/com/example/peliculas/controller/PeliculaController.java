package com.example.peliculas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.peliculas.dto.PeliculaDTO;
import com.example.peliculas.service.PeliculaService;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService service;

    public PeliculaController(PeliculaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> readAll() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable("id") String id) {
        PeliculaDTO pelicula = service.obtenerPorId(id);
        if (pelicula == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pelicula);
    }

    @PostMapping
    public ResponseEntity<PeliculaDTO> create(@RequestBody PeliculaDTO body) {
        return ResponseEntity.ok(service.crear(body));
    }
}
