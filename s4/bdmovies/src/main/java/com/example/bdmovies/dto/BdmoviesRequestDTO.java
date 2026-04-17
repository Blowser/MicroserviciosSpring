package com.example.bdmovies.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BdmoviesRequestDTO {
    private String titulo;
    private String director;
    private String genero;
}