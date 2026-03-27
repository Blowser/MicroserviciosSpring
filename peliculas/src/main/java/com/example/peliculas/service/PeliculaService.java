package com.example.peliculas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.peliculas.dto.PeliculaDTO;

@Service
public class PeliculaService {

    private List<PeliculaDTO> peliculas = new ArrayList<>();

    public PeliculaService() {

        peliculas.add(new PeliculaDTO("1", "El Señor de los Anillos", 2001, "Peter Jackson", "Fantasía",
                "Un hobbit debe destruir un anillo maligno."));
        peliculas.add(new PeliculaDTO("2", "Inception", 2010, "Christopher Nolan", "Ciencia Ficción",
                "Un ladrón entra en los sueños de las personas."));
        peliculas.add(new PeliculaDTO("3", "Titanic", 1997, "James Cameron", "Drama",
                "Una historia de amor en el famoso barco hundido."));
        peliculas.add(new PeliculaDTO("4", "The Matrix", 1999, "Wachowski", "Acción",
                "Un hacker descubre la verdad sobre su realidad."));
        peliculas.add(new PeliculaDTO("5", "Toy Story", 1995, "John Lasseter", "Animación",
                "Los juguetes cobran vida cuando no los ves."));
    }

    public List<PeliculaDTO> obtenerTodas() {
        return peliculas;
    }

    public PeliculaDTO obtenerPorId(String id) {
        for (PeliculaDTO pelicula : peliculas) {
            if (pelicula.getId().equals(id)) {
                return pelicula;
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



}
