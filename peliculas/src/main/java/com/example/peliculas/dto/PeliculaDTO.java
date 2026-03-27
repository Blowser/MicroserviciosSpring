package com.example.peliculas.dto;

public class PeliculaDTO {

    private String id;
    private String titulo;
    private String director;
    private String genero;

    public PeliculaDTO() {}

    public PeliculaDTO(String id, String titulo, String director, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}
