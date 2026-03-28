package cl.duoc.ejemplo.microservicioejemplov2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cl.duoc.ejemplo.microservicioejemplov2.dto.CrearReservaRequestDTO;
import cl.duoc.ejemplo.microservicioejemplov2.dto.ReservaResponseDTO;

@Service
public class ReservaSalaService {

	private final List<ReservaResponseDTO> reservas = new ArrayList<>();

	public ReservaSalaService() {

		reservas.add(ReservaResponseDTO.builder().id("1").nombreEstudiante("Ana Pérez").sala("Sala A")
				.fecha("2026-03-25").hora("10:00").motivo("Estudio de matemáticas").estado("ACTIVA").build());

		reservas.add(ReservaResponseDTO.builder().id("2").nombreEstudiante("Carlos Rojas").sala("Sala B")
				.fecha("2026-03-25").hora("11:00").motivo("Trabajo grupal").estado("ACTIVA").build());

		reservas.add(ReservaResponseDTO.builder().id("3").nombreEstudiante("María Soto").sala("Sala A")
				.fecha("2026-03-26").hora("09:00").motivo("Preparación de exposición").estado("CANCELADA").build());
	}

	public List<ReservaResponseDTO> obtenerTodas() {

		return reservas;
	}

	public ReservaResponseDTO obtenerPorId(String id) {

		for (ReservaResponseDTO reserva : reservas) {

			if (reserva.getId().equals(id)) {

				return reserva;
			}
		}
		return null;
	}

	public ReservaResponseDTO crear(CrearReservaRequestDTO request) {

		ReservaResponseDTO nuevaReserva = ReservaResponseDTO.builder().id(UUID.randomUUID().toString())
				.nombreEstudiante(request.getNombreEstudiante()).sala(request.getSala()).fecha(request.getFecha())
				.hora(request.getHora()).motivo(request.getMotivo()).estado("ACTIVA").build();

		reservas.add(nuevaReserva);
		return nuevaReserva;
	}

	public ReservaResponseDTO cancelar(String id) {

		for (ReservaResponseDTO reserva : reservas) {

			if (reserva.getId().equals(id)) {

				reserva.setEstado("CANCELADA");
				return reserva;
			}
		}
		return null;
	}

	public List<ReservaResponseDTO> consultarDisponibilidad(String sala, String fecha) {

		List<ReservaResponseDTO> resultado = new ArrayList<>();

		for (ReservaResponseDTO reserva : reservas) {

			boolean mismaSala = reserva.getSala().equalsIgnoreCase(sala);
			boolean mismaFecha = reserva.getFecha().equals(fecha);
			boolean activa = reserva.getEstado().equalsIgnoreCase("ACTIVA");

			if (mismaSala && mismaFecha && activa) {

				resultado.add(reserva);
			}
		}

		return resultado;
	}
}
