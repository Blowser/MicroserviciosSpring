package com.semana3.mascotas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenCompraDTO {

    private String id;

    @NotBlank(message = "El ID del producto es obligatorio")
    private String productoId;

    @NotBlank(message = "La fecha es obligatoria")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha debe tener formato yyyy-MM-dd")
    private String fecha;

    @NotBlank(message = "La cantidad es obligatoria")
    @Pattern(regexp = "^[1-9]\\d*$", message = "La cantidad debe ser un número entero positivo")
    private String cantidad;

    private String estado;
}