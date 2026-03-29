package com.semana3.citasmedicas.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaMedicaResponseDTO {

    private String id;
    private String nombrePaciente;
    private String nombreMedico;
    private String fecha;
    private String hora;
    private String motivo;
    private String estado;
}
