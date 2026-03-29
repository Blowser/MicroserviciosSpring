package com.semana3.citasmedicas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.semana3.citasmedicas.dto.CrearCitaRequestDTO;
import com.semana3.citasmedicas.dto.CitaMedicaResponseDTO;


@Service
public class CitaMedicaService {

    private final List<CitaMedicaResponseDTO> citas = new ArrayList<>();

    public CitaMedicaService() {

        citas.add(CitaMedicaResponseDTO.builder()
                .id("1")
                .nombrePaciente("Luna")
                .nombreMedico("Dr. Pérez")
                .fecha("2026-04-01")
                .hora("10:00")
                .motivo("Control general")
                .estado("ACTIVA")
                .build());

        citas.add(CitaMedicaResponseDTO.builder()
                .id("2")
                .nombrePaciente("Max")
                .nombreMedico("Dra. Soto")
                .fecha("2026-04-01")
                .hora("11:00")
                .motivo("Vacunación")
                .estado("ACTIVA")
                .build());

        citas.add(CitaMedicaResponseDTO.builder()
                .id("3")
                .nombrePaciente("Rocky")
                .nombreMedico("Dr. Pérez")
                .fecha("2026-04-02")
                .hora("09:00")
                .motivo("Consulta dermatológica")
                .estado("CANCELADA")
                .build());
    }

    public List<CitaMedicaResponseDTO> obtenerTodas() {
        return citas;
    }

    public CitaMedicaResponseDTO obtenerPorId(String id) {
        return citas.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public CitaMedicaResponseDTO crear(CrearCitaRequestDTO request) {

        CitaMedicaResponseDTO nueva = CitaMedicaResponseDTO.builder()
                .id(UUID.randomUUID().toString())
                .nombrePaciente(request.getNombrePaciente())
                .nombreMedico(request.getNombreMedico())
                .fecha(request.getFecha())
                .hora(request.getHora())
                .motivo(request.getMotivo())
                .estado("ACTIVA")
                .build();

        citas.add(nueva);
        return nueva;
    }

    public CitaMedicaResponseDTO cancelar(String id) {

        for (CitaMedicaResponseDTO cita : citas) {
            if (cita.getId().equals(id)) {
                cita.setEstado("CANCELADA");
                return cita;
            }
        }
        return null;
    }

    public List<CitaMedicaResponseDTO> consultarDisponibilidad(String medico, String fecha) {

        List<CitaMedicaResponseDTO> resultado = new ArrayList<>();

        for (CitaMedicaResponseDTO cita : citas) {

            boolean mismoMedico = cita.getNombreMedico().equalsIgnoreCase(medico);
            boolean mismaFecha = cita.getFecha().equals(fecha);
            boolean activa = cita.getEstado().equalsIgnoreCase("ACTIVA");

            if (mismoMedico && mismaFecha && activa) {
                resultado.add(cita);
            }
        }

        return resultado;
    }
}
