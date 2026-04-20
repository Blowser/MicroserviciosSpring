package com.example.bdmovies.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BdmoviesRequestDTO {

    @NotBlank(message = "El título no puede estar vacío")
    @Size(min = 1, max = 100, message = "El título debe tener entre 1 y 100 caracteres")
    private String titulo;

    @NotBlank(message = "El director no puede estar vacío")
    @Size(min = 3, max = 100, message = "El director debe tener entre 3 y 100 caracteres")
    private String director;

    @NotBlank(message = "El género no puede estar vacío")
    private String genero;

    @Min(value = 0, message = "El rating no puede ser menor a 0")
    @Max(value = 10, message = "El rating no puede ser mayor a 10")
    private Double rating;

}
