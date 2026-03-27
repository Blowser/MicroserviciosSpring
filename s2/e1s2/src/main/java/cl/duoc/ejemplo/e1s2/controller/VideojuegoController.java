package cl.duoc.ejemplo.e1s2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ejemplo.e1s2.dto.VideojuegoDTO;
import cl.duoc.ejemplo.e1s2.service.VideojuegoService;

@RestController
@RequestMapping("/microservicio/videojuegos")
public class VideojuegoController {

	private final VideojuegoService service;

	public VideojuegoController(VideojuegoService service) {

		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<VideojuegoDTO>> readAll() {

		return ResponseEntity.ok(service.obtenerTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable("id") String id) {

		VideojuegoDTO videojuego = service.obtenerPorId(id);

		if (videojuego == null) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(videojuego);
	}

	@PostMapping
	public ResponseEntity<VideojuegoDTO> create(@RequestBody VideojuegoDTO body) {

		return ResponseEntity.ok(service.crear(body));
	}
}
