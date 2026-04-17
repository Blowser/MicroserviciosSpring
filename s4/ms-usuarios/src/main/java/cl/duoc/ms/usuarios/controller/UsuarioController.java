package cl.duoc.ms.usuarios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ms.usuarios.dto.UsuarioResponseDTO;
import cl.duoc.ms.usuarios.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {

		this.usuarioService = usuarioService;
	}

	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> obtenerTodos() {

		return ResponseEntity.ok(usuarioService.obtenerTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {

		UsuarioResponseDTO usuario = usuarioService.obtenerPorId(id);

		if (usuario == null) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(usuario);
	}
}
