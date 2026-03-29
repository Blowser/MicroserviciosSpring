package com.semana3.mascotas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoMascotaDTO {

    private String id;
    private String nombre;
    private String categoria;
    private int precio;
}
