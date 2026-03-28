package cl.duoc.ejemplo.microservicioejemplov2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ejemplo.microservicioejemplov2.dto.CrearReservaRequestDTO;
import cl.duoc.ejemplo.microservicioejemplov2.dto.ReservaResponseDTO;
import cl.duoc.ejemplo.microservicioejemplov2.service.ReservaSalaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservas")
public class ReservaSalaController {

	private final ReservaSalaService service;

	public ReservaSalaController(ReservaSalaService service) {

		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<ReservaResponseDTO>> obtenerTodas() {

		return ResponseEntity.ok(service.obtenerTodas());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable String id) {

		ReservaResponseDTO reserva = service.obtenerPorId(id);

		if (reserva == null) {

			return ResponseEntity.status(404).body("No se encontró la reserva con id: " + id);
		}

		return ResponseEntity.ok(reserva);
	}

	@PostMapping
	public ResponseEntity<?> crear(@Valid @RequestBody CrearReservaRequestDTO request) {

		ReservaResponseDTO creada = service.crear(request);

		return ResponseEntity.ok(creada);
	}

	@PutMapping("/{id}/cancelar")
	public ResponseEntity<?> cancelar(@PathVariable String id) {

		ReservaResponseDTO cancelada = service.cancelar(id);

		if (cancelada == null) {

			return ResponseEntity.status(404).body("No se encontró la reserva con id: " + id);
		}

		return ResponseEntity.ok(cancelada);
	}

	@GetMapping("/disponibilidad")
	public ResponseEntity<List<ReservaResponseDTO>> consultarDisponibilidad(@RequestParam String sala,
			@RequestParam String fecha) {

		return ResponseEntity.ok(service.consultarDisponibilidad(sala, fecha));
	}
}
