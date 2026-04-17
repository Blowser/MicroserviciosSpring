package com.example.bdmovies.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BdmoviesResponseDTO {
    private Long id;
    private String titulo;
    private String director;
    private String genero;
}
