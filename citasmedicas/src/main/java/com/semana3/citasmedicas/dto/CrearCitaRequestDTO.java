package com.semana3.citasmedicas.dto;

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
public class CrearCitaRequestDTO {

    @NotBlank(message = "El nombre del paciente es obligatorio")
    private String nombrePaciente;

    @NotBlank(message = "El nombre del médico es obligatorio")
    private String nombreMedico;

    @NotBlank(message = "La fecha es obligatoria")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha debe tener formato yyyy-MM-dd")
    private String fecha;

    @NotBlank(message = "La hora es obligatoria")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "La hora debe tener formato HH:mm")
    private String hora;

    @NotBlank(message = "El motivo es obligatorio")
    private String motivo;
}