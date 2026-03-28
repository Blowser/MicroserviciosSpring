package com.example.peliculas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.peliculas.dto.PeliculaDTO;

@Service
public class PeliculaService {

    private List<PeliculaDTO> peliculas = new ArrayList<>();

    public PeliculaService() {
    peliculas.add(new PeliculaDTO("1", "Shrek", "Andrew Adamson", "Animación"));
    peliculas.add(new PeliculaDTO("2", "Interestelar", "Christopher Nolan", "Ciencia Ficción"));
    peliculas.add(new PeliculaDTO("3", "El Padrino", "Francis Ford Coppola", "Drama"));
    peliculas.add(new PeliculaDTO("4", "El Señor de los Anillos: La Comunidad del Anillo", "Peter Jackson", "Fantasía"));
    peliculas.add(new PeliculaDTO("5", "Toy Story", "John Lasseter", "Animación"));
    }


    public List<PeliculaDTO> obtenerTodos() {
        return peliculas;
    }

    public PeliculaDTO obtenerPorId(String id) {
        for (PeliculaDTO p : peliculas) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public PeliculaDTO crear(PeliculaDTO nueva) {
        peliculas.add(nueva);
        return nueva;
    }

    public boolean eliminar(String id) {
        return peliculas.removeIf(p -> p.getId().equals(id));
    }

    public PeliculaDTO actualizar(String id, PeliculaDTO nueva) {

    for (int i = 0; i < peliculas.size(); i++) {
        if (peliculas.get(i).getId().equals(id)) {
            peliculas.set(i, nueva);
            return nueva;
        }
    }

    return null;
}

}
